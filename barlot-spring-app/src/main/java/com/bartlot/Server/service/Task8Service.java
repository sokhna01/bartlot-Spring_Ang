package com.bartlot.Server.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.bartlot.Server.config.Common;
import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.entity.WorkTableEntity;
import com.bartlot.Server.model.PropositionModel;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import com.bartlot.Server.repository.WorkTableRepository;

@Service
public class Task8Service {

    @Autowired
    private WorkTableRepository workTableRepository;

    @Autowired
    private BruteAcquisitionService meterDataService;

    @Autowired
    private BruteAcquisitionRepository meterDataRepository;

    /*
     * 
     * firs of all
     * j'ai recuperé les donnees des 35 derniers de la table de travaille
     * 
     * appeler la fonction workTableList
     * 
     * ensuite recuperer toutes les donnees de list meter qui est
     * la table brute(35 derniers jours) ensuite filtrer les donnees
     * 
     */

    public List<PropositionModel> workTableData() {

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

        /*
         * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         * 
         * LocalDate starDate = LocalDate.now().minusDays(1);
         * LocalDate endDate = LocalDate.now();
         * 
         * String dateFormateStart = starDate.format(formatter);
         * String dateFormateEnd = endDate.format(formatter);
         * 
         * Date sqlDateStart = Date.valueOf(dateFormateStart);
         * Date sqlDateEnd = Date.valueOf(dateFormateEnd);
         */

        List<WorkTableEntity> workTableList = workTableRepository
                .findByHorodatageBetweenOrderByHoradotageAsc(begin_date, end_date);

        List<BruteAcquisitionEntity> meterdataListFull = meterDataService.getListMeterData();

        System.out.println("meter lis" + meterdataListFull);
        List<BruteAcquisitionEntity> meterdataListFilter = new ArrayList<BruteAcquisitionEntity>();

        for (BruteAcquisitionEntity meterData : meterdataListFull) {
            if (workTableList.stream()
                    .anyMatch(workEntity -> (workEntity.getIdClient().equals(meterData.getIdClient())
                            && workEntity.getPointComptageId().equals(meterData.getPointComptageId())
                            && workEntity.getHorodatage().equals(meterData.getHorodatage())
                            && meterData.getDataAPlus() != null
                            && meterData.getDataAMoins() != null
                            && meterData.getDataRPlus() != null
                            && meterData.getDataRMoins() != null))) {
                meterdataListFilter.add(meterData);
            }
        }

        /*
         * je cree une liste liste de sortie
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
        for (BruteAcquisitionEntity meterData : meterdataListFilter) {

            /*
             * intialisation j'ajoute des donnees dans l'objet
             */

            if (i == 0) {
                propositionModel.setHorodatage(meterData.getHorodatage());
                if (meterData.getSource().equals("Pr")) {

                    WorkTableEntity workTableEntity = workTableRepository
                            .findByHorodatage(meterData.getHorodatage());

                    System.out.println(workTableEntity.getCommentaire());

                    if (workTableEntity.getCommentaire().equals("Données disponibles sur compteur principal")
                            && workTableEntity.getAttenteAction().equals("oui")
                            && workTableEntity.getIdCompteur().equals(meterData.getIdCompteur())) {

                        propositionModel.setDataAPlusPr(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsPr(meterData.getDataAMoins());
                        propositionModel.setDataRPlusPr(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsPr(meterData.getDataRMoins());
                        propositionModel.setIdCompteurPr(meterData.getIdCompteur());

                    }

                } else if (meterData.getSource().equals("Re")) {

                    propositionModel.setDataAPlusRe(meterData.getDataAPlus());
                    propositionModel.setDataAMoinsRe(meterData.getDataAMoins());
                    propositionModel.setDataRPlusRe(meterData.getDataRPlus());
                    propositionModel.setDataRMoinsRe(meterData.getDataRMoins());
                    propositionModel.setIdCompteurRe(meterData.getIdCompteur());

                }

                else if (meterData.getSource().equals("Sr")) {

                    propositionModel.setDataAPlusSe(meterData.getDataAPlus());
                    propositionModel.setDataAMoinsSe(meterData.getDataAMoins());
                    propositionModel.setDataRPlusSe(meterData.getDataRPlus());
                    propositionModel.setDataRMoinsSe(meterData.getDataRMoins());
                    propositionModel.setIdCompteurSe(meterData.getIdCompteur());

                }

            } else {
                /*
                 * Ici je test si la date de l'objet courant est egal à la date
                 * de l'objet meterData si oui on rempli l'objet courant
                 */
                if (propositionModel.getHorodatage().equals(meterData.getHorodatage())) {

                    if (meterData.getSource().equals("Pr")) {

                        WorkTableEntity workTableEntity = workTableRepository
                                .findByHorodatage(meterData.getHorodatage());

                        System.out.println(workTableEntity.getCommentaire());

                        if (workTableEntity.getCommentaire().equals("Données disponibles sur compteur principal")
                                && workTableEntity.getAttenteAction().equals("oui")
                                && workTableEntity.getIdCompteur().equals(meterData.getIdCompteur())) {

                            propositionModel.setDataAPlusPr(meterData.getDataAPlus());
                            propositionModel.setDataAMoinsPr(meterData.getDataAMoins());
                            propositionModel.setDataRPlusPr(meterData.getDataRPlus());
                            propositionModel.setDataRMoinsPr(meterData.getDataRMoins());
                            propositionModel.setIdCompteurPr(meterData.getIdCompteur());

                        }

                    } else if (meterData.getSource().equals("Re")) {

                        propositionModel.setDataAPlusRe(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsRe(meterData.getDataAMoins());
                        propositionModel.setDataRPlusRe(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsRe(meterData.getDataRMoins());
                        propositionModel.setIdCompteurRe(meterData.getIdCompteur());

                    } else if (meterData.getSource().equals("Sr")) {

                        propositionModel.setDataAPlusSe(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsSe(meterData.getDataAMoins());
                        propositionModel.setDataRPlusSe(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsSe(meterData.getDataRMoins());
                        propositionModel.setIdCompteurSe(meterData.getIdCompteur());

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
                    propositionModel.setHorodatage(meterData.getHorodatage());

                    if (meterData.getSource().equals("Pr")) {

                        WorkTableEntity workTableEntity = workTableRepository
                                .findByHorodatage(meterData.getHorodatage());

                        System.out.println(workTableEntity.getCommentaire());

                        if (workTableEntity.getCommentaire().equals("Données disponibles sur compteur principal")
                                && workTableEntity.getAttenteAction().equals("oui")
                                && workTableEntity.getIdCompteur().equals(meterData.getIdCompteur())) {

                            propositionModel.setDataAPlusPr(meterData.getDataAPlus());
                            propositionModel.setDataAMoinsPr(meterData.getDataAMoins());
                            propositionModel.setDataRPlusPr(meterData.getDataRPlus());
                            propositionModel.setDataRMoinsPr(meterData.getDataRMoins());
                            propositionModel.setIdCompteurPr(meterData.getIdCompteur());

                        }
                    } else if (meterData.getSource().equals("Re")) {

                        propositionModel.setDataAPlusRe(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsRe(meterData.getDataAMoins());
                        propositionModel.setDataRPlusRe(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsRe(meterData.getDataRMoins());
                        propositionModel.setIdCompteurRe(meterData.getIdCompteur());

                    } else if (meterData.getSource().equals("Sr")) {

                        propositionModel.setDataAPlusSe(meterData.getDataAPlus());
                        propositionModel.setDataAMoinsSe(meterData.getDataAMoins());
                        propositionModel.setDataRPlusSe(meterData.getDataRPlus());
                        propositionModel.setDataRMoinsSe(meterData.getDataRMoins());
                        propositionModel.setIdCompteurSe(meterData.getIdCompteur());

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

    public List<WorkTableEntity> getListWorkTableDataAnterieur(String horodatage) {

        Timestamp timestamp = Timestamp.valueOf(horodatage.replace("T", " ").replace("00:00", ""));
        return workTableRepository.findByHorodatageNotNull(timestamp);

    }

    public String updateWorkTable(
            Timestamp horodatage, String dataAPlus,
            String dataAMoins, String dataRPlus, String dataRMoins, String source, String idCompteur,
            String commentaire) {

        workTableRepository.update(horodatage, dataAPlus, dataAMoins, dataRPlus, dataRMoins, source, idCompteur,
                commentaire);

        return "success";
    }

    public String interpolationLineaire(Timestamp timestamp, String source, String idCompteur, String commentaire) {

        Double P1APlus = 0.0;
        Double P2APlus = 0.0;

        Double P1AMoins = 0.0;
        Double P2AMoins = 0.0;

        Double P1RPlus = 0.0;
        Double P2RPlus = 0.0;

        Double P1RMoins = 0.0;
        Double P2RMoins = 0.0;

        int nPeriode = 0;
        double iPeriode = 0;
        String msg = "wrong";

        List<BruteAcquisitionEntity> listOfMeterData = meterDataRepository
                .findAll(Sort.by(Sort.Direction.ASC, "horodatage"));

        int index = -1;
        for (BruteAcquisitionEntity meterData : listOfMeterData) {
            if (meterData.getHorodatage().equals(timestamp)) {
                index = listOfMeterData.indexOf(meterData);
                break;
            }
        }

        if (index != -1) {
            /*
             * car index peut etre le dernier index de la list
             */
            if (listOfMeterData.size() >= index + 1) {

                for (int i = index + 1; i < listOfMeterData.size(); i++) {

                    if (listOfMeterData.get(i).getDataAPlus() != null &&
                            listOfMeterData.get(i).getDataAMoins() != null &&
                            listOfMeterData.get(i).getDataRPlus() != null &&
                            listOfMeterData.get(i).getDataRMoins() != null &&
                            // pour n pas depasser le tableau
                            listOfMeterData.size() >= i + 1) {

                        if (listOfMeterData.get(i + 1).getDataAPlus() != null &&
                                listOfMeterData.get(i + 1).getDataAMoins() != null &&
                                listOfMeterData.get(i + 1).getDataRPlus() != null &&
                                listOfMeterData.get(i + 1).getDataRMoins() != null) {

                            iPeriode = nPeriode = i + 1;
                            P2APlus = listOfMeterData.get(i + 1).getDataAPlus();
                            P2AMoins = listOfMeterData.get(i + 1).getDataAMoins();
                            P2RPlus = listOfMeterData.get(i + 1).getDataRPlus();
                            P2RMoins = listOfMeterData.get(i + 1).getDataRMoins();
                            break;
                        }

                    }
                }
            }

        }

        if (index != -1) {
            /*
             * index peut etre egal à 0 le premier index de l'element
             */
            if (0 <= index - 1) {

                for (int i = index - 1; i >= 0; i++) {

                    nPeriode++;

                    if (listOfMeterData.get(i).getDataAPlus() != null &&
                            listOfMeterData.get(i).getDataAMoins() != null &&
                            listOfMeterData.get(i).getDataRPlus() != null &&
                            listOfMeterData.get(i).getDataRMoins() != null &&

                            // pour ne pas depasser le tableau
                            0 <= i - 1) {

                        if (listOfMeterData.get(i - 1).getDataAPlus() != null &&
                                listOfMeterData.get(i - 1).getDataAMoins() != null &&
                                listOfMeterData.get(i - 1).getDataRPlus() != null &&
                                listOfMeterData.get(i - 1).getDataRMoins() != null) {

                            P1APlus = listOfMeterData.get(i - 1).getDataAPlus();
                            P1AMoins = listOfMeterData.get(i - 1).getDataAMoins();
                            P1RPlus = listOfMeterData.get(i - 1).getDataRPlus();
                            P1RMoins = listOfMeterData.get(i - 1).getDataRMoins();

                            break;
                        }

                    }
                }

            }

        }

        if (P1APlus != null &&
                P2APlus != null &&
                P1RPlus != null &&

                P2RPlus != null &&
                P1AMoins != null &&

                P2AMoins != null &&
                P1RMoins != null &&
                P2RMoins != null) {

            msg = "success";

            double piAPlus = puissanceMoyenne(P1APlus,
                    P2APlus, iPeriode,
                    nPeriode - 1);

            double piAMoins = puissanceMoyenne(P1AMoins,
                    P2AMoins, iPeriode,
                    nPeriode - 1);

            double piRPlus = puissanceMoyenne(P1RPlus,
                    P2RPlus, iPeriode,
                    nPeriode - 1);

            double piRmoins = puissanceMoyenne(P1RMoins,
                    P2RMoins, iPeriode,
                    nPeriode - 1);

            workTableRepository.update(timestamp, Double.toString(piAPlus), Double.toString(piAMoins),
                    Double.toString(piRPlus), Double.toString(piRmoins), source, idCompteur, commentaire);

        }

        return msg;
    }

    public double puissanceMoyenne(double p1, double p2, double i, double n) {

        return (((p2 - p1) / (n + 1)) * i) + p1;

    }

}
