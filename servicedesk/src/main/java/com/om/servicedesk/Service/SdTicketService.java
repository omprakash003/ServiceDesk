package com.om.servicedesk.Service;

import com.om.servicedesk.DTO.InsertTicketsDTO;
import com.om.servicedesk.DTO.TicketResponseDTO;
import com.om.servicedesk.Entity.SdTicketHistory;
import com.om.servicedesk.Entity.SdTickets;
import com.om.servicedesk.Entity.SdUser;
import com.om.servicedesk.Exceptions.ResourceNotFoundException;
import com.om.servicedesk.Repos.SdHistoryRepo;
import com.om.servicedesk.Repos.SdTicketsRepo;
import com.om.servicedesk.Repos.SdUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SdTicketService {
    @Autowired
    private SdTicketsRepo ticketsRepo;
    @Autowired
    private SdUserRepo userRepo;
    @Autowired
    private SdHistoryRepo historyRepo;
    public boolean raiseTicket(InsertTicketsDTO ticketDTO,Long UserId){
        SdUser user=userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("Invalid User Id"));
        SdTickets ticket=new SdTickets();
        ticket.setShortDescription(ticketDTO.getShortDescription());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setUser(user);
        ticketsRepo.save(ticket);
        return true;

    }
    public TicketResponseDTO getTicketInfo(Long ticketId){
        SdTickets ticket=ticketsRepo.findById(ticketId).orElseThrow(()->
                new ResourceNotFoundException("cannot find ticket with ticket number "+ ticketId));
        SdUser assignedTo=ticket.getAssignedTo();
        String assigned="None";

        if(assignedTo !=null){
            assigned=assignedTo.getShortName();
        }
        return new TicketResponseDTO(ticket.getTicketId(),ticket.getStatus(),ticket.getSubStatus(),ticket.getShortDescription()
        ,ticket.getDescription(),ticket.getUser().getShortName(),assigned,ticket.getCreationDate());
    }
    public boolean assignTicket(Long assigneeId,Long assignId,Long ticketId,String comment){
        SdUser assignee=userRepo.findById(assigneeId).orElseThrow(()->new ResourceNotFoundException("Invlaid Assignee Id"));
        SdUser assignedTo=userRepo.findById(assignId).orElseThrow(()->new ResourceNotFoundException("Invlaid Assigend to Id"));
        SdTickets tickets=ticketsRepo.findById(ticketId).orElseThrow(()->new ResourceNotFoundException("Invlaid Ticket Id"));
        tickets.setAssignee(assignee);
        tickets.setAssignedTo(assignedTo);
        tickets.setSubStatus("UNDER ANALYSIS");
        ticketsRepo.save(tickets);
        SdTicketHistory ticketsHistory=new SdTicketHistory();
        ticketsHistory.setTicket(tickets);
        ticketsHistory.setAssignedBy(assignee);
        ticketsHistory.setAssignedTo(assignedTo);
        ticketsHistory.setComments(comment);
        historyRepo.save(ticketsHistory);
        return true;


    }
    public boolean updateStatus(String subStatus,Long ticketId){
        SdTickets tickets=ticketsRepo.findById(ticketId).orElseThrow(()->new ResourceNotFoundException("Invlaid Ticket Id"));
        tickets.setStatus(subStatus);
        ticketsRepo.save(tickets);
        return true;

    }
    public boolean updateSubStatus(String subStatus,Long ticketId){
        SdTickets tickets=ticketsRepo.findById(ticketId).orElseThrow(()->new ResourceNotFoundException("Invlaid Ticket Id"));
        tickets.setStatus(subStatus);
        ticketsRepo.save(tickets);
        return true;

    }
}
