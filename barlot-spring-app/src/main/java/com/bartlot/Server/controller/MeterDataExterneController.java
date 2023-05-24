package com.bartlot.Server.controller;

import com.bartlot.Server.service.Task5Service;
import com.bartlot.Server.service.Task6Service;

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

    @Autowired
    private Task6Service task6Service;

    @Autowired
    private Task5Service task5Service;

    @PostMapping("/insert_meter_data_externe")
    public ResponseEntity<?> insertXlsxToBD(@RequestParam("idClient") String strIdClient,
            @RequestParam("idCompany") Integer intIdCompany,
            @RequestParam("file") MultipartFile file) {

        Map<String, String> map = new HashMap<String, String>();
        String idClient = strIdClient;

        task5Service.readXLSXFile(file, idClient, intIdCompany);

        map.put("msg", "insert_ok");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get_xlsx_file")
    public ResponseEntity<byte[]> createXLSXFile(@RequestParam("idClient") String idClient, Integer idCompany)
            throws SQLException {
        System.out.println("Id du client" + idClient);

        task6Service.executeTask6(idClient, idCompany);
        byte[] fileContent = task6Service.generateXLSXFile(idClient);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=donnees_externes.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(fileContent);
    }

    @PostMapping("/tache6")
    public ResponseEntity<?> executeTask6(@RequestParam("idClient") String strIdClient,
            @RequestParam("idCompany") Integer intIdCompany) throws SQLException {

        Map<String, String> map = new HashMap<String, String>();
        String idClient = strIdClient;

        map.put("msg", "insert_ok");
        return ResponseEntity.ok(map);
    }

}