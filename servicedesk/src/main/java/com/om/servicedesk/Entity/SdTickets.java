package com.om.servicedesk.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter

public class SdTickets {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,generator = "ticket_gen")
    @SequenceGenerator(name = "ticket_gen",sequenceName = "TICKETS_SEQUENCE",allocationSize = 1)
    @Column(name="TICKET_ID")
    private Long ticketId;
    @Column(name = "SHORT_DESCRIPTION",nullable = false)
    private String shortDescription;
    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")

    private SdUser user;

    @ManyToOne
    @JoinColumn(name = "ASSIGNEE_ID")

    private  SdUser assignee;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_TO_ID")

    private SdUser assignedTo;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
    @Column(name="STATUS")
    private String status;
    @Column(name="SUB_STATUS")
    private String subStatus;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<SdTicketHistory> sdTicketHistories;
    public SdTickets(){
        this.status="NEW";
        this.creationDate=LocalDateTime.now();

    }

}
