package com.bartlot.Server.repository;

import com.bartlot.Server.entity.CompanyUsersEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUsersRepository extends JpaRepository<CompanyUsersEntity, Integer> {

    @Query(value = "SELECT c_users.id, c_users.firstname, c_users.lastname, c_users.address, ca.address, ca.city, c_users.phone, c_users.idcompany, ci.name, c_users.reset_password, c_users.created_date, ci.language, cc.code, ci.type, ci.dispatchtype, ci.useautocompletion "
            + "FROM company_users c_users "
            + "INNER JOIN companies_info ci ON ci.idcompany = c_users.idcompany "
            + "INNER JOIN company_adress ca ON ci.idcompany = ca.idcompany "
            + "INNER JOIN country_companies cc ON ci.idcompany = cc.idcompany "
            + "WHERE c_users.username = ?1 AND c_users.password = ?2 AND ci.companycode = ?3", nativeQuery = true)

    List<Object[]> findCompanyUsers(String username, String password, String companyCode);

    Optional<CompanyUsersEntity> findByUsername(String username);

    @Query(value = "SELECT cu.* FROM company_users cu INNER JOIN companies_info ci ON ci.idcompany=cu.idcompany WHERE cu.username=?1 AND ci.companyCode=?2", nativeQuery = true)
    CompanyUsersEntity findByUsernameAndCompanyCode(String username, String companyCode);

}
