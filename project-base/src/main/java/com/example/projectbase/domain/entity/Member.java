package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
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
@Table(name = "members")
public class Member extends DateAuditing {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Nationalized
  @Column(nullable = false)
  private String fullName;

  @Nationalized
  @Column(nullable = false)
  private String email;

  @Nationalized
  @Column(nullable = false)
  private String avatar;

  @Nationalized
  @Column(nullable = false)
  private String phone;

  @Nationalized
  @Column(nullable = false)
  private String address;

  @Nationalized
  @Column(nullable = false)
  private String className;

  @Nationalized
  @Column(nullable = false)
  private String birth;

  @Nationalized
  @Column(nullable = false)
  private String gen;

  @Nationalized
  @Column(nullable = false)
  private Integer numberCourse;

  @Nationalized
  @Column(nullable = false)
  private String status ;

  @Nationalized
  @Column(nullable = false)
  private String qr;

  //Link to table Role
  @ManyToOne
  @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_MEMBER_ROLE"))
  private Role role;

  //Link to table Question
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
  @JsonIgnore
  private Set<Question> questions = new HashSet<>();

  //Link to table Member_Notification
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "notifiedMember")
  @JsonIgnore
  private Set<Member_Notification> memberNotifications= new HashSet<>();
}
