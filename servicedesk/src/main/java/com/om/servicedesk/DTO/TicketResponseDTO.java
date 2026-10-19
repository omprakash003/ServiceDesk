package com.om.servicedesk.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class TicketResponseDTO {
    private Long ticketId;
    private  String status;
    private String subStatus;
    private String shortDescription;
    private String description;
    private String userName;
    private String  assignedTo;
    private LocalDateTime creationDate;


}
