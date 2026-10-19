package com.om.servicedesk.Repos;

import com.om.servicedesk.Entity.SdTicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SdHistoryRepo extends JpaRepository<SdTicketHistory,Long> {
}
