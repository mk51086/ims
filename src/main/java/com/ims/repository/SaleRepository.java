package com.ims.repository;

import com.ims.entity.Sale;
import com.ims.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
    @Query("select s from Sale s where day (s.date) = day (CURRENT_DATE) and month (s.date) = month (CURRENT_DATE) and year (s.date) = year (CURRENT_DATE)")
    List<Sale> findByDay();
    @Query("select s from Sale s where month (s.date)=month (CURRENT_DATE ) and year (s.date) = year (CURRENT_DATE)")
    List<Sale> findByMonth();
    @Query("select s from Sale s where year (s.date)=year (CURRENT_DATE )")
    List<Sale> findByYear();

}
