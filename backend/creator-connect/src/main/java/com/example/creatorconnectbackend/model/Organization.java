package com.example.creatorconnectbackend.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    private Long OrgID;

    private String OrgName;

    private String ProfileImage;

    private String CompanyType;

    private Long Size;

    private String WebsiteLink;

    private String TargetInfluencerType;

    private String Location;

    @OneToOne
    @JoinColumn(name = "OrgID", referencedColumnName = "UserID")
    private User user;
}