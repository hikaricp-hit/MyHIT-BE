package com.example.projectbase.security;

import com.example.projectbase.domain.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

  private final String id;
  @JsonIgnore
  private final String username;
  @JsonIgnore
  private final String password;
  private final String fullName;
  private final String email;
  private final String avatar;
  private final String phone;
  private final String address;
  private final String className;
  private final String birth;
  private final String gen;
  private final Integer numberCourse;
  private final String status;
  private final String qr;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(String id, String username, String password, String fullName, String email, String avatar,
                       String phone, String address, String className, String birth, String gen,
                       Integer numberCourse, String status, String qr,
                       Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.avatar = avatar;
    this.phone = phone;
    this.address = address;
    this.className = className;
    this.birth = birth;
    this.gen = gen;
    this.numberCourse = numberCourse;
    this.status = status;
    this.qr = qr;
    this.authorities = authorities == null ? null : new ArrayList<>(authorities);
  }

  public static UserPrincipal create(Member member) {
    List<GrantedAuthority> authorities = new LinkedList<>();
    authorities.add(new SimpleGrantedAuthority(member.getRole().getName()));
    return new UserPrincipal(member.getId(), member.getUsername(), member.getPassword(), member.getFullName(),
            member.getEmail(), member.getAvatar(), member.getPhone(), member.getAddress(), member.getClassName(),
            member.getBirth(), member.getGen(), member.getNumberCourse(), member.getStatus(), member.getQr(),
            authorities);
  }

  public String getId() {
    return id;
  }

  @JsonIgnore
  @Override
  public String getUsername() {
    return username;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public String getClassName() {
    return className;
  }

  public String getBirth() {
    return birth;
  }

  public String getGen() {
    return gen;
  }

  public Integer getNumberCourse() {
    return numberCourse;
  }

  public String getStatus() {
    return status;
  }

  public String getQr() {
    return qr;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities == null ? null : new ArrayList<>(authorities);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null || getClass() != object.getClass())
      return false;
    UserPrincipal that = (UserPrincipal) object;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
