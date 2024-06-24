package com.example.projectbase.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "members_events")
public class Member_Event {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    //Link to table Member
    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_MEMBER_EVENT"))
    private Member participants;

    //Link to table Event
    @ManyToOne
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "FK_EVENT_MEMBER"))
    private Event event;

    @Nationalized
    @Column(nullable = false)
    private String status;
}
