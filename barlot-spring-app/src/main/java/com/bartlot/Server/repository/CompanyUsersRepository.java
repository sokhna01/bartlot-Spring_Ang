package com.bartlot.Server.repository;

import com.bartlot.Server.entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUsersRepository extends JpaRepository<UsersEntity, Integer> {

    @Query(value = "SELECT c_users.id, c_users.firstname, c_users.lastname, c_users.address, c_users.city, c_users.phone, c_users.reset_password, c_users.created_date, c_users.prefered_language, c_users.useautocompletion "
            + "FROM users c_users "
            + "WHERE c_users.username = ?1 AND c_users.password = ?2", nativeQuery = true)

    List<Object[]> findCompanyUsers(String username, String password);

    Optional<UsersEntity> findByUsername(String username);

    @Query(value = "SELECT cu.* FROM users cu WHERE cu.username=?1 AND cu.password=?2", nativeQuery = true)
    UsersEntity findByUsernameAndPassword(String username, String Password);
}