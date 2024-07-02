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
@Table(name = "events")
public class Event {
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
    private String type;

    @Nationalized
    @Column(nullable = false)
    private String location;

    @Nationalized
    @Column(nullable = false)
    private Date startDate;

    @Nationalized
    @Column(nullable = false)
    private Date endDate;

    @Nationalized
    @Column(nullable = false)
    private Date startTime;

    @Nationalized
    @Column(nullable = false)
    private Date endTime;

    //Link to table Member_Event
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @JsonIgnore
    private Set<Member_Event> member_events = new HashSet<>();

}
