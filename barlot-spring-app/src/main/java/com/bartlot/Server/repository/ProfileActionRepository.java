package com.bartlot.Server.repository;

import com.bartlot.Server.entity.ProfilActionEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileActionRepository extends JpaRepository<ProfilActionEntity, String> {
    @Query(value = "SELECT pf_action.act_code, url_code, act_name, category, action_label FROM profil_action pf_action "
            + "INNER JOIN action act ON act.act_code = pf_action.act_code where pf_action.pf_code = ?;", nativeQuery = true)
    List<Object[]> getListActions(String pfCode);
}
