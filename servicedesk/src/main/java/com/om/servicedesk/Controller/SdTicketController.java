package com.om.servicedesk.Controller;

import com.om.servicedesk.DTO.InsertTicketsDTO;
import com.om.servicedesk.DTO.TicketResponseDTO;
import com.om.servicedesk.Entity.SdTickets;
import com.om.servicedesk.Exceptions.ResourceNotFoundException;
import com.om.servicedesk.Service.SdTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class SdTicketController {
    @Autowired
    private SdTicketService ticketService;

    @GetMapping("/get/all")
    public ResponseEntity<List<TicketResponseDTO>>getAll(){
        List<SdTickets> list=ticketService.getAll();
        if(list.isEmpty()){
            throw new ResourceNotFoundException("No tickets found");
        }
        return new ResponseEntity<>(TicketResponseDTO.getList(list),HttpStatus.OK);
    }
    @GetMapping("/get/{userName}")
    public ResponseEntity<List<TicketResponseDTO>>getByUserName(@PathVariable String userName){
        List<SdTickets>list=ticketService.getByUserName(userName);
        if(list.isEmpty()){
            throw new ResourceNotFoundException("No ticket found for the user "+ userName);
        }
        return new ResponseEntity<>(TicketResponseDTO.getList(list),HttpStatus.OK);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> raiseTicket(@RequestBody InsertTicketsDTO ticket, @PathVariable Long userId){
        ticketService.raiseTicket(ticket,userId);
        return new ResponseEntity<>("Ticket has been created..", HttpStatus.OK);

    }
    @GetMapping("/ticket-info/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getInfo(@PathVariable Long ticketId){
        return new ResponseEntity<>(ticketService.getTicketInfo(ticketId),HttpStatus.OK);
    }
    @PutMapping("/assign/{assigneeId}/{assignTo}/{ticketId}")
    public ResponseEntity<String>assignTicket(@PathVariable Long assigneeId,@PathVariable Long assignTo,@PathVariable Long ticketId,@RequestBody String comment){
        if(ticketService.assignTicket(assigneeId,assignTo,ticketId,comment)){
            return new ResponseEntity<>("Ticket has been assigned",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Ticket has assignment failed",HttpStatus.NOT_ACCEPTABLE);

    }
    @PutMapping("/update-status/{ticketId}")
    public  ResponseEntity<String> updateStatus(@RequestBody String status,@PathVariable  Long ticketId){
        if(ticketService.updateSubStatus(status,ticketId)){
            return new ResponseEntity<>("SubStatus Updated",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("SubStatus could not be Updated",HttpStatus.NOT_ACCEPTABLE);


    }
    @PutMapping("/update-sub-status/{ticketId}")
    public  ResponseEntity<String> updateSubStatus(@RequestBody String SubStatus,@PathVariable  Long ticketId){
        if(ticketService.updateSubStatus(SubStatus,ticketId)){
            return new ResponseEntity<>("SubStatus Updated",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("SubStatus could not be Updated",HttpStatus.NOT_ACCEPTABLE);


    }
}
