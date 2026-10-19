package com.om.servicedesk.Entity;


import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "SD_TICKET_HISTORY")
public class SdTicketHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hist_gen")
    @SequenceGenerator(name = "hist_gen",sequenceName = "HISTORY_SEQUENCE",allocationSize = 1)
    @Column(name = "HISTORY_ID")
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private SdTickets ticket;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_BY")

    private SdUser assignedBy;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_TO")
    private SdUser assignedTo;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "ASSIGNMENT_DATE")
    private LocalDateTime assignmentDate;
    public SdTicketHistory(){
        this.assignmentDate=LocalDateTime.now();
    }
}
