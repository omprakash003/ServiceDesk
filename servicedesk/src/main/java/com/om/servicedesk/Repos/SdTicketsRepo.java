package com.om.servicedesk.Repos;

import com.om.servicedesk.Entity.SdTickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SdTicketsRepo extends JpaRepository<SdTickets,Long> {
}
