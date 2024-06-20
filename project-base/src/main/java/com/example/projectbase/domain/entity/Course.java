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
@Table(name = "courses")
public class Course {
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
    private String leader;

    @Nationalized
    @Column(nullable = false)
    private String startDate;

    @Nationalized
    @Column(nullable = false)
    private String picture;

    @Nationalized
    @Column(nullable = false)
    private String status;

    //Link to table Register
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @JsonIgnore
    private Set<Register> registers = new HashSet<>();
}
