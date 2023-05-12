package com.bartlot.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.MeterDataRepository;

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
public class MeterDataService {
    @Autowired
    private MeterDataRepository meterDataRepository;

    public List<MeterDataEntity> getListMeterData(int idCompany) {

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = meterDataRepository.findLastRecentRowDate();

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

        return meterDataRepository.findByIdCompanyAndHorodatageBetween(idCompany, begin_date, end_date);
    }

    public String insertRow(MeterDataEntity meterData, int idCompany) {
        String response = "notOk";
        try {
            meterData.setIdCompany(idCompany);
            meterDataRepository.save(meterData);
            response = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String insertRow(MeterDataEntity meterData, String idClient) {
        String response = "notOk";
        try {
            meterData.setIdClient(idClient);
            meterDataRepository.save(meterData);
            response = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public HashMap<String, List<MeterDataEntity>> getListMeterDataByType(int idCompany) {

        HashMap<String, List<MeterDataEntity>> map = new HashMap<String, List<MeterDataEntity>>();
        List<MeterDataEntity> listCompteurPrincipal = new ArrayList<MeterDataEntity>();
        List<MeterDataEntity> listCompteurRedondant = new ArrayList<MeterDataEntity>();
        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = meterDataRepository.findLastRecentRowDate();
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

        List<MeterDataEntity> meterDataList = meterDataRepository.findByIdCompanyAndHorodatageBetween(idCompany,
                begin_date,
                end_date);

        for (MeterDataEntity meterData : meterDataList) {

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
    public HashMap<String, MeterDataEntity> getListMeterDataForTask1(int idCompany) {

        HashMap<String, MeterDataEntity> list = new HashMap<String, MeterDataEntity>();

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = meterDataRepository.findLastRecentRowDate();
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

            List<MeterDataEntity> meterDataList = meterDataRepository.findByIdCompanyAndHorodatageBetween(idCompany,
                    begin_date, end_date);

            for (MeterDataEntity meterData : meterDataList) {

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
    public HashMap<String, MeterDataEntity> getListAbsenceMeterData(int idCompany) {

        HashMap<String, MeterDataEntity> list = new HashMap<String, MeterDataEntity>();
        // DataBase db = new DataBase();

        LocalDate nowtsp = LocalDate.now();
        Timestamp tsp = meterDataRepository.findLastRecentRowDate();

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

            List<MeterDataEntity> meterDataList = meterDataRepository.findMeterDataBybetweenDate(idCompany, begin_date,
                    end_date);

            for (MeterDataEntity meterData : meterDataList) {

                String strTsp = "" + meterData.getHorodatage();

                list.put(meterData.getIdCompteur() + "-" + strTsp.substring(0, 16),
                        meterData);
            }
        }

        return list;
    }

    public List<Map<String, Object>> findAllClientSitePointComptage() {
        List<Object[]> results = meterDataRepository.findAllSiteClientAndPointDeComptage();
        List<Map<String, Object>> selectListData = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> selectListMap = new HashMap<>();
            selectListMap.put("idclient", result[0]);
            selectListMap.put("idsite", result[1]);
            selectListMap.put("idpointcomptage", result[2]);
            selectListData.add(selectListMap);
        }
        return selectListData;
    }
}