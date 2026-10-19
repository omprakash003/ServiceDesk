package com.om.servicedesk.Repos;

import com.om.servicedesk.Entity.SdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SdUserRepo extends JpaRepository<SdUser,Long> {
    Optional<SdUser> findByEmail(String email);
    Optional<SdUser> findByPhone(String phone);
    Optional<SdUser> findByUserName(String userName);
}
