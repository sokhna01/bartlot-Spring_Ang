package com.bartlot.Server.controller;

import com.bartlot.Server.service.Task5Service;
import com.bartlot.Server.service.Task6Service;
// import com.bartlot.Server.service.Task7Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

@RestController
public class MeterDataExterneController {

    // @Autowired
    // private Task7Service task7Service;

    @Autowired
    private Task6Service task6Service;

    @Autowired
    private Task5Service task5Service;

    @PostMapping("/tache5")
    public ResponseEntity<?> insertXlsxToBD(@RequestParam("idClient") String strIdClient,
            @RequestParam("idSite") String strIdSite, @RequestParam("idPointDeComptage") String strIdPointDeComptage,
            @RequestParam("file") MultipartFile file) {

        Map<String, String> map = new HashMap<String, String>();
        String idClient = strIdClient;
        String idSite = strIdSite;
        String idPointDeComptage = strIdPointDeComptage;

        task5Service.readXLSXFile(file, idClient, idSite, idPointDeComptage);

        map.put("msg", "insert_ok");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/tache6")
    public ResponseEntity<byte[]> createXLSXFile(@RequestParam("idClient") String idClient,
            @RequestParam("idSite") String idSite,
            @RequestParam("idPointDeComptage") String idPointDeComptage)
            throws SQLException {
        System.out.println("Id du client" + idClient);

        task6Service.executeTask6(idClient, idSite, idPointDeComptage);

        // boolean insertionSuccess = false;
        // try {
        // task7Service.insertMDIntoWorkTable();
        // insertionSuccess = true;
        // } catch (Exception e) {
        // insertionSuccess = false;
        // }

        byte[] fileContent = task6Service.generateXLSXFile(idClient);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=donnees_externes.xlsx");

        // if (insertionSuccess) {
        // headers.add("update-status", "update_ok");
        // }

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(fileContent);
    }
}