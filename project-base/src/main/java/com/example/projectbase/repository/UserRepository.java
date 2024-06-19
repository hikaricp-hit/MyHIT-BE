package com.example.projectbase.repository;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, String> {

  @Query("SELECT u FROM Member u WHERE u.id = ?1")
  Optional<Member> findById(String id);

  @Query("SELECT u FROM Member u WHERE u.username = ?1")
  Optional<Member> findByUsername(String username);

  default Member getUser(UserPrincipal currentUser) {
    return findByUsername(currentUser.getUsername())
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_USERNAME,
            new String[]{currentUser.getUsername()}));
  }

}
