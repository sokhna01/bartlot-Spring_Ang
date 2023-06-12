package com.bartlot.Server.repository;

import com.bartlot.Server.entity.ProfilesEntity;
import com.bartlot.Server.model.ReturnObject;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<ProfilesEntity, Integer> {

    String returnCodeBase = "Server error: 10";

    @Query(value = "SELECT m_profiles.pf_code, pf_name FROM users_profiles m_profiles INNER JOIN profiles pf ON pf.pf_code = m_profiles.pf_code where m_profiles.user_id = ?1", nativeQuery = true)
    List<Object[]> getProfiles(Integer idUsers);

    default ReturnObject getProfilesWithException(Integer idUsers) {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(getProfiles(idUsers));
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
