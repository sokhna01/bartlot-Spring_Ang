package com.bartlot.Server.service;

import com.bartlot.Server.config.Common;
import com.bartlot.Server.entity.MeterDataExterneEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class Task6Service {

    @Autowired
    private MeterDataExterneService meterDataExterneService;

    public void readXLSXFile(MultipartFile file, String idClient) {
        try {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String today = dateFormat1.format(new Date());

            File directory = new File(Common.meterDataPath + "/" + idClient);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);

            int version = 1;
            String newFileName = "data_1_" + today + "." + fileExtension;
            File xlsxFile = new File(Common.meterDataPath + "/" + idClient, newFileName);

            while (xlsxFile.exists()) {
                version++;
                newFileName = "data_" + version + "_" + today + "." + fileExtension;
                xlsxFile = new File(Common.meterDataPath + "/" + idClient, newFileName);
            }

            file.transferTo(xlsxFile);

            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(xlsxFile));

            // XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            boolean isHeader = true;
            List<MeterDataExterneEntity> listCompteur = new ArrayList<>();
            while (itr.hasNext()) {
                Row row = itr.next();
                MeterDataExterneEntity meterDataExterne = new MeterDataExterneEntity();
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

                String presence = getPresenceValue(meterDataExterne, meterDataExterne.getDataAPlus(),
                        meterDataExterne.getDataAMoins(), listCompteur);
                meterDataExterne.setPresence(presence);
                meterDataExterneService.insertRow(meterDataExterne, idClient);
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPresenceValue(MeterDataExterneEntity compteurCourant, Double dataAPlus, Double dataAMoins,
            List<MeterDataExterneEntity> listCompteur) throws SQLException {
        String presence = null;

        int index = -1;
        for (int i = 0; i < listCompteur.size(); i++) {
            if (compteurCourant.getId() == listCompteur.get(i).getId()) {
                index = i;
                break;
            }
        }

        if ((dataAPlus == null || dataAPlus == 0.0) && (dataAMoins == null || dataAMoins == 0.0)) {
            // La donnees est absente simultanement
            // sur DataAplus et DataAmoins
            int tmp = 0;
            for (int i = 0; i < 6; i++) {

                if (index > 0 && (i + 1) <= index) {

                    if (listCompteur.get(index - (i + 1)) != null) {
                        try {
                            boolean testEmptyOrNullAPlus = listCompteur.get(index - (i + 1)).getDataAPlus() == null
                                    || listCompteur.get(index - (i + 1)).getDataAPlus() == 0.0;
                            boolean testEmptyOrNullAMoins = listCompteur.get(index - (i + 1)).getDataAMoins() == null
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
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) > 0 && dataAMoins != null
                && Double.compare(dataAMoins, 0.0) == 0) {
            presence = "2";
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) == 0 && dataAMoins != null
                && Double.compare(dataAMoins, 0.0) > 0) {
            presence = "2";
        } else if (dataAPlus != null && Double.compare(dataAPlus, 0.0) == 0 && dataAMoins != null
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

    // public void createXLSXFile(String idClient) {
    // List<Object> listSourceExterne =
    // meterDataExterneService.retrieveData(idClient);
    // try {
    // XSSFWorkbook workbook = new XSSFWorkbook();
    // XSSFSheet sheet = workbook.createSheet("Données externes");

    // XSSFRow headerRow = sheet.createRow(0);
    // headerRow.createCell(0).setCellValue("Horodatage");
    // headerRow.createCell(1).setCellValue("DataAPlus");
    // headerRow.createCell(2).setCellValue("DataAMoins");
    // headerRow.createCell(3).setCellValue("DataRPlus");
    // headerRow.createCell(4).setCellValue("DataRMoins");
    // headerRow.createCell(5).setCellValue("Presence");
    // headerRow.createCell(6).setCellValue("Qualite");
    // headerRow.createCell(7).setCellValue("Source");

    // // Ajouter les données à la feuille
    // int rowIndex = 1;
    // for (Object obj : listSourceExterne) {
    // Object[] row = (Object[]) obj;
    // XSSFRow sheetRow = sheet.createRow(rowIndex++);
    // sheetRow.createCell(0).setCellValue(row[0].toString());
    // sheetRow.createCell(1).setCellValue(Double.parseDouble(row[1].toString()));
    // sheetRow.createCell(2).setCellValue(Double.parseDouble(row[2].toString()));
    // sheetRow.createCell(3).setCellValue(Double.parseDouble(row[3].toString()));
    // sheetRow.createCell(4).setCellValue(Double.parseDouble(row[4].toString()));
    // sheetRow.createCell(5).setCellValue(row[5].toString());
    // sheetRow.createCell(6).setCellValue(row[6].toString());
    // sheetRow.createCell(7).setCellValue(row[7].toString());

    // }

    // // Écrire le classeur dans un fichier
    // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // String today = dateFormat.format(new Date());
    // String fileName = "donnees_externes_" + today + ".xlsx";
    // FileOutputStream outputStream = new FileOutputStream(
    // Common.meterDataPath + "/" + idClient + "/" + fileName);
    // workbook.write(outputStream);
    // workbook.close();

    // // Fermer le flux de sortie
    // outputStream.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}