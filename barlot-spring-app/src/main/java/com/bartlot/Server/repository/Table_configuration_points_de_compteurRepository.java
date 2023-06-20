
package com.bartlot.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import java.util.List;

import com.bartlot.Server.entity.Table_configuration_points_de_compteurEntity;

public interface Table_configuration_points_de_compteurRepository
                extends JpaRepository<Table_configuration_points_de_compteurEntity, Integer> {

}

// package com.bartlot.Server.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// import
// com.bartlot.Server.entity.Table_configuration_points_de_compteurEntity;
// import org.springframework.data.jpa.repository.Query;

// public interface Table_configuration_points_de_compteurRepository
// extends JpaRepository<Table_configuration_points_de_compteurEntity, Integer>
// {

// @Query("SELECT int FROM Table_configuration_points_de_compteurEntity int
// WHERE "
// + "int.idCompany = :idCompany AND int.id_client")

// // Table_configuration_points_de_compteurEntity findByid_client(String
// // id_client);

// // @Query("SELECT t FROM Table_configuration_points_de_compteurEntity t WHERE
// // t.idCompany = :idCompany")

// List<Table_configuration_points_de_compteurEntity>
// findByIdCompanyId_client(Integer idCompany,
// String id_client);

// }
