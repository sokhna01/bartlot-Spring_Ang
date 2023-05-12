package com.bartlot.Server.service;

import com.bartlot.Server.config.Common;
import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.MeterDataRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class Task2Service {

    @Autowired
    private MeterDataService meterDataService;
    @Autowired
    private MeterDataRepository meterDataRepository;

    // Utiliser pour inserer les prochaines taches 1
    public void readXLSXFileForTask2(int idCompany) {
        try {
            // creating a new file
            File file = new File(Common.meterDataPath + idCompany + "/" +
                    Common.filename);
            // creating Workbook instance that refers to .xlsx file
            try (XSSFWorkbook wb = new XSSFWorkbook(file)) {
                // creating a Sheet object to retrieve object
                XSSFSheet sheet = wb.getSheetAt(0);
                // iterating over excel file
                Iterator<Row> itr = sheet.iterator();
                HashMap<String, MeterDataEntity> map = meterDataService.getListAbsenceMeterData(idCompany);
                boolean isHeader = true;
                while (itr.hasNext()) {
                    Row row = itr.next();
                    MeterDataEntity meterData = new MeterDataEntity();
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }

                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        if (cell.getColumnIndex() == 0) {
                            if (cell.getCellType() == CellType.BLANK) {

                                meterData.setIdClient("");

                            } else {
                                meterData.setIdClient(cell.getStringCellValue());

                            }
                        }
                        if (cell.getColumnIndex() == 1) {
                            if (cell.getCellType() == CellType.BLANK) {

                                meterData.setIdSite("");
                            } else {
                                meterData.setIdSite(cell.getStringCellValue());
                            }
                        }
                        if (cell.getColumnIndex() == 2) {
                            if (cell.getCellType() == CellType.BLANK) {

                                meterData.setPointComptageId("");
                            } else {
                                meterData.setPointComptageId(cell.getStringCellValue());
                            }
                        }
                        if (cell.getColumnIndex() == 3) {
                            if (cell.getCellType() == CellType.BLANK) {

                                meterData.setIdCompteur("");
                            } else {

                                meterData.setIdCompteur(cell.getStringCellValue());

                            }
                        }
                        if (cell.getColumnIndex() == 4) {
                            if (cell.getCellType() == CellType.BLANK) {
                                meterData.setHorodatage(new Timestamp(cell.getDateCellValue().getTime()));
                            } else {

                                meterData.setHorodatage(new Timestamp(cell.getDateCellValue().getTime()));
                            }
                        }
                        if (cell.getColumnIndex() == 5) {
                            if (cell.getCellType() == CellType.BLANK) {
                                meterData.setDataAPlus(null);
                            } else {
                                meterData.setDataAPlus(cell.getNumericCellValue());
                            }
                        }
                        if (cell.getColumnIndex() == 6) {
                            if (cell.getCellType() == CellType.BLANK) {
                                meterData.setDataAMoins(null);
                            } else {
                                meterData.setDataAMoins(cell.getNumericCellValue());
                            }
                        }
                        if (cell.getColumnIndex() == 7) {
                            if (cell.getCellType() == CellType.BLANK) {
                                meterData.setDataRPlus(null);
                            } else {
                                meterData.setDataRPlus(cell.getNumericCellValue());
                            }
                        }
                        if (cell.getColumnIndex() == 8) {
                            if (cell.getCellType() == CellType.BLANK) {
                                meterData.setDataRMoins(null);
                            } else {
                                meterData.setDataRMoins(cell.getNumericCellValue());
                            }
                        }

                    }

                    String strHorodatage = meterData.getHorodatage() + "";
                    String idRow = meterData.getIdCompteur() + "-" + strHorodatage.substring(0,
                            16);
                    MeterDataEntity meterMap = map.get(idRow);
                    if (meterMap != null) {

                        meterMap.setDataAPlus(meterData.getDataAPlus());
                        meterMap.setDataAMoins(meterData.getDataAMoins());
                        meterMap.setDataRPlus(meterData.getDataRPlus());
                        meterMap.setDataRMoins(meterData.getDataRMoins());
                        meterDataRepository.updateMissingData(
                                meterMap.getDataAPlus(),
                                meterMap.getDataAMoins(),
                                meterMap.getDataRPlus(),
                                meterMap.getDataRMoins(),
                                meterData.getId());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
