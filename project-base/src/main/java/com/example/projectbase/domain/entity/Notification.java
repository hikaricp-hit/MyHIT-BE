package com.example.projectbase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @Nationalized
    @Column(nullable = false)
    private String detail;

    @Nationalized
    @Column(nullable = false)
    private Date sendDate;

    @Nationalized
    @Column(nullable = false)
    private String type;

    //Link to table Member_Notification
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notification")
    @JsonIgnore
    private Set<Member_Notification> memberNotifications= new HashSet<>();
}
