package com.bartlot.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.model.ClientSitePointAssociation;
import com.bartlot.Server.repository.BruteAcquisitionRepository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import com.bartlot.Server.config.Common;

@Service
public class BruteAcquisitionService {

    @Autowired
    private BruteAcquisitionRepository bruteAcquisitionEntity;

    public List<BruteAcquisitionEntity> getListMeterData() {

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = bruteAcquisitionEntity.findLastRecentRowDateWithException();

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

        return bruteAcquisitionEntity.findByHorodatageBetweenOrderByHorodatageAscWithException(begin_date, end_date);
    }

    public String insertRow(BruteAcquisitionEntity meterData) {
        String response = "notOk";
        try {
            bruteAcquisitionEntity.save(meterData);
            response = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String insertRow(BruteAcquisitionEntity meterData, String idClient) {
        String response = "notOk";
        try {
            meterData.setIdClient(idClient);
            bruteAcquisitionEntity.save(meterData);
            response = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public HashMap<String, List<BruteAcquisitionEntity>> getListMeterDataByType() {

        HashMap<String, List<BruteAcquisitionEntity>> map = new HashMap<String, List<BruteAcquisitionEntity>>();
        List<BruteAcquisitionEntity> listCompteurPrincipal = new ArrayList<BruteAcquisitionEntity>();
        List<BruteAcquisitionEntity> listCompteurRedondant = new ArrayList<BruteAcquisitionEntity>();
        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = bruteAcquisitionEntity.findLastRecentRowDateWithException();
        // System.out.println("babs "+tsp);
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
        // LocalDate nowtsp = LocalDate.now();
        LocalDate oldDate = nowtsp.minusDays(Common.maxDayCompteur);
        java.util.Date beginDate = Date.from(oldDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date endDate = Date.from(nowtsp.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, 1); // Add one day because compare is based to timestamp. Not the same with date
        Date begin_date = new Date(beginDate.getTime());
        Date end_date = new Date(c.getTimeInMillis());

        List<BruteAcquisitionEntity> meterDataList = bruteAcquisitionEntity
                .findByHorodatageBetweenOrderByHorodatageAsc(begin_date, end_date);

        for (BruteAcquisitionEntity meterData : meterDataList) {

            if (meterData.getIdCompteur().equals("CPT-P")) {
                listCompteurPrincipal.add(meterData);
            } else if (meterData.getIdCompteur().equals("CPT-R")) {
                listCompteurRedondant.add(meterData);
            }
        }

        map.put("CPT-P", listCompteurPrincipal);
        map.put("CPT-R", listCompteurRedondant);

        return map;
    }

    /*
     * Permet de recuperer tous les
     * compteurs pour la tache 1
     * lors des 35 derniers jours de la bd
     */
    public HashMap<String, BruteAcquisitionEntity> getListMeterDataForTask1() {

        HashMap<String, BruteAcquisitionEntity> list = new HashMap<String, BruteAcquisitionEntity>();

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = bruteAcquisitionEntity.findLastRecentRowDateWithException();
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
        for (LocalDate dateTime = oldDate; dateTime.isBefore(nowtsp)
                || dateTime.isEqual(nowtsp); dateTime = dateTime.plusDays(1)) {
            LocalDate onDay = dateTime.plusDays(1);
            java.util.Date beginDate = Date.from(dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date onDaydate = Date.from(onDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar c = Calendar.getInstance();
            c.setTime(onDaydate);
            // Add one day because compare is based to timestamp. Not the same with date
            c.add(Calendar.DATE, 1);
            Date begin_date = new Date(beginDate.getTime());
            Date end_date = new Date(c.getTimeInMillis());

            List<BruteAcquisitionEntity> meterDataList = bruteAcquisitionEntity
                    .findByHorodatageBetweenOrderByHorodatageAsc(begin_date, end_date);

            for (BruteAcquisitionEntity meterData : meterDataList) {

                String strTsp = "" + meterData.getHorodatage();
                list.put(meterData.getIdCompteur() + "-" + strTsp.substring(0, 10),
                        meterData);
            }

        }

        return list;
    }

    /*
     * Permet de recuperer tous les
     * compteurs ayant un champs vide
     * lors des 35 derniers jours de la
     * BD
     */
    public HashMap<String, BruteAcquisitionEntity> getListAbsenceMeterData() {

        HashMap<String, BruteAcquisitionEntity> list = new HashMap<String, BruteAcquisitionEntity>();
        // DataBase db = new DataBase();

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = bruteAcquisitionEntity.findLastRecentRowDateWithException();

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
        for (LocalDate dateTime = oldDate; dateTime.isBefore(nowtsp)
                || dateTime.isEqual(nowtsp); dateTime = dateTime.plusDays(1)) {
            LocalDate onDay = dateTime.plusDays(1);
            java.util.Date beginDate = Date.from(dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date onDaydate = Date.from(onDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar c = Calendar.getInstance();
            c.setTime(onDaydate);
            // Add one day because compare is based to timestamp. Not the same with date
            c.add(Calendar.DATE, 1);
            Date begin_date = new Date(beginDate.getTime());
            Date end_date = new Date(c.getTimeInMillis());

            List<BruteAcquisitionEntity> meterDataList = bruteAcquisitionEntity.findMeterDataBybetweenDate(begin_date,
                    end_date);

            for (BruteAcquisitionEntity meterData : meterDataList) {

                String strTsp = "" + meterData.getHorodatage();

                list.put(meterData.getIdCompteur() + "-" + strTsp.substring(0, 16),
                        meterData);
            }
        }

        return list;
    }

    public List<Map<String, Object>> findAllClientSitePointComptage() {
        List<Object[]> results = bruteAcquisitionEntity.findAllSiteClientAndPointDeComptage();
        Map<String, List<String>> siteMap = new HashMap<>();
        Map<String, List<String>> clientMap = new HashMap<>();
        Map<String, List<String>> pointComptageMap = new HashMap<>();

        for (Object[] result : results) {
            String client = (String) result[0];
            String site = (String) result[1];
            String pointComptage = (String) result[2];

            clientMap.computeIfAbsent(client, k -> new ArrayList<>()).add(client);

            siteMap.computeIfAbsent(site, k -> new ArrayList<>()).add(site);

            pointComptageMap.computeIfAbsent(pointComptage, k -> new ArrayList<>()).add(pointComptage);
        }
        List<Map<String, Object>> selectListData = new ArrayList<>();

        Map<String, Object> siteClientPointMap = new HashMap<>();
        if (!clientMap.isEmpty()) {
            siteClientPointMap.put("idClients", clientMap.keySet());
        }
        if (!siteMap.isEmpty()) {
            siteClientPointMap.put("idSites", siteMap.keySet());
        }
        if (!pointComptageMap.isEmpty()) {
            siteClientPointMap.put("idPointComptage", pointComptageMap.keySet());
        }
        selectListData.add(siteClientPointMap);

        return selectListData;
    }

    public List<ClientSitePointAssociation> getAllClientsWithSitesAndPoints() {
        List<Object[]> results = bruteAcquisitionEntity.findAllSiteClientAndPointDeComptage();
        Map<String, ClientSitePointAssociation> clientAssociationMap = new HashMap<>();

        for (Object[] result : results) {
            String clientId = (String) result[0];
            String siteId = (String) result[1];
            String pointId = (String) result[2];

            ClientSitePointAssociation clientAssociation = clientAssociationMap.get(clientId);
            if (clientAssociation == null) {
                clientAssociation = new ClientSitePointAssociation();
                clientAssociation.setIdClient(clientId);
                clientAssociation.setIdSites(new ArrayList<>());
                clientAssociation.setIdPoints(new ArrayList<>());
                clientAssociationMap.put(clientId, clientAssociation);
            }
            clientAssociation.getIdSites().add(siteId);
            clientAssociation.getIdPoints().add(pointId);
        }

        return new ArrayList<>(clientAssociationMap.values());
    }

    public List<BruteAcquisitionEntity> getListCompteurMD() {
        return bruteAcquisitionEntity.findBySourceIsNullAndPresenceIsNullAndQualiteIsNull();
    }

}