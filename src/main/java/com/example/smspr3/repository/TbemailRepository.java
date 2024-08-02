package com.example.smspr3.repository;

import com.example.smspr3.domain.Tbemail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbemailRepository extends JpaRepository<Tbemail,String> {
    Tbemail findByUsername(String username);
    Tbemail findByUsernameAndNumber(String username, String number);

}
