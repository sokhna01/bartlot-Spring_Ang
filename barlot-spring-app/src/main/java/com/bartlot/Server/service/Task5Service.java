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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class Task5Service {

    @Autowired
    private MeterDataExterneService meterDataExterneService;

    @Autowired
    private MeterDataService meterDataService;

    @Autowired
    private Task3Service task3Service;

    public void readXLSXFile(MultipartFile file, String idClient, Integer idCompany) {

        try {
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            boolean isHeader = true;
            List<MeterDataEntity> listCompteur = new ArrayList<>();

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
                            meterData.setHorodatage(null);
                        } else {
                            meterData.setHorodatage(new Timestamp(cell.getDateCellValue().getTime()));
                        }
                    }
                    if (cell.getColumnIndex() == 1) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataAPlus(null);
                        } else {
                            meterData.setDataAPlus(cell.getNumericCellValue());
                        }
                    }
                    if (cell.getColumnIndex() == 2) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataAMoins(null);
                        } else {
                            meterData.setDataAMoins(cell.getNumericCellValue());

                        }
                    }
                    if (cell.getColumnIndex() == 3) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataRPlus(null);
                        } else {
                            meterData.setDataRPlus(cell.getNumericCellValue());
                        }
                    }
                    if (cell.getColumnIndex() == 4) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterData.setDataRMoins(null);
                        } else {
                            meterData.setDataRMoins(cell.getNumericCellValue());
                        }
                    }

                }

                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedTimestamp = dateFormat.format(currentTimestamp);

                meterData.setCreatedDate(Timestamp.valueOf(formattedTimestamp));

                String presence = task3Service.getPresenceValue(meterData,
                        meterData.getDataAPlus(),
                        meterData.getDataAMoins(), listCompteur);

                meterData.setPresence(presence);
                meterData.setIdClient(idClient);
                meterData.setSource("Se");
                meterData.setQualite("5");
                meterDataService.insertRow(meterData, idCompany);

            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        readXLSXFileForExt(file, idClient, idCompany);
    }

    public void readXLSXFileForExt(MultipartFile file, String idClient, Integer idCompany) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            boolean isHeader = true;
            List<MeterDataExterneEntity> listCompteur = new ArrayList<>();

            while (itr.hasNext()) {
                Row row = itr.next();
                MeterDataExterneEntity meterDataExterne = new MeterDataExterneEntity();
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
                            meterDataExterne.setHorodatage(null);
                        } else {
                            meterDataExterne.setHorodatage(new Timestamp(cell.getDateCellValue().getTime()));
                        }
                    }
                    if (cell.getColumnIndex() == 1) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterDataExterne.setDataAPlus(null);
                        } else {
                            meterDataExterne.setDataAPlus(cell.getNumericCellValue());
                        }
                    }
                    if (cell.getColumnIndex() == 2) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterDataExterne.setDataAMoins(null);
                        } else {
                            meterDataExterne.setDataAMoins(cell.getNumericCellValue());

                        }
                    }
                    if (cell.getColumnIndex() == 3) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterDataExterne.setDataRPlus(null);
                            // meterData.setDataRPlus(null);
                        } else {
                            meterDataExterne.setDataRPlus(cell.getNumericCellValue());
                        }
                    }
                    if (cell.getColumnIndex() == 4) {
                        if (cell.getCellType() == CellType.BLANK) {
                            meterDataExterne.setDataRMoins(null);
                        } else {
                            meterDataExterne.setDataRMoins(cell.getNumericCellValue());
                        }
                    }

                }
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedTimestamp = dateFormat.format(currentTimestamp);

                meterDataExterne.setCreatedDate(Timestamp.valueOf(formattedTimestamp));
                meterDataExterne.setSource("Se");
                meterDataExterne.setQualite("5");

                String presence = getPresenceValueExt(meterDataExterne,
                        meterDataExterne.getDataAPlus(),
                        meterDataExterne.getDataAMoins(), listCompteur);

                meterDataExterne.setPresence(presence);
                meterDataExterneService.insertRow(meterDataExterne, idClient);
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPresenceValueExt(MeterDataExterneEntity compteurCourant,
            Double dataAPlus, Double dataAMoins,
            List<MeterDataExterneEntity> listCompteur) throws SQLException {
        String presence = null;

        int index = -1;
        for (int i = 0; i < listCompteur.size(); i++) {
            if (compteurCourant.getId() == listCompteur.get(i).getId()) {
                index = i;
                break;
            }
        }

        if ((dataAPlus == null || dataAPlus == 0.0) && (dataAMoins == null ||
                dataAMoins == 0.0)) {
            // La donnees est absente simultanement
            // sur DataAplus et DataAmoins
            int tmp = 0;
            for (int i = 0; i < 6; i++) {

                if (index > 0 && (i + 1) <= index) {

                    if (listCompteur.get(index - (i + 1)) != null) {
                        try {
                            boolean testEmptyOrNullAPlus = listCompteur.get(index - (i +
                                    1)).getDataAPlus() == null
                                    || listCompteur.get(index - (i + 1)).getDataAPlus() == 0.0;
                            boolean testEmptyOrNullAMoins = listCompteur.get(index - (i +
                                    1)).getDataAMoins() == null
                                    || listCompteur.get(index - (i + 1)).getDataAMoins() == 0.0;
                            if (testEmptyOrNullAPlus && testEmptyOrNullAMoins) {
                                tmp = i + 1;
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    break;
                }
            }
            // System.out.println("tmp "+tmp);
            if (tmp <= 5) {
                int tmpBis = 0;
                for (int i = 0; i <= 5 - tmp; i++) {
                    if (index + (i + 1) < listCompteur.size()) {
                        if (listCompteur.get(index + (i + 1)) != null) {
                            try {
                                boolean testEmptyOrNullAPlus = Double
                                        .isNaN(listCompteur.get(index + (i + 1)).getDataAPlus())
                                        || Double.isInfinite(listCompteur.get(index + (i + 1)).getDataAPlus());
                                boolean testEmptyOrNullAMoins = Double
                                        .isNaN(listCompteur.get(index + (i + 1)).getDataAMoins())
                                        || Double.isInfinite(listCompteur.get(index + (i + 1)).getDataAMoins());

                                if (testEmptyOrNullAPlus && testEmptyOrNullAMoins) {
                                    tmpBis = i + 1;
                                } else {
                                    break;
                                }
                            } catch (Exception e) {

                            }
                        }
                    } else {
                        break;
                    }
                }
                if (tmp + tmpBis + 1 <= 6) { // J'ajoute le compteur courant dans la liste
                    presence = "0";
                } else {
                    presence = "1";
                }
            } else {
                presence = "1";
            }
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) > 0 &&
                dataAMoins != null
                && Double.compare(dataAMoins, 0.0) == 0) {
            presence = "2";
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) == 0 &&
                dataAMoins != null
                && Double.compare(dataAMoins, 0.0) > 0) {
            presence = "2";
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) == 0 &&
                dataAMoins != null
                && Double.compare(dataAMoins, 0.0) == 0) {

            int tmp = 0;
            for (int i = 0; i < 6; i++) {
                if (index > 0 && (i + 1) <= index) {
                    if (listCompteur.get(index - (i + 1)) != null
                            && listCompteur.get(index - (i + 1)).getDataAPlus() != null
                            && listCompteur.get(index - (i + 1)).getDataAMoins() != null
                            && listCompteur.get(index - (i + 1)).getDataAPlus() == 0.0
                            && listCompteur.get(index - (i + 1)).getDataAMoins() == 0.0) {
                        tmp = i + 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // System.out.println("tmp "+tmp);
            if (tmp <= 5) {
                int tmpBis = 0;

                for (int i = 0; i < 6 - tmp; i++) {
                    if (index + (i + 1) < listCompteur.size()) {
                        if (listCompteur.get(index + (i + 1)) != null
                                && listCompteur.get(index + (i + 1)).getDataAPlus() != 0.0
                                && listCompteur.get(index + (i + 1)).getDataAMoins() != 0.0) {
                            tmpBis = i + 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                if (tmp + tmpBis + 1 <= 6) {
                    presence = "3";
                } else {
                    presence = "4";
                }
            } else {
                presence = "4";
            }
        } else {
            presence = "5";
        }

        return presence;
    }
}

// DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
// String today = dateFormat1.format(new Date());

// File directory = new File(Common.meterDataPath + "/" + idClient);
// if (!directory.exists()) {
// directory.mkdirs();
// }

// String originalFileName = file.getOriginalFilename();
// String fileExtension = StringUtils.getFilenameExtension(originalFileName);

// int version = 1;
// String newFileName = "data_1_" + today + "." + fileExtension;
// File xlsxFile = new File(Common.meterDataPath + "/" + idClient, newFileName);

// while (xlsxFile.exists()) {
// version++;
// newFileName = "data_" + version + "_" + today + "." + fileExtension;
// xlsxFile = new File(Common.meterDataPath + "/" + idClient, newFileName);
// }

// file.transferTo(xlsxFile);

// XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(xlsxFile));
