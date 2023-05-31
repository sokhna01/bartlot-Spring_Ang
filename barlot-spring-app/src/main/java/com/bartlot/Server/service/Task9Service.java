package com.bartlot.Server.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.InterventionEntity;
import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.IntereventionRepository;
import com.bartlot.Server.repository.MeterDataRepository;

@Service
public class Task9Service {

    @Autowired
    private MeterDataRepository meterDataRepository;

    @Autowired
    private IntereventionRepository intereventionRepository;

    public String updateIntervention(int id,
            String beginDate, String endDate, boolean annuler) {

        Timestamp beginDateTimestamp = Timestamp.valueOf(beginDate.replace("T", " ").replace(".000 00:00", ".000"));
        Timestamp endDateTimestamp = Timestamp.valueOf(endDate.replace("T", " ").replace(".000 00:00", ".000"));
        InterventionEntity interventionEntity = intereventionRepository.findById(id).orElse(null);
        interventionEntity.setStartHorodatage(beginDateTimestamp);
        interventionEntity.setEndHorodatage(endDateTimestamp);
        interventionEntity.setAnnuler(annuler);
        intereventionRepository.save(interventionEntity);

        return "insert_ok";
    }

    public String intervention(String idCompteur, String beginDate, String endDate) {

        String msg = "not_ok";

        MeterDataEntity meterData = meterDataRepository.findByIdCompteur(idCompteur);

        if (meterData != null) {

            Timestamp beginDateTimestamp = Timestamp.valueOf(beginDate.replace("T", " ").replace(".000 00:00", ".000"));
            Timestamp endDateTimestamp = Timestamp.valueOf(endDate.replace("T", " ").replace(".000 00:00", ".000"));
            InterventionEntity interventionEntity = new InterventionEntity();
            interventionEntity.setIdclient(meterData.getIdClient());
            interventionEntity.setIdsite(meterData.getIdSite());
            interventionEntity.setIdcompteur(idCompteur);
            interventionEntity.setStartHorodatage(beginDateTimestamp);
            interventionEntity.setEndHorodatage(endDateTimestamp);

            InterventionEntity interventionEntityTable = intereventionRepository.findByStartHorodatageAndEndHorodatage(
                    interventionEntity.getStartHorodatage(), interventionEntity.getEndHorodatage());

            if (interventionEntityTable != null) {

                msg = "exist";

            } else {
                intereventionRepository.save(interventionEntity);
                msg = "insert_ok";
            }

        }

        return msg;
    }

    public List<InterventionEntity> getListIntervention(String beginHorodatage, String endHorodatage) {

        Timestamp beginDateTimestamp = Timestamp
                .valueOf(beginHorodatage.replace("T", " ").replace(".000 00:00", ".000"));
        Timestamp endDateTimestamp = Timestamp.valueOf(endHorodatage.replace("T", " ").replace(".000 00:00", ".000"));

        return intereventionRepository.findByHorodatage(beginDateTimestamp,
                endDateTimestamp);

    }
}
