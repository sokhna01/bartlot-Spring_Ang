package com.bartlot.Server.service;

import com.bartlot.Server.config.Common;
import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Service
public class Task4Service {

    @Autowired
    private BruteAcquisitionRepository bruteAcquisitionRepository;

    @Autowired
    private Task7Service task7Service;

    public void executeTask4() {
        double borneSup = Common.puissanceNominale * 1.2;
        double borneInf = Common.puissanceNominale * 0.1;
        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = bruteAcquisitionRepository.findLastRecentRowDateWithException();

        if (tsp != null) {
            String strTsp = "" + tsp;
            String date = strTsp.split(" ")[0];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed;
            try {
                parsed = format.parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(parsed);
                nowtsp = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
                        c.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        LocalDate oldDate = nowtsp.minusDays(Common.maxDayCompteur);
        // System.out.println("Old date:" + oldDate);
        // System.out.println("nowtsp :" + nowtsp);
        // System.out.println("Date ancienne:" + oldDate);
        for (LocalDate dateTime = oldDate; dateTime.isBefore(nowtsp)
                || dateTime.isEqual(nowtsp); dateTime = dateTime.plusDays(1)) {
            HashMap<String, List<BruteAcquisitionEntity>> map = getListMeterDataByDate(dateTime,
                    dateTime);
            List<BruteAcquisitionEntity> listCompteursOkQualite = new ArrayList<BruteAcquisitionEntity>();
            List<BruteAcquisitionEntity> listCompteursPrincipal = new ArrayList<BruteAcquisitionEntity>();
            List<BruteAcquisitionEntity> listCompteursRedondant = new ArrayList<BruteAcquisitionEntity>();
            List<Double> listRedResult = new ArrayList<Double>();
            listCompteursPrincipal = map.get("compteurPrincipal");
            listCompteursRedondant = map.get("compteurRedondant");
            System.out.println("Taille de la liste Compteur Principal : " + listCompteursPrincipal.size());
            System.out.println("Taille de la liste Compteur Redondant : " + listCompteursRedondant.size());
            if (listCompteursPrincipal.size() >= 144 && listCompteursRedondant.size() >= 144) {
                for (int i = 0; i < 144; i++) {

                    boolean statusPi = false;
                    boolean statusPj = false;
                    double Pi = 0;
                    double Pj = 0;

                    // Partie compteur principal
                    if (Integer.parseInt(listCompteursPrincipal.get(i).getPresence()) == 2
                            || Integer.parseInt(listCompteursPrincipal.get(i).getPresence()) == 3
                            || Integer.parseInt(listCompteursPrincipal.get(i).getPresence()) == 4) {
                        Pi = calculPuissance(listCompteursPrincipal.get(i).getDataAPlus(),
                                listCompteursPrincipal.get(i).getDataAMoins());
                        if (Pi > borneInf && Pi < borneSup) {
                            statusPi = true;
                        } else {
                            bruteAcquisitionRepository.updateQualite("0",
                                    listCompteursPrincipal.get(i).getId());

                        }
                    } else {
                        bruteAcquisitionRepository.updateQualite("0",
                                listCompteursPrincipal.get(i).getId());
                    }

                    // Partie compteur redondant
                    if (Integer.parseInt(listCompteursRedondant.get(i).getPresence()) == 2
                            || Integer.parseInt(listCompteursRedondant.get(i).getPresence()) == 3
                            || Integer.parseInt(listCompteursRedondant.get(i).getPresence()) == 4) {
                        Pj = calculPuissance(listCompteursRedondant.get(i).getDataAPlus(),
                                listCompteursRedondant.get(i).getDataAMoins());
                        if (Pj > borneInf && Pj < borneSup) {
                            statusPj = true;
                        } else {
                            bruteAcquisitionRepository.updateQualite("0",
                                    listCompteursRedondant.get(i).getId());
                        }
                    } else {
                        bruteAcquisitionRepository.updateQualite("0",
                                listCompteursRedondant.get(i).getId());
                    }

                    if (statusPi && statusPj) {
                        listCompteursOkQualite.add(listCompteursPrincipal.get(i));
                        listCompteursOkQualite.add(listCompteursRedondant.get(i));
                        listRedResult.add(Math.abs(calculRedQualite(Pi, Pj)));
                    } else {
                        bruteAcquisitionRepository.updateQualite("0",
                                listCompteursPrincipal.get(i).getId());
                        bruteAcquisitionRepository.updateQualite("0",
                                listCompteursRedondant.get(i).getId());
                    }

                }
                // Fin loop 144
            } else {
                System.out.println("Bonjour");
            }

            double moyenneRed = calculMoyenneRed(listRedResult); // Calcul de la moyenne journaliere

            for (int i = 0; i < listCompteursOkQualite.size(); i++) {
                bruteAcquisitionRepository.updateQualite(calculQualiteValue(moyenneRed),
                        listCompteursOkQualite.get(i).getId());
            }

        }
        task7Service.insertMDIntoWorkTable();
    }

    public HashMap<String, List<BruteAcquisitionEntity>> getListMeterDataByDate(LocalDate beginDate,
            LocalDate endDate) {

        HashMap<String, List<BruteAcquisitionEntity>> map = new HashMap<String, List<BruteAcquisitionEntity>>();
        List<BruteAcquisitionEntity> listCompteurPrincipal = new ArrayList<BruteAcquisitionEntity>();
        List<BruteAcquisitionEntity> listCompteurSecondaire = new ArrayList<BruteAcquisitionEntity>();
        java.util.Date onbeginDate = Date.from(beginDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date onEndDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date begin_date = new Date(onbeginDate.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(onEndDate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date end_date = new Date(c.getTimeInMillis());

        List<BruteAcquisitionEntity> meterDataList = bruteAcquisitionRepository
                .findByHorodatageBetweenOrderByHorodatageAsc(begin_date, end_date);

        for (BruteAcquisitionEntity meterData : meterDataList) {

            if (meterData.getSource().equals("Pr")) {
                listCompteurPrincipal.add(meterData);
            } else if (meterData.getSource().equals("Re")) {
                listCompteurSecondaire.add(meterData);
            }
        }

        map.put("compteurPrincipal", listCompteurPrincipal);
        map.put("compteurRedondant", listCompteurSecondaire);

        return map;
    }

    public double calculPuissance(double dataAPlus, double dataAMoins) {
        double puissance = dataAPlus - dataAMoins;
        return puissance;
    }

    public double calculRedQualite(double Pi, double Pj) {
        double red = 0;
        red = (Pi - Pj) / ((Pi + Pj) / 2);
        return red;
    }

    public double calculMoyenneRed(List<Double> listRedResult) {
        double moyenneRed = 0;
        double somme = 0;
        for (int i = 0; i < listRedResult.size(); i++) {
            somme = somme + listRedResult.get(i);
        }
        moyenneRed = somme / listRedResult.size();
        return moyenneRed;
    }

    public String calculQualiteValue(double moyenneRed) {
        String qualiteValue = "0";
        if (moyenneRed > 0 && moyenneRed <= Common.SeuilRed1) {
            qualiteValue = "1";
        } else if (moyenneRed > Common.SeuilRed1 && moyenneRed <= Common.SeuilRed2) {
            qualiteValue = "2";
        } else if (moyenneRed > Common.SeuilRed2) {
            qualiteValue = "3";
        }
        return qualiteValue;
    }
}