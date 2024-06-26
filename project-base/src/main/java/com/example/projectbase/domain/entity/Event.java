package com.example.projectbase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
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
    private String startDate;

    @Nationalized
    @Column(nullable = false)
    private String endDate;

    @Nationalized
    @Column(nullable = false)
    private String startTime;

    @Nationalized
    @Column(nullable = false)
    private String endTime;

    //Link to table Member_Event
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @JsonIgnore
    private Set<Member_Event> member_events = new HashSet<>();

    public static final String TYPE_CLASS = "Class";
    public static final String TYPE_ACTIVITY = "Activity";
    public static final String TYPE_OFFLINE = "Offline";
}
