/*
 * @ (#) ProductDAO.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import fit.ktth.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p FROM Product p WHERE "
            + "CAST(p.id AS string) LIKE %:keyword% OR "
            + "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "CAST(p.price AS string) LIKE %:keyword%")
    List<Product> search(@Param("keyword") String keyword);
    List<Product> findProductByRegisterDate(LocalDate registerDate);

    Product findById(int id);

    boolean existsById(int id);

    void deleteById(int id);

    List<Product> findProductByCategory_Id(int id);
}
