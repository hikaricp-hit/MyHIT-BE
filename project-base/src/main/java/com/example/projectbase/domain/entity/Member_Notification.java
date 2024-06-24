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
@Table(name = "members_notifications")
public class Member_Notification {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    //Link to table Member
    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_MEMBER_NOTIFICATION"))
    private Member notifiedMember;

    //Link to table Notification
    @ManyToOne
    @JoinColumn(name = "notification_id", foreignKey = @ForeignKey(name = "FK_NOTIFICATION_MEMBER"))
    private Notification notification;


}
