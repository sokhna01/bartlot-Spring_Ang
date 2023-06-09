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
<<<<<<< HEAD
            // list.put(meterConfig.getIdCompteurPrincipal(), meterConfig);
            // list.put(meterConfig.getIdCompteurRedondant(), meterConfig);
=======

            if (meterConfig.getIdCompteurPrincipal() == null &&
                    meterConfig.getIdCompteurRedondant() != null) {

                list.put(meterConfig.getIdCompteurRedondant(), meterConfig);

            } else if (meterConfig.getIdCompteurPrincipal() != null &&
                    meterConfig.getIdCompteurRedondant() == null) {

                list.put(meterConfig.getIdCompteurPrincipal(), meterConfig);

            }

>>>>>>> 2bc1e6b8ffb04029092b07da297aa581c06c5989
        }
        return list;
    }

}
