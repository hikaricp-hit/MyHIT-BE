package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column(nullable = false)
    private String content;

    @Nationalized
    @Column(nullable = false)
    private String status;

    @Nationalized
    @Column
    private String answer;

    @Nationalized
    @Column(nullable = false)
    private String askDate;

    @Nationalized
    @Column(nullable = false)
    private String responseDate;

    //Link to table Member
    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_QUESTION_ROLE"))
    private Member member;
}
