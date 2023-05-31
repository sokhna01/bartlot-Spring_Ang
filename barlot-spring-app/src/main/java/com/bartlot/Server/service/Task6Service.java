package com.bartlot.Server.service;

import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.entity.MeterDataExterneEntity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.List;

@Service
public class Task6Service {

    @Autowired
    private MeterDataExterneService meterDataExterneService;

    @Autowired
    private MeterDataService meterDataService;

    @Autowired
    private Task3Service task3Service;

    public void executeTask6(String idClient, int idCompany) throws SQLException {
        List<MeterDataExterneEntity> listCompteur = meterDataExterneService.getListCompteur(); // Récupérer les données
                                                                                               // de la table
                                                                                               // meterDataExterne
        List<MeterDataEntity> listCompteurMD = meterDataService.getListCompteurMD(); // Récupérer les données de la
                                                                                     // table meterData

        for (MeterDataEntity meterData : listCompteurMD) {
            meterData.setSource("Se");
            meterData.setQualite("5");

            String presence = task3Service.getPresenceValue(meterData,
                    meterData.getDataAPlus(),
                    meterData.getDataAMoins(), listCompteurMD);

            meterData.setPresence(presence);
            meterDataService.insertRow(meterData);
        }

        for (MeterDataExterneEntity meterDataExterne : listCompteur) {
            meterDataExterne.setSource("Se");
            meterDataExterne.setQualite("5");

            String presence = getPresenceValueExt(meterDataExterne,
                    meterDataExterne.getDataAPlus(),
                    meterDataExterne.getDataAMoins(), listCompteur);

            meterDataExterne.setPresence(presence);
            meterDataExterneService.insertRow(meterDataExterne, idClient);
        }
    }

    public String getPresenceValueExt(MeterDataExterneEntity compteurCourant,
            Double dataAPlus, Double dataAMoins,
            List<MeterDataExterneEntity> listCompteur) {
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
            // La donnée est absente simultanement sur DataAplus et DataAmoins
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

// Specifier le repertoire dans lequel le fichier sera telechargé et faire une
// nomination ascendante des fichiers pour éviter les confusions

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
