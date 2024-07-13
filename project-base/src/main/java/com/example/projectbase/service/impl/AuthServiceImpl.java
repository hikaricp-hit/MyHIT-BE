package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SuccessMessage;
import com.example.projectbase.domain.dto.common.DataMailDto;
import com.example.projectbase.domain.dto.request.ConfirmOtpRequestDto;
import com.example.projectbase.domain.dto.request.ForgotPasswordRequestDto;
import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.request.TokenRefreshRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.LoginResponseDto;
import com.example.projectbase.domain.dto.response.TokenRefreshResponseDto;
import com.example.projectbase.domain.entity.Member;
import com.example.projectbase.domain.entity.Otp;
import com.example.projectbase.exception.UnauthorizedException;
import com.example.projectbase.repository.MemberRepository;
import com.example.projectbase.repository.OtpRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.security.jwt.JwtTokenProvider;
import com.example.projectbase.service.AuthService;
import com.example.projectbase.util.RandomOTPUtil;
import com.example.projectbase.util.SendMailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final MemberRepository memberRepository;

  private final SendMailUtil sendMailUtil;

  private final OtpRepository otpRepository;

  private final PasswordEncoder passwordEncoder;
  @Override
  public LoginResponseDto login(LoginRequestDto request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmailOrPhone(), request.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
      String accessToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.FALSE);
      String refreshToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.TRUE);
      return new LoginResponseDto(accessToken, refreshToken, userPrincipal.getId(), authentication.getAuthorities());
    } catch (InternalAuthenticationServiceException e) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_PASSWORD);
    }
  }

  @Override
  public TokenRefreshResponseDto refresh(TokenRefreshRequestDto request) {
    return null;
  }

  @Override
  public CommonResponseDto logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    SecurityContextLogoutHandler logout = new SecurityContextLogoutHandler();
    logout.logout(request, response, authentication);
    return new CommonResponseDto(true, SuccessMessage.LOGOUT);
  }

  @Override
  public CommonResponseDto forgetPassword(ForgotPasswordRequestDto request) {
    Optional<Member> member = memberRepository.findMemberByEmail(request.getEmail());

    if(!member.get().getEmail().equals(request.getEmail())) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_EMAIL);
    }

    // Tạo mã OTP ngẫu nhiên
    String otp = RandomOTPUtil.random();

    // Lưu mã OTP vào cơ sở dữ liệu với thời gian hết hạn là 60 giây
    Otp otpEntity = new Otp();
    otpEntity.setUsername(member.get().getUsername());
    otpEntity.setOtp(otp);
    otpEntity.setCreatedAt(LocalDateTime.now());
    otpEntity.setExpiresAt(LocalDateTime.now().plusSeconds(60));
    otpRepository.save(otpEntity);

    // Gửi mã OTP tới email của người dùng
    DataMailDto mailDto = new DataMailDto();
    mailDto.setTo(request.getEmail());
    mailDto.setSubject("Mã OTP của bạn là: " + otp);

      Map<String, Object> properties = new HashMap<>();
      properties.put("otp", otp);

    mailDto.setProperties(properties);

    try {
      sendMailUtil.sendEmailWithHTML(mailDto, "sendmail.html");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new CommonResponseDto(true, SuccessMessage.SEND_MAIL);
  }

  @Override
  public CommonResponseDto confirmOtpAndChangePassword(ConfirmOtpRequestDto request) {
    Optional<Member> member = memberRepository.findMemberByEmail(request.getEmail());

    if (member.isEmpty()) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_EMAIL);
    }

    Optional<Otp> otpEntity = otpRepository.findByUsernameAndOtp(member.get().getUsername(), request.getOtp());

    if (otpEntity.isEmpty()) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_OTP);
    }

    // Kiểm tra thời gian hết hạn của mã OTP
    if (otpEntity.get().getExpiresAt().isBefore(LocalDateTime.now())) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_OTP_EXPIRED);
    }

    String newPassword = request.getNewPassword();
    member.get().setPassword(passwordEncoder.encode(newPassword));
    memberRepository.save(member.get());

    // Xóa mã OTP sau khi sử dụng
    otpRepository.deleteByUsername(member.get().getUsername());

    return new CommonResponseDto(true, SuccessMessage.CHANGE_PASSWORD_SUCCESS);
  }


}
