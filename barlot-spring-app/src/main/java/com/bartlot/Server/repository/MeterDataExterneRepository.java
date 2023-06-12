package com.bartlot.Server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.MeterDataExterneEntity;
import com.bartlot.Server.model.ReturnObject;

public interface MeterDataExterneRepository extends JpaRepository<MeterDataExterneEntity, Integer> {

    String returnCodeBase = "Server error: 03";

    // @Query(value = "SELECT * FROM meter_data_source_externe WHERE idclient=?1",
    // nativeQuery = true)
    @Query("SELECT e FROM MeterDataExterneEntity e WHERE e.idClient = :idClient")
    List<MeterDataExterneEntity> findAllSourceExterne(String idClient);

    // @Modifying
    // @Query("UPDATE MeterDataExterneEntity e SET e.presence = :presence, e.qualite
    // = :qualite, e.source = :source WHERE e.id = :id")
    // void updatePresenceQualiteSource(String idClient, String presence, String
    // qualite);

    /* **************************************************************** */
    /* *******************METHODE AVEC TRY-CATCH********************** */
    /* ************************************************************** */
    default ReturnObject findAllSourceExterneWithException(String idClient) {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findAllSourceExterne(idClient));
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