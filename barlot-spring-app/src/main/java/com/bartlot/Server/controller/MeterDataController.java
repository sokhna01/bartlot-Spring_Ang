package com.bartlot.Server.controller;

import com.bartlot.Server.entity.InterventionEntity;
import com.bartlot.Server.entity.MeterConfigEntity;
import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.entity.WorkTableEntity;
import com.bartlot.Server.model.ClientSitePointAssociation;
import com.bartlot.Server.model.PropositionModel;
import com.bartlot.Server.repository.MeterConfigRepository;
import com.bartlot.Server.repository.BruteAcquisitionRepository;
import com.bartlot.Server.service.BruteAcquisitionService;
import com.bartlot.Server.service.Task1Service;
import com.bartlot.Server.service.Task2Service;
import com.bartlot.Server.service.Task4Service;
import com.bartlot.Server.service.Task7Service;
import com.bartlot.Server.service.Task8Service;
import com.bartlot.Server.service.Task9Service;
import com.bartlot.Server.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;

// import com.bartlot.Server.service.Task8Service;
// import com.bartlot.Server.service.Task9Service;
import com.bartlot.Server.service.Task3Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
// @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class MeterDataController {
    @Autowired
    private BruteAcquisitionService meterDataService;

    @Autowired
    private BruteAcquisitionRepository meterDataRepository;

    @Autowired
    private Task1Service task1Service;

    @Autowired
    private Task2Service task2Service;

    @Autowired
    private Task3Service task3Service;

    @Autowired
    private Task4Service task4Service;

    @Autowired
    private Task7Service task7Service;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MeterConfigRepository meterConfigRepository;

    @Autowired
    private Task9Service task9Service;

    @Autowired
    private Task8Service task8Service;

    @PostMapping("/tache9")
    public ResponseEntity<?> addIntervention(
            @RequestParam("idCompteur") String idCompteur,
            @RequestParam("beginDate") String beginDate,
            @RequestParam("endDate") String endDate) {

        String resp = task9Service.intervention(idCompteur, beginDate, endDate);

        return ResponseEntity.ok(resp);

    }

    @PostMapping("/update_intervention")
    public ResponseEntity<?> updateIntervention(
            @RequestParam("id") int id,
            @RequestParam("beginDate") String beginDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("annuler") boolean annuler) {

        Map<String, String> map = new HashMap<String, String>();

        String resp = task9Service.updateIntervention(id, beginDate, endDate,
                annuler);
        map.put("msg", resp);
        return ResponseEntity.ok(map);
    }

    // @PostMapping("/getlist_intervention")
    // public List<InterventionEntity>
    // getListIntervention(@RequestParam("idcompany") String idcompany) {

    // int idCompany = Integer.parseInt(idcompany);

    // return task9Service.getListIntervention(idCompany);
    // }

    @PostMapping("/search_by_date")
    public List<InterventionEntity> searchListByDate(
            @RequestParam("begin_horodatage") String beginHorodatage,
            @RequestParam("end_horodatage") String endHorodatage) {

        return task9Service.getListIntervention(beginHorodatage, endHorodatage);
    }

    @GetMapping("/tache8_get_table")
    public List<PropositionModel> tache8GetTable() {

        return task8Service.workTableData();
    }

    @PostMapping("/tache8_update_table")
    public ResponseEntity<?> updateWorkTable(
            @RequestParam("horodatage") String horodatage,
            @RequestParam("dataAPlus") String dataAPlus,
            @RequestParam("dataAMoins") String dataAMoins,
            @RequestParam("dataRPlus") String dataRPlus,
            @RequestParam("dataRMoins") String dataRMoins,
            @RequestParam("source") String source,
            @RequestParam("idCompteur") String idCompteur,
            @RequestParam("commentaire") String commentaire) {

        String msg = "";

        Timestamp timestamp = Timestamp.valueOf(horodatage.replace("T", " ").replace("00:00", ""));

        if (!dataAPlus.equals("null") && !dataAMoins.equals("null") && !dataRPlus.equals("null") &&
                !dataRMoins.equals("null")) {

            msg = task8Service.updateWorkTable(timestamp, dataAPlus, dataAMoins, dataRPlus,
                    dataRMoins, source, idCompteur, commentaire);

        } else if (dataAPlus.equals("null") && dataAMoins.equals("null") && dataRPlus.equals("null") &&
                dataRMoins.equals("null")) {

            msg = task8Service.interpolationLineaire(timestamp, source, idCompteur, commentaire);

        }

        // Afficher l'objet Timestamp sans le décalage horaire
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", msg);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/getlist_data_anterieur")
    public List<WorkTableEntity> getListDataAnterieur(@RequestParam("horodatage") String horodatage) {

        return task8Service.getListWorkTableDataAnterieur(horodatage);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Code pour gérer le téléchargement du fichier
        return ResponseEntity.ok("upload_ok");
    }

    // @GetMapping("/getlistmeterdata")
    // public List<BruteAcquisitionEntity> getListMeter() {
    // return meterDataService.getListMeterData();
    // }

    @GetMapping("/getlistmeterdata")
    public ResponseEntity<List<BruteAcquisitionEntity>> getListMeter() {

        List<BruteAcquisitionEntity> meterData = meterDataService.getListMeterData();
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:4200")
                .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                .header("Access-Control-Allow-Headers", "*")
                .body(meterData);
    }

    @PostMapping("/insert_meter_data")
    public ResponseEntity<?> insertXlsxToBD(
            @RequestParam("filename") String filename) {

        Map<String, String> map = new HashMap<String, String>();

        Long count = meterDataRepository.count();

        if (count == 0) {

            task1Service.readXLSXFile(filename);
        } else {
            task1Service.readXLSXFileForNextTask1(filename);
        }

        map.put("msg", "insert_ok");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/tache2")
    public ResponseEntity<?> updateMissingField() {

        Map<String, String> map = new HashMap<String, String>();
        task2Service.readXLSXFileForTask2();
        map.put("msg", "update_ok");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/tache3")
    public ResponseEntity<?> updateSourceandPresence() {

        Map<String, String> map = new HashMap<String, String>();

        task3Service.updateSource();

        map.put("msg", "update_ok");

        return ResponseEntity.ok(map);

    }

    @GetMapping("/tache4")
    public ResponseEntity<?> updateQualite() {

        Map<String, String> map = new HashMap<String, String>();

        task4Service.executeTask4();

        map.put("msg", "update_ok");

        return ResponseEntity.ok(map);

        // boolean insertionSuccess = false;
        // try {
        // task7Service.insertMDIntoWorkTable();
        // insertionSuccess = true;
        // } catch (Exception e) {
        // insertionSuccess = false;
        // }

        // if (insertionSuccess) {
        // map.put("msg", "update_ok");
        // } else {
        // map.put("msg", "update_failed");
        // }

    }

    @GetMapping("/getlistmeterdatareporting")
    public HashMap<String, List<BruteAcquisitionEntity>> getListMeterDataByType() {

        HashMap<String, List<BruteAcquisitionEntity>> list = new HashMap<String, List<BruteAcquisitionEntity>>();

        list = meterDataService.getListMeterDataByType();

        return list;

    }

    @GetMapping("/getlistmeterconfig")
    public HashMap<String, MeterConfigEntity> getListMeterConfig() {
        HashMap<String, MeterConfigEntity> list = new HashMap<String, MeterConfigEntity>();
        List<MeterConfigEntity> meterConfigs = meterConfigRepository.findAll();
        for (MeterConfigEntity meterConfig : meterConfigs) {
            list.put(meterConfig.getIdCompteurPrincipal(), meterConfig);
            list.put(meterConfig.getIdCompteurRedondant(), meterConfig);
        }
        return list;
    }

    @GetMapping("/getid")
    public List<Map<String, Object>> findAllClientSitePointComptage(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (tokenService.isTokenValid(token)) {

                return meterDataService.findAllClientSitePointComptage();
            } else {
                throw new RuntimeException("Token invalide");
            }
        } else {
            throw new RuntimeException("Jeton manquant ou mal formaté");
        }
    }

    @GetMapping("/selectListData")
    public ResponseEntity<List<Map<String, Object>>> getSelectListData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            if (tokenService.isTokenValid(token)) {
                List<Map<String, Object>> selectListData = meterDataService.findAllClientSitePointComptage();
                return ResponseEntity.ok().body(selectListData);
            } else {
                throw new RuntimeException("Token invalide");
            }
        } else {
            throw new RuntimeException("Jeton manquant ou mal formaté");
        }
    }

    @GetMapping("/clients")
    public List<ClientSitePointAssociation> getAllClientsWithSitesAndPoints(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            if (tokenService.isTokenValid(token)) {
                return meterDataService.getAllClientsWithSitesAndPoints();
            } else {
                throw new RuntimeException("Token invalide");
            }
        } else {
            throw new RuntimeException("Jeton manquant ou mal formaté");
        }
    }

    @PostMapping("/tache7")
    public ResponseEntity<?> executeTask7(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (tokenService.isTokenValid(token)) {
                task7Service.insertMDIntoWorkTable();
                return ResponseEntity.ok("Données insérées dans la table de travail avec succès");
            } else {
                throw new RuntimeException("Token invalide");
            }
        } else {
            throw new RuntimeException("Jeton manquant ou mal formaté");
        }
    }

    // @PostMapping("/tache9")
    // public ResponseEntity<?> addIntervention(
    // @RequestParam("idCompteur") String idCompteur,
    // @RequestParam("beginDate") String beginDate,
    // @RequestParam("endDate") String endDate) {

    // Map<String, String> map = new HashMap<String, String>();

    // String resp = task9Service.intervention(idCompteur, beginDate, endDate);
    // map.put("msg", resp);
    // return ResponseEntity.ok(map);
    // }

    // @PostMapping("/update_intervention")
    // public ResponseEntity<?> updateIntervention(
    // @RequestParam("id") int id,
    // @RequestParam("beginDate") String beginDate,
    // @RequestParam("endDate") String endDate,
    // @RequestParam("annuler") boolean annuler) {

    // Map<String, String> map = new HashMap<String, String>();

    // String resp = task9Service.updateIntervention(id, beginDate, endDate,
    // annuler);
    // map.put("msg", resp);
    // return ResponseEntity.ok(map);
    // }

    // @PostMapping("/getlist_intervention")
    // public List<InterventionEntity>
    // getListIntervention(@RequestParam("idcompany") String idcompany) {

    // int idCompany = Integer.parseInt(idcompany);

    // return task9Service.getListIntervention(idCompany);
    // }

    // @PostMapping("/search_by_date")
    // public List<InterventionEntity> searchListByDate(
    // @RequestParam("begin_horodatage") String beginHorodatage,
    // @RequestParam("end_horodatage") String endHorodatage) {

    // return task9Service.getListIntervention(beginHorodatage, endHorodatage);
    // }

    // @GetMapping("/tache8_get_table")
    // public List<PropositionModel> tache8GetTable() {

    // return task8Service.workTableData();
    // }

    // @PostMapping("/tache8_update_table")
    // public ResponseEntity<?> updateWorkTable(
    // @RequestParam("horodatage") String horodatage,
    // @RequestParam("dataAPlus") String dataAPlus,
    // @RequestParam("dataAMoins") String dataAMoins,
    // @RequestParam("dataRPlus") String dataRPlus,
    // @RequestParam("dataRMoins") String dataRMoins,
    // @RequestParam("source") String source,
    // @RequestParam("idCompteur") String idCompteur,
    // @RequestParam("commentaire") String commentaire) {

    // String msg = "";

    // Timestamp timestamp = Timestamp.valueOf(horodatage.replace("T", "
    // ").replace("00:00", ""));

    // if (!dataAPlus.equals("null") && !dataAMoins.equals("null") &&
    // !dataRPlus.equals("null") &&
    // !dataRMoins.equals("null")) {

    // msg = task8Service.updateWorkTable(timestamp, dataAPlus, dataAMoins,
    // dataRPlus,
    // dataRMoins, source, idCompteur, commentaire);

    // } else if (dataAPlus.equals("null") && dataAMoins.equals("null") &&
    // dataRPlus.equals("null") &&
    // dataRMoins.equals("null")) {

    // msg = task8Service.interpolationLineaire(timestamp, source, idCompteur,
    // commentaire);

    // }

    // // Afficher l'objet Timestamp sans le décalage horaire
    // Map<String, String> map = new HashMap<String, String>();
    // map.put("msg", msg);
    // return ResponseEntity.ok(map);
    // }

    // @PostMapping("/getlist_data_anterieur")
    // public List<WorkTableEntity> getListDataAnterieur(@RequestParam("horodatage")
    // String horodatage) {

    // return task8Service.getListWorkTableDataAnterieur(horodatage);
    // }
}