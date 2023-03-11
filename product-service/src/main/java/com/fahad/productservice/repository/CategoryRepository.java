package com.fahad.productservice.repository;


import com.fahad.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
//      ResponseEntity<List<Category>> findByName();

      @Query(value = "select c from Category c where c.id in (select p.category from Product p where p.status = 'true')", nativeQuery = true)
      List<String> findAllCategory(@Param("filterValue") String filterValue);
}
