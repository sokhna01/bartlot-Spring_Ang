package com.bartlot.Server.service;

import com.bartlot.Server.config.Common;
import com.bartlot.Server.entity.MeterConfigEntity;
import com.bartlot.Server.model.ReturnObject;
import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class Task1Service {

    @Autowired
    private BruteAcquisitionRepository bruteAcquisitionRepository;

    @Autowired
    private MeterConfigService meterConfigService;

    @Autowired
    private BruteAcquisitionService bruteAcquisitionService;

    public void readXLSXFile(String fileName) {
        try {
            File file = new File(Common.meterDataPath + fileName);
            try (XSSFWorkbook wb = new XSSFWorkbook(file)) {
                XSSFSheet sheet = wb.getSheetAt(0);
                Iterator<Row> itr = sheet.iterator();

                boolean isHeader = true;
                HashMap<String, MeterConfigEntity> meterConfigMap = meterConfigService.getListMeterConfig();

                while (itr.hasNext()) {
                    Row row = itr.next();
                    BruteAcquisitionEntity meterData = new BruteAcquisitionEntity();
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
                                meterData.setHorodatage(null);
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
                    if (meterConfigMap.get(meterData.getIdCompteur()) != null) {
                        MeterConfigEntity conf = meterConfigMap.get(meterData.getIdCompteur());
                        if (conf != null && conf.getModifierLaConventionPointDeSigne()) {
                            Double dataAmoinsBis = meterData.getDataAMoins();
                            Double dataAplusBis = meterData.getDataAPlus();
                            Double dataRmoinsBis = meterData.getDataRMoins();
                            Double dataRplusBis = meterData.getDataRPlus();

                            meterData.setDataAMoins(dataAplusBis);
                            meterData.setDataAPlus(dataAmoinsBis);
                            meterData.setDataRMoins(dataRplusBis);
                            meterData.setDataRPlus(dataRmoinsBis);
                        }
                    }
                    bruteAcquisitionService.insertRow(meterData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utiliser pour inserer les prochaines
    public void readXLSXFileForNextTask1(String fileName) {
        // taches 1
        try {
            File file = new File(Common.meterDataPath + fileName);
            System.out.println("Filename" + file);
            try (XSSFWorkbook wb = new XSSFWorkbook(file)) {
                ReturnObject tspObject = bruteAcquisitionRepository.findLastRecentRowDateWithException();
                XSSFSheet sheet = wb.getSheetAt(0);
                Iterator<Row> itr = sheet.iterator();
                boolean isHeader = true;
                System.out.println("");
                Timestamp tsp = (Timestamp) tspObject.getObject();
                String now = "";
                if (tsp != null) {
                    String strTsp = "" + tsp;
                    now = strTsp.split(" ")[0];
                }

                HashMap<String, MeterConfigEntity> map = meterConfigService.getListMeterConfig();
                HashMap<String, BruteAcquisitionEntity> mapListCompteur = bruteAcquisitionService
                        .getListMeterDataForTask1();

                while (itr.hasNext()) {
                    Row row = itr.next();
                    BruteAcquisitionEntity meterData = new BruteAcquisitionEntity();
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

                    if (map.get(meterData.getIdCompteur()) != null) {
                        MeterConfigEntity conf = map.get(meterData.getIdCompteur());
                        if (conf != null && conf.getModifierLaConventionPointDeSigne()) {

                            Double dataAmoinsBis = meterData.getDataAMoins();
                            Double dataAplusBis = meterData.getDataAPlus();
                            Double dataRmoinsBis = meterData.getDataRMoins();
                            Double dataRplusBis = meterData.getDataRPlus();

                            meterData.setDataAMoins(dataAplusBis);
                            meterData.setDataAPlus(dataAmoinsBis);
                            meterData.setDataRMoins(dataRplusBis);
                            meterData.setDataRPlus(dataRmoinsBis);
                        }
                    }
                    meterData.setSource("");
                    meterData.setPresence("");

                    String strTsp = "" + meterData.getHorodatage();
                    String dateTsp = strTsp.split(" ")[0];
                    Date dateMeter = new Date(meterData.getHorodatage().getTime());

                    if (tsp != null) {

                        Date nowDate = new Date(tsp.getTime());
                        if (dateTsp.equalsIgnoreCase(now)) {
                            String idRow = meterData.getIdCompteur() + "-" + dateTsp;
                            BruteAcquisitionEntity meterMap = mapListCompteur.get(idRow);
                            meterData.setId(meterMap.getId());
                            bruteAcquisitionRepository.updateMissingData(
                                    meterMap.getDataAPlus(),
                                    meterMap.getDataAMoins(),
                                    meterMap.getDataRPlus(),
                                    meterMap.getDataRMoins(),
                                    meterData.getId());
                        } else if (dateMeter.after(nowDate)) {
                            bruteAcquisitionService.insertRow(meterData);
                        }

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}