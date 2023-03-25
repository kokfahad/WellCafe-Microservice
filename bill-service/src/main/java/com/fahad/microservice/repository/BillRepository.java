package com.fahad.microservice.repository;


import com.fahad.microservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "select * from Bill b order by b.id desc", nativeQuery = true)
    List<Bill> getAllBills();

//    ArrayList<Bill> findByEmailOrderByIdDesc(String currentUser);

    List<Bill> findByCreatedByOrderByIdDesc(String currentUser);
}
