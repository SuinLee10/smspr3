package com.example.smspr3.repository;

import com.example.smspr3.domain.Tbfeedlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbfeedlikeRepository extends JpaRepository<Tbfeedlike,String> {
    Tbfeedlike findByTbfeedIdAndTbuserId(String tbpostId, String tbuserId);
}
