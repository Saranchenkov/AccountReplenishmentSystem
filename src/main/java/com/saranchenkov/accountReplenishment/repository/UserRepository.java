package com.saranchenkov.accountReplenishment.repository;

import com.saranchenkov.accountReplenishment.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ivan on 17.06.2017.
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("select u from User u where u.id=:id")
//    User findById(@Param("id") Integer id);

    @Query("select u from User u where u.email=:email")
    User findByEmail(@Param("email") String email);

    @Override
    @Query("select u from User u where u.role=0")
    Page<User> findAll(Pageable pageable);

    @Modifying
    @Query("update User set balance=:balance where id=:id")
    void update(@Param("balance")double balance, @Param("id") int id);

}
