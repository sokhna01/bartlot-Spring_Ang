package com.bartlot.Server.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.MeterConfigEntity;
import com.bartlot.Server.repository.MeterConfigRepository;

@Service
public class MeterConfigService {

    @Autowired
    private MeterConfigRepository meterConfigRepository;

    public HashMap<String, MeterConfigEntity> getListMeterConfig() {
        HashMap<String, MeterConfigEntity> list = new HashMap<String, MeterConfigEntity>();
        List<MeterConfigEntity> meterConfigs = meterConfigRepository.findAll();
        for (MeterConfigEntity meterConfig : meterConfigs) {

            list.put(meterConfig.getIdPointComptage(), meterConfig);

        }
        return list;
    }

}
