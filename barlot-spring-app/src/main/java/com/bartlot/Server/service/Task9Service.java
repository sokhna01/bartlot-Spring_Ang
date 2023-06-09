package com.bartlot.Server.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.InterventionEntity;
import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.model.ReturnObject;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import com.bartlot.Server.repository.InterventionRepository;

@Service
public class Task9Service {

    @Autowired
    private BruteAcquisitionRepository meterDataRepository;

    @Autowired
    private InterventionRepository intereventionRepository;

    public String updateIntervention(int id, String beginDate, String endDate, boolean annuler) {

        try {

            Timestamp beginDateTimestamp = Timestamp.valueOf(beginDate.replace("T", " ").replace(".000 00:00", ".000"));
            Timestamp endDateTimestamp = Timestamp.valueOf(endDate.replace("T", " ").replace(".000 00:00", ".000"));

            InterventionEntity interventionEntity = intereventionRepository.findById(id).orElse(null);

            interventionEntity.setStartHorodatage(beginDateTimestamp);
            interventionEntity.setEndHorodatage(endDateTimestamp);
            interventionEntity.setAnnuler(annuler);

            intereventionRepository.save(interventionEntity);

            return "insert_ok";

        } catch (Exception e) {

            return e.getMessage();
        }

    }

    public String addIntervention(String idCompteur, String beginDate, String endDate) {

        String msg = "not_ok";

        ReturnObject returnObject = meterDataRepository.findByIdCompteurWithException(idCompteur);

        if (!returnObject.getStatus().equals("ok")) {

            return returnObject.getStatus();
        }
        BruteAcquisitionEntity meterDataEntity = (BruteAcquisitionEntity) returnObject.getObject();

        if (meterDataEntity != null) {

            Timestamp beginDateTimestamp = Timestamp
                    .valueOf(beginDate.replace("T", " ").replace(".000 00:00", ".000"));
            Timestamp endDateTimestamp = Timestamp.valueOf(endDate.replace("T", " ").replace(".000 00:00", ".000"));

            InterventionEntity interventionEntity = new InterventionEntity();
            interventionEntity.setIdclient(meterDataEntity.getIdClient());
            interventionEntity.setIdsite(meterDataEntity.getIdSite());
            interventionEntity.setIdcompteur(idCompteur);
            interventionEntity.setStartHorodatage(beginDateTimestamp);
            interventionEntity.setEndHorodatage(endDateTimestamp);

            InterventionEntity interventionEntityTable = intereventionRepository
                    .findByStartHorodatageAndEndHorodatage(
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
