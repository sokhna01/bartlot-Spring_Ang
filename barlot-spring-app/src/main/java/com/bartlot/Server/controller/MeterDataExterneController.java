package com.bartlot.Server.controller;

import com.bartlot.Server.config.Common;
// import com.bartlot.Server.service.FileStorageService;
import com.bartlot.Server.service.Task6Service;

import org.springframework.core.io.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@RestController
public class MeterDataExterneController {

    @Autowired
    private Task6Service task6Service;

    // @Autowired
    // private FileStorageService fileStorageService;

    @PostMapping("/insert_meter_data_externe")
    public ResponseEntity<?> insertXlsxToBD(@RequestParam("idClient") String strIdClient,
            @RequestParam("file") MultipartFile file) {

        Map<String, String> map = new HashMap<String, String>();
        String idClient = strIdClient;

        task6Service.readXLSXFile(file, idClient);

        map.put("msg", "insert_ok");
        return ResponseEntity.ok(map);
    }

    // @GetMapping("/create_xlsx_file")
    // public ResponseEntity<Resource> createXLSXFile(@RequestParam("idClient")
    // String idClient) {
    // task6Service.createXLSXFile(idClient);
    // File file = new File(Common.meterDataPath + "/" + idClient +
    // "/donnees_externes_"
    // + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
    // Path path = Paths.get(file.getAbsolutePath());
    // ByteArrayResource resource = null;
    // try {
    // resource = new ByteArrayResource(Files.readAllBytes(path));
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return ResponseEntity.ok()
    // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" +
    // file.getName())
    // .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
    // .contentLength(file.length())
    // .body(resource);
    // }
}