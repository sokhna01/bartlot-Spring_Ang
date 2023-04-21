package com.bartlot.Server.service;

import com.bartlot.Server.entity.MeterConfigEntity;
import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.MeterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Task3Service {

    @Autowired
    private MeterDataService meterDataService;
    @Autowired
    private MeterConfigService meterConfigService;

    @Autowired
    private MeterDataRepository meterDataRepository;


    public void updateSource(int idCompany) {
        List<MeterDataEntity> list = new ArrayList<MeterDataEntity>();
        list = meterDataService.getListMeterData(idCompany);
        String presence = "";
        HashMap<String, MeterConfigEntity> map = meterConfigService.getListMeterConfig();
        HashMap<String, List<MeterDataEntity>> listAllCompteur = meterDataService.getListMeterDataByType(idCompany);
        for (int i = 0; i < list.size(); i++) {
            MeterDataEntity meter = list.get(i);
            List<MeterDataEntity> listCompteur = listAllCompteur.get(meter.getIdCompteur());
            try {
                presence = getPresenceValue(meter, "" + meter.getDataAPlus(), "" +
                        meter.getDataAMoins(), listCompteur);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MeterConfigEntity config = map.get(meter.getIdCompteur());
            if (config.getType() != null) {
                meterDataRepository.updateSource(config.getType(), presence,
                        list.get(i).getId());
            } else {
                System.out.println("aze");
            }
        }
    }

    public String getPresenceValue(MeterDataEntity compteurCourant, String dataAPlus, String dataAMoins,
                                   List<MeterDataEntity> listCompteur) throws SQLException {
        String presence = null;

        int index = -1;
        for (int i = 0; i < listCompteur.size(); i++) {
            if (compteurCourant.getId() == listCompteur.get(i).getId()) {
                index = i;
                break;
            }
        }

        if ((dataAPlus.isEmpty() || dataAPlus.trim().equals("null"))
                && (dataAMoins.isEmpty() || dataAMoins.trim().equals("null"))) {
            // La donnees est absente simultanement
            // sur DataAplus et DataAmoins
            int tmp = 0;
            for (int i = 0; i < 6; i++) {

                if (index > 0 && (i + 1) <= index) {

                    if (listCompteur.get(index - (i + 1)) != null) {
                        try {
                            boolean testEmptyOrNullAPlus = listCompteur.get(index - (i + 1)).getDataAPlus() == null
                                    || listCompteur.get(index - (i + 1)).getDataAPlus().isEmpty()
                                    || listCompteur.get(index - (i + 1)).getDataAPlus().trim().equals("null");
                            boolean testEmptyOrNullAMoins = listCompteur.get(index - (i + 1)).getDataAMoins() == null
                                    || listCompteur.get(index - (i + 1)).getDataAMoins().isEmpty()
                                    || listCompteur.get(index - (i + 1)).getDataAMoins().trim().equals("null");
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
                                boolean testEmptyOrNullAPlus = listCompteur.get(index + (i + 1)).getDataAPlus() == null
                                        || listCompteur.get(index + (i + 1)).getDataAPlus().isEmpty()
                                        || listCompteur.get(index + (i + 1)).getDataAPlus().trim().equals("null");
                                boolean testEmptyOrNullAMoins = listCompteur.get(index + (i + 1))
                                        .getDataAMoins() == null
                                        || listCompteur.get(index + (i + 1)).getDataAMoins().isEmpty()
                                        || listCompteur.get(index + (i + 1)).getDataAMoins().trim().equals("null");
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
        } else if (dataAPlus != null && !dataAPlus.isEmpty() && !dataAPlus.trim().equals("null")
                && Double.parseDouble(dataAPlus.trim()) > 0 && dataAMoins != null && !dataAMoins.isEmpty()
                && !dataAMoins.trim().equals("null") && Double.parseDouble(dataAMoins.trim()) == 0) {
            presence = "2";
        } else if (dataAPlus != null && !dataAPlus.isEmpty() && !dataAPlus.trim().equals("null")
                && Double.parseDouble(dataAPlus.trim()) == 0 && dataAMoins != null && !dataAMoins.isEmpty()
                && !dataAMoins.trim().equals("null") && Double.parseDouble(dataAMoins.trim()) > 0) {
            presence = "2";
        } else if (dataAPlus != null && !dataAPlus.isEmpty() && !dataAPlus.trim().equals("null")
                && Double.parseDouble(dataAPlus.trim()) == 0 && dataAMoins != null && !dataAMoins.isEmpty()
                && !dataAMoins.trim().equals("null") && Double.parseDouble(dataAMoins.trim()) == 0) {
            int tmp = 0;
            for (int i = 0; i < 6; i++) {
                if (index > 0 && (i + 1) <= index) {
                    if (listCompteur.get(index - (i + 1)) != null
                            && !listCompteur.get(index - (i + 1)).getDataAPlus().isEmpty()
                            && Double.parseDouble(listCompteur.get(index - (i + 1)).getDataAPlus().trim()) == 0
                            && !listCompteur.get(index - (i + 1)).getDataAMoins().isEmpty()
                            && Double.parseDouble(listCompteur.get(index - (i + 1)).getDataAMoins().trim()) == 0) {
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
                                && !listCompteur.get(index + (i + 1)).getDataAPlus().isEmpty()
                                && Double.parseDouble(listCompteur.get(index + (i + 1)).getDataAPlus().trim()) == 0
                                && !listCompteur.get(index + (i + 1)).getDataAMoins().isEmpty()
                                && Double.parseDouble(listCompteur.get(index + (i + 1)).getDataAMoins().trim()) == 0) {
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
