package com.example.projectbase.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Column;

@ConfigurationProperties("admin")
@Getter
@Setter
public class AdminInfoProperties {

  private String username;
  private String password;
  private String fullName;
  private String email;

  private String avatar;
  private String phone;
  private String address;
  private String className;
  private String birth;
  private String gen;
  private Integer numberCourse;
  private String status ;
  private String qr;


}
