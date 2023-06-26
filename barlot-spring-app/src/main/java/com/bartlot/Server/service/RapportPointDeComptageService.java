package com.bartlot.Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.RapportPointDeComptageEntity;
import com.bartlot.Server.repository.RapportPointComptageRepository;

@Service
public class RapportPointDeComptageService {

    @Autowired
    private RapportPointComptageRepository rapportPointComptageRepository;

    public List<RapportPointDeComptageEntity> getListRapportPointDeComptage() {
        return rapportPointComptageRepository.findAll();
    }
}
