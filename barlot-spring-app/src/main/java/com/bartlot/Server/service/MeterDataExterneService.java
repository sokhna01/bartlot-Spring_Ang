package com.bartlot.Server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.MeterDataExterneEntity;
import com.bartlot.Server.repository.MeterDataExterneRepository;

@Service
public class MeterDataExterneService {
    @Autowired
    private MeterDataExterneRepository meterDataExterneRepository;

    public String insertRow(MeterDataExterneEntity meterDataExterne, String idClient) {
        String response = "notOk";
        try {
            meterDataExterne.setIdClient(idClient);
            meterDataExterneRepository.save(meterDataExterne);
            response = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // public List<Object> retrieveData(String idClient) {
    // try {
    // List<MeterDataExterneEntity> data =
    // meterDataExterneRepository.findAllSourceExterne(idClient);
    // return new ArrayList<>(data);
    // } catch (Exception e) {
    // return Collections.emptyList();
    // }
    // }

}
