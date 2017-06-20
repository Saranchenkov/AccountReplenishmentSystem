package com.saranchenkov.accountReplenishment.repository;

import com.saranchenkov.accountReplenishment.model.Balance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Ivan on 17.06.2017.
 */
@Transactional(readOnly = true)
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    @Query("select b from Balance b where b.id=:id")
    Balance findById(@Param("id") Integer id);

    @Query("select b from Balance b where b.userEmail=:userEmail")
    Balance findByUserEmail(@Param("userEmail") String userEmail);

    @Override
    Page<Balance> findAll(Pageable pageable);

    @Query("select b from Balance b where b.date between :startDate and :endDate")
    Page<Balance> filterAll(Pageable pageable, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
