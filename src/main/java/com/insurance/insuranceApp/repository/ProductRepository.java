package com.insurance.insuranceApp.repository;

import com.insurance.insuranceApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 *
 * Product repository
 * @author georman.calderon georve@gmail.com
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value="SELECT p from Product p WHERE p.name LIKE %:name%")
    List<Product> findProductByName(@Param("name")String name);

    @Query(value="SELECT p from Product p WHERE p.level =:levelNumber")
    List<Product> findProductByLevel(@Param("levelNumber")Integer levelNumber);
    @Query(value="SELECT p.name from Product p where p.Id=:id")
    String findKeyword(@Param("id") Integer id);
    @Query(value="SELECT p.level from Product p where p.Id=:id")
    Integer getLevel(@Param("id") Integer id);
}
