package com.bartlot.Server.service;

import com.bartlot.Server.entity.MeterConfigEntity;
import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Task3Service {

    @Autowired
    private BruteAcquisitionService bruteAcquisitionService;

    @Autowired
    private MeterConfigService meterConfigService;

    @Autowired
    private BruteAcquisitionRepository bruteAcquisitionRepository;

    public void updateSource() {
        List<BruteAcquisitionEntity> list = new ArrayList<BruteAcquisitionEntity>();
        list = bruteAcquisitionService.getListMeterData();
        String presence = "";
        HashMap<String, MeterConfigEntity> map = meterConfigService.getListMeterConfig();
        HashMap<String, List<BruteAcquisitionEntity>> listAllCompteur = bruteAcquisitionService
                .getListMeterDataByType();
        for (int i = 0; i < list.size(); i++) {
            BruteAcquisitionEntity meter = list.get(i);
            List<BruteAcquisitionEntity> listCompteur = listAllCompteur.get(meter.getIdCompteur());
            try {
                Double dataAPlus = Double.parseDouble("" + meter.getDataAPlus());
                Double dataAMoins = Double.parseDouble("" + meter.getDataAMoins());
                presence = getPresenceValue(meter, dataAPlus, dataAMoins, listCompteur);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MeterConfigEntity config = map.get(meter.getIdCompteur());
            if (config.getIdCompteurPrincipal() == null && config.getIdCompteurRedondant() != null) {
                bruteAcquisitionRepository.updateSource("Re", presence,
                        list.get(i).getId());
            } else if (config.getIdCompteurPrincipal() != null && config.getIdCompteurRedondant() == null) {
                bruteAcquisitionRepository.updateSource("Pr", presence,
                        list.get(i).getId());
            }
        }
    }

    public String getPresenceValue(BruteAcquisitionEntity compteurCourant, Double dataAPlus, Double dataAMoins,
            List<BruteAcquisitionEntity> listCompteur) throws SQLException {
        String presence = null;

        int index = -1;
        for (int i = 0; i < listCompteur.size(); i++) {
            if (compteurCourant.getId() == listCompteur.get(i).getId()) {
                index = i;
                break;
            }
        }

        if ((dataAPlus == null || dataAPlus == 0.0) && (dataAMoins == null || dataAMoins == 0.0)) {
            // La donnees est absente simultanement
            // sur DataAplus et DataAmoins
            int tmp = 0;
            for (int i = 0; i < 6; i++) {

                if (index > 0 && (i + 1) <= index) {

                    if (listCompteur.get(index - (i + 1)) != null) {
                        try {
                            boolean testEmptyOrNullAPlus = listCompteur.get(index - (i + 1)).getDataAPlus() == null
                                    || listCompteur.get(index - (i + 1)).getDataAPlus() == 0.0;
                            boolean testEmptyOrNullAMoins = listCompteur.get(index - (i + 1)).getDataAMoins() == null
                                    || listCompteur.get(index - (i + 1)).getDataAMoins() == 0.0;
                            if (testEmptyOrNullAPlus && testEmptyOrNullAMoins) {
                                tmp = i + 1;
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    break;
                }
            }
            // System.out.println("tmp "+tmp);
            if (tmp <= 5) {
                int tmpBis = 0;
                for (int i = 0; i <= 5 - tmp; i++) {
                    if (index + (i + 1) < listCompteur.size()) {
                        if (listCompteur.get(index + (i + 1)) != null) {
                            try {
                                boolean testEmptyOrNullAPlus = Double
                                        .isNaN(listCompteur.get(index + (i + 1)).getDataAPlus())
                                        || Double.isInfinite(listCompteur.get(index + (i + 1)).getDataAPlus());
                                boolean testEmptyOrNullAMoins = Double
                                        .isNaN(listCompteur.get(index + (i + 1)).getDataAMoins())
                                        || Double.isInfinite(listCompteur.get(index + (i + 1)).getDataAMoins());

                                if (testEmptyOrNullAPlus && testEmptyOrNullAMoins) {
                                    tmpBis = i + 1;
                                } else {
                                    break;
                                }
                            } catch (Exception e) {

                            }
                        }
                    } else {
                        break;
                    }
                }
                if (tmp + tmpBis + 1 <= 6) { // J'ajoute le compteur courant dans la liste
                    presence = "0";
                } else {
                    presence = "1";
                }
            } else {
                presence = "1";
            }
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) > 0 && dataAMoins != null
                && Double.compare(dataAMoins, 0.0) == 0) {
            presence = "2";
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) == 0 && dataAMoins != null
                && Double.compare(dataAMoins, 0.0) > 0) {
            presence = "2";
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) == 0 && dataAMoins != null
                && Double.compare(dataAMoins, 0.0) == 0) {

            int tmp = 0;
            for (int i = 0; i < 6; i++) {
                if (index > 0 && (i + 1) <= index) {
                    if (listCompteur.get(index - (i + 1)) != null
                            && listCompteur.get(index - (i + 1)).getDataAPlus() != null
                            && listCompteur.get(index - (i + 1)).getDataAMoins() != null
                            && listCompteur.get(index - (i + 1)).getDataAPlus() == 0.0
                            && listCompteur.get(index - (i + 1)).getDataAMoins() == 0.0) {
                        tmp = i + 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // System.out.println("tmp "+tmp);
            if (tmp <= 5) {
                int tmpBis = 0;

                for (int i = 0; i < 6 - tmp; i++) {
                    if (index + (i + 1) < listCompteur.size()) {
                        if (listCompteur.get(index + (i + 1)) != null
                                && listCompteur.get(index + (i + 1)).getDataAPlus() != 0.0
                                && listCompteur.get(index + (i + 1)).getDataAMoins() != 0.0) {
                            tmpBis = i + 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                if (tmp + tmpBis + 1 <= 6) {
                    presence = "3";
                } else {
                    presence = "4";
                }
            } else {
                presence = "4";
            }
        } else {
            presence = "5";
        }

        return presence;
    }

}