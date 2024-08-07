package com.example.smspr3.repository;

import com.example.smspr3.domain.Tbrefreshtoken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbrefreshtokenRepository extends JpaRepository<Tbrefreshtoken,String> {
    Tbrefreshtoken findByTbuserId(String tbuserId);
}
