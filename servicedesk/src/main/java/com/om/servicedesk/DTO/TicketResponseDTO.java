package com.om.servicedesk.DTO;

import com.om.servicedesk.Entity.SdTickets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public static List<TicketResponseDTO> getList(List<SdTickets> list){
        List<TicketResponseDTO> responselist=new ArrayList<>();
        for(SdTickets e: list){
            responselist.add(new TicketResponseDTO(e));

        }
        return responselist;

    }
    public TicketResponseDTO(SdTickets tickets){
        this.assignedTo=tickets.getAssignedTo().getUserName();
        this.creationDate=tickets.getCreationDate();
        this.description=tickets.getDescription();
        this.shortDescription=tickets.getShortDescription();
        this.status=tickets.getStatus();
        this.subStatus=tickets.getSubStatus();
        this.ticketId=tickets.getTicketId();
        this.userName=tickets.getUser().getUserName();
    }


}
