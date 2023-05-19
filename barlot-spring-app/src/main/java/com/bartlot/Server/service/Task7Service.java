package com.bartlot.Server.service;

import com.bartlot.Server.entity.WorkTableEntity;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.MeterDataRepository;
import com.bartlot.Server.repository.WorkTableRepository;

@Service
public class Task7Service {

    @Autowired
    private WorkTableRepository workTableRepository;

    @Autowired
    private MeterDataRepository meterDataRepository;

    private static final String idCompteur = "CPT-P";

    public void insertMDIntoWorkTable() {
        List<MeterDataEntity> meterDataList = meterDataRepository.findAllByIdCompteur();

        for (MeterDataEntity meterData : meterDataList) {
            Timestamp horodatage = meterData.getHorodatage();

            if (workTableRepository.existsByHorodatageAndIdCompteur(horodatage, idCompteur).size() == 0) {
                System.out.println("Il n'y a rien! Mettons nos données. ");
                WorkTableEntity workTableEntry = new WorkTableEntity();

                workTableEntry.setHorodatage(meterData.getHorodatage());
                workTableEntry.setIdClient(meterData.getIdClient());
                workTableEntry.setIdSite(meterData.getIdSite());
                workTableEntry.setPointComptageId(meterData.getPointComptageId());

                if ("2".equals(meterData.getPresence())
                        && ("1".equals(meterData.getQualite()) || "2".equals(meterData.getQualite()))) {
                    workTableEntry.setDataAMoins(meterData.getDataAMoins());
                    workTableEntry.setDataAPlus(meterData.getDataAPlus());
                    workTableEntry.setDataRMoins(meterData.getDataRMoins());
                    workTableEntry.setDataRPlus(meterData.getDataRPlus());
                    workTableEntry.setPresence(meterData.getPresence());
                    workTableEntry.setQualite(meterData.getQualite());
                    workTableEntry.setSource(meterData.getSource());
                    workTableEntry.setValidation("Validée");
                    workTableEntry.setAttenteAction("Non");
                } else {
                    workTableEntry.setCommentaire("Analyse attendue");
                    workTableEntry.setAttenteAction("Oui");
                }
                workTableRepository.save(workTableEntry);
            } else {
                List<WorkTableEntity> existingEntries = workTableRepository.existsByHorodatageAndIdCompteur(horodatage,
                        idCompteur);

                for (WorkTableEntity existingEntry : existingEntries) {
                    if ("2".equals(meterData.getPresence())
                            && ("1".equals(meterData.getQualite()) || "2".equals(meterData.getQualite()))) {
                        existingEntry.setCommentaire("Données disponibles sur compteur principal");
                        existingEntry.setAttenteAction("Oui");
                    }
                }
            }

        }
    }
}
// meterData.getDataAPlus() != null && meterData.getDataAMoins() != null
// && meterData.getDataRPlus() != null && meterData.getDataRMoins() != null