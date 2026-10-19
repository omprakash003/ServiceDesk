package com.om.servicedesk.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "SD_USERS")
@Entity

public class SdUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_gen")
    @SequenceGenerator(name ="user_gen",
    sequenceName = "USER_SEQUENCE",
    allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_NAME",nullable = false)
    private String userName;
    @Column(name = "SHORT_NAME",nullable = false)
    private String shortName;
    @Column(name = "EMAIL",nullable = false)
    private String email;
    @Column(name = "PHONE",nullable = false)
    private String phone;
    @Column(name = "USER_ROLE")
    private String userRole="USER";
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<SdTickets> userTickets;

    @OneToMany(mappedBy = "assignee")
    @JsonIgnore
    private List<SdTickets> AssigneeTickets;

    @OneToMany(mappedBy = "assignedBy")
    @JsonIgnore
    private List<SdTicketHistory>assigneeTicketsHistory;

    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnore
    private List<SdTicketHistory>assignedToTicketsHistory;


    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnore
    private List<SdTickets> AssignedToTickets;



}
