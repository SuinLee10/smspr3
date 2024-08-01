package com.example.smspr3.repository;

import com.example.smspr3.domain.Tbfeed;
import com.example.smspr3.domain.Tbfeedfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbfeedfileRepository extends JpaRepository<Tbfeedfile,String> {
}
