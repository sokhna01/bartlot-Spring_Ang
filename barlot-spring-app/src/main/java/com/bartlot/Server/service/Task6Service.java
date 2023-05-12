package com.bartlot.Server.service;

import com.bartlot.Server.entity.MeterDataExterneEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class Task6Service {

    @Autowired
    private MeterDataExterneService meterDataExterneService;

    public byte[] generateXLSXFile(String idClient) {
        List<MeterDataExterneEntity> listSourceExterne = meterDataExterneService.retrieveData(idClient);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sheet1");

            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Horodatage");
            headerRow.createCell(1).setCellValue("Data A+");
            headerRow.createCell(2).setCellValue("Data A-");
            headerRow.createCell(3).setCellValue("Data R+");
            headerRow.createCell(4).setCellValue("Data R-");
            headerRow.createCell(5).setCellValue("Source");
            headerRow.createCell(6).setCellValue("Presence");
            headerRow.createCell(7).setCellValue("Qualité");

            int rowIndex = 1;
            for (MeterDataExterneEntity entity : listSourceExterne) {
                XSSFRow sheetRow = sheet.createRow(rowIndex++);
                sheetRow.createCell(0).setCellValue(entity.getHorodatage());

                Double dataAPlus = entity.getDataAPlus();
                Cell cellAPlus = sheetRow.createCell(1);
                if (dataAPlus != null) {
                    cellAPlus.setCellValue(dataAPlus);
                } else {
                    cellAPlus.setCellType(CellType.BLANK);
                }

                Double dataAMoins = entity.getDataAMoins();
                Cell cellAMoins = sheetRow.createCell(2);
                if (dataAMoins != null) {
                    cellAMoins.setCellValue(dataAMoins);
                } else {
                    cellAMoins.setCellType(CellType.BLANK);
                }

                Double dataRPlus = entity.getDataRPlus();
                Cell cellRPlus = sheetRow.createCell(3);
                if (dataRPlus != null) {
                    cellRPlus.setCellValue(dataRPlus);
                } else {
                    cellRPlus.setCellType(CellType.BLANK);
                }

                Double dataRMoins = entity.getDataRMoins();
                Cell cellRMoins = sheetRow.createCell(4);
                if (dataRMoins != null) {
                    cellRMoins.setCellValue(dataRMoins);
                } else {
                    cellRMoins.setCellType(CellType.BLANK);
                }

                sheetRow.createCell(5).setCellValue(entity.getSource());
                sheetRow.createCell(6).setCellValue(entity.getPresence());
                sheetRow.createCell(7).setCellValue(entity.getQualite());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            byte[] fileContent = outputStream.toByteArray();
            outputStream.close();

            return fileContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}