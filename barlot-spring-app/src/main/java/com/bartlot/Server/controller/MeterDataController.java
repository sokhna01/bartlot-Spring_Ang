package com.bartlot.Server.controller;

import com.bartlot.Server.entity.MeterConfigEntity;
import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.MeterConfigRepository;
import com.bartlot.Server.repository.MeterDataRepository;
import com.bartlot.Server.service.MeterDataService;
import com.bartlot.Server.service.Task1Service;
import com.bartlot.Server.service.Task2Service;
import com.bartlot.Server.service.Task4Service;
import com.bartlot.Server.service.Task7Service;
import com.bartlot.Server.service.Task3Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
// @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class MeterDataController {
    @Autowired
    private MeterDataService meterDataService;

    @Autowired
    private MeterDataRepository meterDataRepository;

    @Autowired
    private Task1Service task1Service;

    @Autowired
    private Task2Service task2Service;

    @Autowired
    private Task3Service task3Service;

    @Autowired
    private Task4Service task4Service;

    @Autowired
    private Task7Service task7Service;

    @Autowired
    private MeterConfigRepository meterConfigRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Code pour gérer le téléchargement du fichier
        return ResponseEntity.ok("upload_ok");
    }

    @PostMapping("/getlistmeterdata")
    public List<MeterDataEntity> getListMeter(@RequestParam("idcompany") String idcompany) {

        int idCompany = Integer.parseInt(idcompany);

        return meterDataService.getListMeterData(idCompany);
    }

    @PostMapping("/insert_meter_data")
    public ResponseEntity<?> insertXlsxToBD(@RequestParam("idcompany") String idcompany,
            @RequestParam("filename") String filename) {

        Map<String, String> map = new HashMap<String, String>();
        int idCompany = Integer.parseInt(idcompany);

        Long count = meterDataRepository.countByIdCompany(idCompany);

        if (count == 0) {

            task1Service.readXLSXFile(filename, idCompany);
        } else {
            System.out.println("testtt");
            task1Service.readXLSXFileForNextTask1(filename, idCompany);
        }

        map.put("msg", "insert_ok");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/tache2")
    public ResponseEntity<?> updateMissingField(@RequestParam("idcompany") String idcompany) {

        Map<String, String> map = new HashMap<String, String>();
        int idCompany = Integer.parseInt(idcompany);
        task2Service.readXLSXFileForTask2(idCompany);
        map.put("msg", "update_ok");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/tache3")
    public ResponseEntity<?> updateSourceandPresence(@RequestParam("idcompany") String idcompany) {

        int idCompany = Integer.parseInt(idcompany);
        Map<String, String> map = new HashMap<String, String>();

        task3Service.updateSource(idCompany);

        map.put("msg", "update_ok");

        return ResponseEntity.ok(map);

    }

    @PostMapping("/tache4")
    public ResponseEntity<?> updateQualite(@RequestParam("idcompany") String idcompany) {

        int idCompany = Integer.parseInt(idcompany);
        Map<String, String> map = new HashMap<String, String>();

        task4Service.executeTask4(idCompany);

        map.put("msg", "update_ok");
        return ResponseEntity.ok(map);

    }

    @PostMapping("/getlistmeterdatareporting")
    public HashMap<String, List<MeterDataEntity>> getListMeterDataByType(@RequestParam("idcompany") String idcompany) {

        int idCompany = Integer.parseInt(idcompany);
        HashMap<String, List<MeterDataEntity>> list = new HashMap<String, List<MeterDataEntity>>();

        list = meterDataService.getListMeterDataByType(idCompany);

        return list;

    }

    @GetMapping("/getlistmeterconfig")
    public HashMap<String, MeterConfigEntity> getListMeterConfig() {
        HashMap<String, MeterConfigEntity> list = new HashMap<String, MeterConfigEntity>();
        List<MeterConfigEntity> meterConfigs = meterConfigRepository.findAll();
        for (MeterConfigEntity meterConfig : meterConfigs) {
            list.put(meterConfig.getIdCompteur(), meterConfig);
        }
        return list;
    }

    @GetMapping("/getid")
    public List<Map<String, Object>> findAllClientSitePointComptage() {
        return meterDataService.findAllClientSitePointComptage();
    }

    @GetMapping("/selectListData")
    public ResponseEntity<List<Map<String, Object>>> getSelectListData() {
        List<Map<String, Object>> selectListData = meterDataService.findAllClientSitePointComptage();
        return ResponseEntity.ok().body(selectListData);
    }

    @PostMapping("/tache7")
    public ResponseEntity<String> insertMDIntoWorkTable() {
        task7Service.insertMDIntoWorkTable();
        return ResponseEntity.ok("Données insérées dans la table de travail.");
    }
}