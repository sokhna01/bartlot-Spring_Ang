package com.bartlot.Server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartlot.Server.entity.Table_configuration_points_de_compteurEntity;
import com.bartlot.Server.service.Task12Service;
import com.bartlot.Server.repository.Table_configuration_points_de_compteurRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class Table_configuration_points_de_compteurController {

    @Autowired
    private Task12Service Task12Service;

    @Autowired // Injecter le repository
    private Table_configuration_points_de_compteurRepository table_configuration_points_de_compteurRepository;

    @PostMapping("/update_table_configuration_points_de_compteur")
    public ResponseEntity<?> updateTable_configuration_points_de_compteur(
            @RequestParam("id") Integer id,
            @RequestParam("idcompany") String idcompany,
            @RequestParam("id_client") String id_client,
            @RequestParam("id_site") String id_site,
            @RequestParam("id_point_de_comptage") String id_point_de_comptage,
            @RequestParam("convention_signe") String convention_signe,
            @RequestParam("id_compteur_principal") String id_compteur_principal,
            @RequestParam("id_compteur_redondance") String id_compteur_redondance,
            @RequestParam("tension_nominale_vn_primaire") Float tension_nominale_vn_primaire,
            @RequestParam("courant_nominale_in_primaire") Float courant_nominale_in_primaire,
            @RequestParam("puissance_nominale_pn_du_point_de_comptage") Float puissance_nominale_pn_du_point_de_comptage,
            @RequestParam("b_injection") Float b_injection,
            @RequestParam("b_soutirage") Float b_soutirage,
            @RequestParam("alarme_seul_a_plus") Float alarme_seul_a_plus,
            @RequestParam("alarme_seul_a_moins") Float alarme_seul_a_moins,
            @RequestParam("alarme_seul_r_plus") Float alarme_seul_r_plus,
            @RequestParam("alarme_seul_r_moins") Float alarme_seul_r_moins,
            @RequestParam("alarme_seul_r_tg_mois") Float alarme_seul_r_tg_mois,
            @RequestParam("annuler") Boolean annuler) {

        Map<String, String> map = new HashMap<String, String>();

        String resp = Task12Service.updateTable_configuration_points_de_compteur(
                id,
                idcompany,
                id_client,
                id_site,
                id_point_de_comptage,
                convention_signe,
                id_compteur_principal,
                id_compteur_redondance,
                tension_nominale_vn_primaire,
                courant_nominale_in_primaire,
                puissance_nominale_pn_du_point_de_comptage,
                b_injection,
                b_soutirage,
                alarme_seul_a_plus,
                alarme_seul_a_moins,
                alarme_seul_r_plus,
                alarme_seul_r_moins,
                alarme_seul_r_tg_mois,
                annuler);
        map.put("msg", resp);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/getlist_table_configuration_points_de_compteur")
    public List<Table_configuration_points_de_compteurEntity> getAllTable_configuration_points_de_compteurEntities() {
        return table_configuration_points_de_compteurRepository.findAll();
    }

    // @PostMapping("/getlist_table_configuration_points_de_compteur")
    // public List<Table_configuration_points_de_compteurEntity>
    // getListTable_configuration_points_de_compteur(
    // @RequestParam("idcompany") String idcompany) {

    // int idCompany = Integer.parseInt(idcompany);

    // return
    // Task12Service.getListTable_configuration_points_de_compteur(idCompany);
    // }

}
