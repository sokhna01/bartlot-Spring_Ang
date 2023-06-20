package com.bartlot.Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.Table_configuration_points_de_compteurEntity;
// import com.bartlot.Server.entity.MeterDataEntity;
import com.bartlot.Server.repository.Table_configuration_points_de_compteurRepository;
// import com.bartlot.Server.repository.MeterDataRepository;

@Service
public class Task12Service {

    // @Autowired
    // private MeterDataRepository meterDataRepository;

    @Autowired
    private Table_configuration_points_de_compteurRepository table_configuration_points_de_compteurRepository;

    public String table_configuration_points_de_compteur(String idCompany, String id_client, String id_site,
            String id_point_de_comptage, String convention_signe,
            String id_compteur_principal, String id_compteur_redondance, Float tension_nominale_vn_primaire,
            Float courant_nominale_in_primaire, Float puissance_nominale_pn_du_point_de_comptage, Float b_injection,
            Float b_soutirage, Float alarme_seul_a_plus, Float alarme_seul_a_moins, Float alarme_seul_r_plus,
            Float alarme_seul_r_moins, Float alarme_seul_r_tg_mois) {

        String msg = "not_ok";

        msg = "insert_ok";

        Table_configuration_points_de_compteurEntity table_configuration_points_de_compteurEntity = new Table_configuration_points_de_compteurEntity();
        table_configuration_points_de_compteurEntity.setId_client(id_client);
        table_configuration_points_de_compteurEntity.setId_site(id_site);
        table_configuration_points_de_compteurEntity.setId_point_de_comptage(id_point_de_comptage);
        table_configuration_points_de_compteurEntity.setConvention_signe(convention_signe);
        table_configuration_points_de_compteurEntity.setId_compteur_principal(id_compteur_principal);
        table_configuration_points_de_compteurEntity.setId_compteur_redondance(id_compteur_redondance);
        table_configuration_points_de_compteurEntity.setTension_nominale_vn_primaire(tension_nominale_vn_primaire);
        table_configuration_points_de_compteurEntity.setCourant_nominale_in_primaire(courant_nominale_in_primaire);
        table_configuration_points_de_compteurEntity
                .setPuissance_nominale_pn_du_point_de_comptage(puissance_nominale_pn_du_point_de_comptage);
        table_configuration_points_de_compteurEntity.setB_injection(b_injection);
        table_configuration_points_de_compteurEntity.setB_soutirage(b_soutirage);
        table_configuration_points_de_compteurEntity.setAlarme_seul_a_plus(alarme_seul_a_plus);
        table_configuration_points_de_compteurEntity.setAlarme_seul_a_moins(alarme_seul_a_moins);
        table_configuration_points_de_compteurEntity.setAlarme_seul_r_plus(alarme_seul_r_plus);
        table_configuration_points_de_compteurEntity.setAlarme_seul_r_moins(alarme_seul_r_moins);
        table_configuration_points_de_compteurEntity.setAlarme_seul_r_tg_mois(alarme_seul_r_tg_mois);

        table_configuration_points_de_compteurRepository.save(table_configuration_points_de_compteurEntity);
        msg = "insert_ok";

        return msg;
    }

    public String updateTable_configuration_points_de_compteur(Integer id, String idCompany, String id_client,
            String id_site,
            String id_point_de_comptage, String convention_signe,
            String id_compteur_principal, String id_compteur_redondance, Float tension_nominale_vn_primaire,
            Float courant_nominale_in_primaire, Float puissance_nominale_pn_du_point_de_comptage, Float b_injection,
            Float b_soutirage, Float alarme_seul_a_plus, Float alarme_seul_a_moins, Float alarme_seul_r_plus,
            Float alarme_seul_r_moins, Float alarme_seul_r_tg_mois, Boolean annuler) {

        String msg = "not_ok";

        msg = "insert_ok";

        Table_configuration_points_de_compteurEntity table_configuration_points_de_compteurEntity = table_configuration_points_de_compteurRepository
                .findById(id).orElse(null);
        table_configuration_points_de_compteurEntity.setId_client(id_client);
        table_configuration_points_de_compteurEntity.setId_site(id_site);
        table_configuration_points_de_compteurEntity.setId_point_de_comptage(id_point_de_comptage);
        table_configuration_points_de_compteurEntity.setConvention_signe(convention_signe);
        table_configuration_points_de_compteurEntity.setId_compteur_principal(id_compteur_principal);
        table_configuration_points_de_compteurEntity.setId_compteur_redondance(id_compteur_redondance);
        table_configuration_points_de_compteurEntity.setTension_nominale_vn_primaire(tension_nominale_vn_primaire);
        table_configuration_points_de_compteurEntity.setCourant_nominale_in_primaire(courant_nominale_in_primaire);
        table_configuration_points_de_compteurEntity
                .setPuissance_nominale_pn_du_point_de_comptage(puissance_nominale_pn_du_point_de_comptage);
        table_configuration_points_de_compteurEntity.setB_injection(b_injection);
        table_configuration_points_de_compteurEntity.setB_soutirage(b_soutirage);
        table_configuration_points_de_compteurEntity.setAlarme_seul_a_plus(alarme_seul_a_plus);
        table_configuration_points_de_compteurEntity.setAlarme_seul_a_moins(alarme_seul_a_moins);
        table_configuration_points_de_compteurEntity.setAlarme_seul_r_plus(alarme_seul_r_plus);
        table_configuration_points_de_compteurEntity.setAlarme_seul_r_moins(alarme_seul_r_moins);
        table_configuration_points_de_compteurEntity.setAlarme_seul_r_tg_mois(alarme_seul_r_tg_mois);

        table_configuration_points_de_compteurRepository.save(table_configuration_points_de_compteurEntity);
        msg = "insert_ok";

        return msg;
    }

    public List<Table_configuration_points_de_compteurEntity> getAllTable_configuration_points_de_compteurEntities() {
        // ...
        return table_configuration_points_de_compteurRepository.findAll();
    }

}
