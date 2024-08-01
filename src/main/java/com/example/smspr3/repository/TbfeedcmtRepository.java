package com.example.smspr3.repository;

import com.example.smspr3.domain.Tbfeedcmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbfeedcmtRepository extends JpaRepository<Tbfeedcmt,String> {
}
