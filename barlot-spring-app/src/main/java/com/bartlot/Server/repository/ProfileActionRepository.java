package com.bartlot.Server.repository;

import com.bartlot.Server.entity.ProfilActionEntity;
import com.bartlot.Server.model.ReturnObject;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileActionRepository extends JpaRepository<ProfilActionEntity, String> {

    String returnCodeBase = "Server error: 12";

    @Query(value = "SELECT pf_action.act_code, url_code, act_name, category, action_label FROM profil_action pf_action "
            + "INNER JOIN action act ON act.act_code = pf_action.act_code where pf_action.pf_code = ?;", nativeQuery = true)
    List<Object[]> getListActions(String pfCode);

    /* **************************************************************** */
    /* *******************METHODE AVEC TRY-CATCH********************** */
    /* ************************************************************** */

    default ReturnObject getListActionsWithException(String pfCode) {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(getListActions(pfCode));
            returnObject.setStatus("ok");
            return returnObject;

        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "01");
            return returnObject;
        }
    }

}
