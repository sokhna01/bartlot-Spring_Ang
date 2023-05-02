package com.bartlot.Server.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.config.Common;
import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.entity.WorkTableEntity;
import com.bartlot.Server.model.PropositionModel;
import com.bartlot.Server.repository.WorkTableRepository;

@Service
public class WorkTableService {

    @Autowired
    private WorkTableRepository workTableRepository;

    @Autowired
    private MeterDataService meterDataService;

    /*
     * 
     * firs of all
     * j'ai recuperé la date des 35 derniers jours de la table de travail
     * 
     * appeler la fonction workTableList
     * 
     * ensuite recuperer toutes les donnees de list meter qui est
     * la table brute ensuite filtrer les donnees
     * 
     */

    public List<PropositionModel> workTableData(int idCompany) {

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = workTableRepository.findLastRecentRowDate();

        if (tsp != null) {
            String strTsp = "" + tsp;
            String date = strTsp.split(" ")[0];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed;
            try {
                parsed = format.parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(parsed);
                nowtsp = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {

                e.printStackTrace();
            }

        }

        LocalDate oldDate = nowtsp.minusDays(Common.maxDayCompteur);
        java.util.Date beginDate = Date.from(oldDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date endDate = Date.from(nowtsp.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, 1); // Add one day because compare is based to timestamp. Not the same with date
        Date begin_date = new Date(beginDate.getTime());
        Date end_date = new Date(c.getTimeInMillis());

        List<WorkTableEntity> workTableList = workTableRepository
                .findByHorodatageBetweenOrderByHoradotageAsc(begin_date, end_date);

        List<MeterDataEntity> meterdataListFull = meterDataService.getListMeterData(idCompany);

        List<MeterDataEntity> meterdataListFilter = new ArrayList<MeterDataEntity>();

        for (MeterDataEntity meterData : meterdataListFull) {
            if (workTableList.stream()
                    .anyMatch(workEntity -> (workEntity.getIdClient().equals(meterData.getIdClient())
                            && workEntity.getPointComptageId().equals(meterData.getPointComptageId())
                            && workEntity.getHorodatage().equals(meterData.getHorodatage())
                            && !meterData.getDataAPlus().isEmpty()
                            && meterData.getDataAPlus() != null
                            && !meterData.getDataAMoins().isEmpty()
                            && meterData.getDataAMoins() != null
                            && !meterData.getDataRPlus().isEmpty()
                            && meterData.getDataRPlus() != null
                            && !meterData.getDataRMoins().isEmpty()
                            && meterData.getDataRMoins() != null))) {
                meterdataListFilter.add(meterData);
            }
        }

        /*
         * je cree une liste de sortie dont nous allons envoyé coté client
         * j'ai cree la variable i pour connaitre si i est egal ou pas la taille du
         * tableau
         * pour chaque iteration j'incremente i puis je test si il est egal ou pas
         * a la taille de la liste si oui j'ajoute l'objet courant à la liste
         */

        List<PropositionModel> listProposition = new ArrayList<PropositionModel>();
        PropositionModel propositionModel = new PropositionModel();
        int length = meterdataListFilter.size();
        int i = 0;
        System.out.println("meter filter" + meterdataListFilter);
        for (MeterDataEntity meterData : meterdataListFilter) {

            /*
             * intialisation j'ajoute des donnees dans l'objet
             */

            if (i == 0) {
                propositionModel.setHorodotage(meterData.getHorodatage());
                if (meterData.getSource().equals("Pr")) {

                    propositionModel.setDataAPlusPr(meterData.getDataAPlus());
                    propositionModel.setDataAMoinsPr(meterData.getDataAMoins());
                    propositionModel.setDataRPlusPr(meterData.getDataRPlus());
                    propositionModel.setDataRMoinsPr(meterData.getDataRMoins());
                    propositionModel.setIdCompteurPr(meterData.getIdCompteur());

                } else if (meterData.getSource().equals("Re")) {

                    propositionModel.setDataAPlusRe(meterData.getDataAPlus());
                    propositionModel.setDataAMoinsRe(meterData.getDataAMoins());
                    propositionModel.setDataRPlusRe(meterData.getDataRPlus());
                    propositionModel.setDataRMoinsRe(meterData.getDataRMoins());
                    propositionModel.setIdCompteurRe(meterData.getIdCompteur());

                }

            } else {
                /*
                 * Ici je test si la date de l'objet courant est egal à la date
                 * de l'objet meterData si oui on rempli l'objet courant
                 */
                if (propositionModel.getHorodotage().equals(meterData.getHorodatage())) {

                    if (meterData.getSource().equals("Pr")) {

                        propositionModel.setDataAPlusPr(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsPr(meterData.getDataAMoins());
                        propositionModel.setDataRPlusPr(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsPr(meterData.getDataRMoins());
                        propositionModel.setIdCompteurPr(meterData.getIdCompteur());

                    } else if (meterData.getSource().equals("Re")) {

                        propositionModel.setDataAPlusRe(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsRe(meterData.getDataAMoins());
                        propositionModel.setDataRPlusRe(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsRe(meterData.getDataRMoins());
                        propositionModel.setIdCompteurRe(meterData.getIdCompteur());

                    }

                } else {

                    /*
                     * sinon cela veut dire qu'on a une nouvelle date
                     * donc on insert d'abord l'objet courant dans la liste
                     * puis la variable p fait reference à un nouvel objet
                     * car on na fait un new
                     * et on rempli le nouvel objet
                     */

                    listProposition.add(propositionModel);
                    propositionModel = new PropositionModel();
                    propositionModel.setHorodotage(meterData.getHorodatage());

                    if (meterData.getSource().equals("Pr")) {

                        propositionModel.setDataAPlusPr(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsPr(meterData.getDataAMoins());
                        propositionModel.setDataRPlusPr(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsPr(meterData.getDataRMoins());
                        propositionModel.setIdCompteurPr(meterData.getIdCompteur());

                    } else if (meterData.getSource().equals("Re")) {

                        propositionModel.setDataAPlusRe(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsRe(meterData.getDataAMoins());
                        propositionModel.setDataRPlusRe(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsRe(meterData.getDataRMoins());
                        propositionModel.setIdCompteurRe(meterData.getIdCompteur());

                    }

                }
            }

            i++;
            if (i == length) {
                listProposition.add(propositionModel);
            }

        }

        return listProposition;
    }

    public double puissanceMoyenne(double p1, double p2, double i, double n) {

        return (((p2 - p1) / ((n * i) + 1)) * i) + p1;

    }

}
