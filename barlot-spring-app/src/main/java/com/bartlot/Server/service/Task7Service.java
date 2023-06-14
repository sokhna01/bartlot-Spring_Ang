package com.bartlot.Server.service;

import com.bartlot.Server.entity.TravailEntity;
import com.bartlot.Server.model.ReturnObject;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.entity.MeterDataExterneEntity;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import com.bartlot.Server.repository.MeterDataExterneRepository;
import com.bartlot.Server.repository.TravailRepository;

@Service
public class Task7Service {

    @Autowired
    private TravailRepository travailRepository;

    @Autowired
    private BruteAcquisitionRepository bruteAcquisitionRepository;

    @Autowired
    private MeterDataExterneRepository meterDataExterneRepository;

    private static final String idCompteur = "CPT-P";

    public void insertMDExtIntoWorkTable(String idClient, String idSite, String idPointDeComptage) {
        ReturnObject resultObjectMDext = meterDataExterneRepository.findAllSourceExterneWithException(idClient);

        List<MeterDataExterneEntity> meterDataExtList = (List<MeterDataExterneEntity>) resultObjectMDext.getObject();

        for (MeterDataExterneEntity meterDataExt : meterDataExtList) {
            Timestamp horodatage = meterDataExt.getHorodatage();

            if (travailRepository.existsByHorodatageAndIdCompteur(horodatage, idCompteur).size() == 0) {
                System.out.println("Il n'y a rien! Mettons nos données. ");
                TravailEntity workTableEntry = new TravailEntity();

                workTableEntry.setHorodatage(meterDataExt.getHorodatage());
                workTableEntry.setIdClient(meterDataExt.getIdClient());
                workTableEntry.setIdSite(idSite);
                workTableEntry.setPointComptageId(idPointDeComptage);

                if ("2".equals(meterDataExt.getPresence())
                        && ("1".equals(meterDataExt.getQualite()) ||
                                "2".equals(meterDataExt.getQualite()))) {
                    workTableEntry.setDataAMoins(meterDataExt.getDataAMoins());
                    workTableEntry.setDataAPlus(meterDataExt.getDataAPlus());
                    workTableEntry.setDataRMoins(meterDataExt.getDataRMoins());
                    workTableEntry.setDataRPlus(meterDataExt.getDataRPlus());
                    workTableEntry.setPresence(meterDataExt.getPresence());
                    workTableEntry.setQualite(meterDataExt.getQualite());
                    workTableEntry.setSource(meterDataExt.getSource());
                    workTableEntry.setValidation("Validée");
                    workTableEntry.setAttenteAction("Non");
                } else {
                    workTableEntry.setCommentaire("Analyse attendue");
                    workTableEntry.setAttenteAction("Oui");
                }
                try {
                    travailRepository.save(workTableEntry);
                } catch (Exception e) {

                }
            } else {
                ReturnObject resultObjectWT = travailRepository
                        .existsByHorodatageAndIdCompteurWithException(horodatage, idCompteur);

                List<TravailEntity> existingEntries = (List<TravailEntity>) resultObjectWT.getObject();

                for (TravailEntity existingEntry : existingEntries) {
                    if (existingEntry.getHorodatage().equals(meterDataExt.getHorodatage())
                            && "2".equals(meterDataExt.getPresence())
                            && ("1".equals(meterDataExt.getQualite()) ||
                                    "2".equals(meterDataExt.getQualite()))) {
                        existingEntry.setCommentaire("Données disponibles sur compteur principal");
                        existingEntry.setAttenteAction("Oui");
                    }
                }
            }

        }
    }

    public void insertMDIntoWorkTable() {
        ReturnObject resultObject = bruteAcquisitionRepository.findAllByIdCompteurWithException();

        List<BruteAcquisitionEntity> meterDataList = (List<BruteAcquisitionEntity>) resultObject.getObject();

        for (BruteAcquisitionEntity meterData : meterDataList) {
            Timestamp horodatage = meterData.getHorodatage();

            if (travailRepository.existsByHorodatageAndIdCompteur(horodatage,
                    idCompteur).size() == 0) {
                System.out.println("Il n'y a rien! Mettons nos données. ");
                TravailEntity workTableEntry = new TravailEntity();

                workTableEntry.setHorodatage(meterData.getHorodatage());
                workTableEntry.setIdClient(meterData.getIdClient());
                workTableEntry.setIdSite(meterData.getIdSite());
                workTableEntry.setPointComptageId(meterData.getPointComptageId());

                if ("2".equals(meterData.getPresence())
                        && ("1".equals(meterData.getQualite()) ||
                                "2".equals(meterData.getQualite()))) {
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
                try {
                    travailRepository.save(workTableEntry);
                } catch (Exception e) {

                }
            } else {
                ReturnObject resultObjectWT = travailRepository
                        .existsByHorodatageAndIdCompteurWithException(horodatage, idCompteur);

                List<TravailEntity> existingEntries = (List<TravailEntity>) resultObjectWT.getObject();

                for (TravailEntity existingEntry : existingEntries) {
                    if (existingEntry.getHorodatage().equals(meterData.getHorodatage())
                            && "2".equals(meterData.getPresence())
                            && ("1".equals(meterData.getQualite()) ||
                                    "2".equals(meterData.getQualite()))) {
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