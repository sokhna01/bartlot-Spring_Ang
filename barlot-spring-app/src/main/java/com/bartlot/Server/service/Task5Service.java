package com.bartlot.Server.service;

import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.entity.MeterDataExterneEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;

@Service
public class Task5Service {

    @Autowired
    private MeterDataExterneService meterDataExterneService;

    @Autowired
    private MeterDataService meterDataService;

    public void readXLSXFile(MultipartFile file, String idClient, Integer idCompany) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            boolean isHeader = true;

            while (itr.hasNext()) {
                Row row = itr.next();

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                MeterDataEntity meterData = new MeterDataEntity();
                MeterDataExterneEntity meterDataExterne = new MeterDataExterneEntity();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getColumnIndex() == 0) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setHorodatage(null);
                            meterDataExterne.setHorodatage(null);
                        } else {
                            Timestamp horodatage = new Timestamp(cell.getDateCellValue().getTime());
                            meterData.setHorodatage(horodatage);
                            meterDataExterne.setHorodatage(horodatage);
                        }
                    } else if (cell.getColumnIndex() == 1) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataAPlus(null);
                            meterDataExterne.setDataAPlus(null);
                        } else {
                            double dataAPlus = cell.getNumericCellValue();
                            meterData.setDataAPlus(dataAPlus);
                            meterDataExterne.setDataAPlus(dataAPlus);
                        }
                    } else if (cell.getColumnIndex() == 2) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataAMoins(null);
                            meterDataExterne.setDataAMoins(null);
                        } else {
                            double dataAMoins = cell.getNumericCellValue();
                            meterData.setDataAMoins(dataAMoins);
                            meterDataExterne.setDataAMoins(dataAMoins);
                        }
                    } else if (cell.getColumnIndex() == 3) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataRPlus(null);
                            meterDataExterne.setDataRPlus(null);
                        } else {
                            double dataRPlus = cell.getNumericCellValue();
                            meterData.setDataRPlus(dataRPlus);
                            meterDataExterne.setDataRPlus(dataRPlus);
                        }
                    } else if (cell.getColumnIndex() == 4) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataRMoins(null);
                            meterDataExterne.setDataRMoins(null);
                        } else {
                            double dataRMoins = cell.getNumericCellValue();
                            meterData.setDataRMoins(dataRMoins);
                            meterDataExterne.setDataRMoins(dataRMoins);
                        }
                    }
                }

                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                String formattedTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTimestamp);
                meterData.setIdClient(idClient);
                meterDataService.insertRow(meterData);

                meterDataExterne.setCreatedDate(Timestamp.valueOf(formattedTimestamp));
                meterDataExterne.setIdClient(idClient);
                meterDataExterneService.insertRow(meterDataExterne, idClient);
            }

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
