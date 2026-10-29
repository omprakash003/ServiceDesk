package com.om.servicedesk.Repos;

import com.om.servicedesk.Entity.SdTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SdTicketsRepo extends JpaRepository<SdTickets,Long> {
    @Query("Select T from SdTickets T where assignedTo.userId = " +
            "(Select u.userId from SdUser u where u.userName= :name)")
    List<SdTickets> getByUsername(@Param("name") String name);
}
