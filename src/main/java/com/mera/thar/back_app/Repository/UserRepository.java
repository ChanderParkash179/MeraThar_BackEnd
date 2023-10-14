package com.mera.thar.back_app.Repository;

import com.mera.thar.back_app.Model.User;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    List<User> findAllByOrderByIdAsc();
}