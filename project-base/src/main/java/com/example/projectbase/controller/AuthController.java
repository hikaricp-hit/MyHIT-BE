package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.ConfirmOtpRequestDto;
import com.example.projectbase.domain.dto.request.ForgotPasswordRequestDto;
import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.service.AuthService;
import com.example.projectbase.validator.annotation.ValidFileImage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
@Validated
@RestApiV1
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "API Login")
  @PostMapping(UrlConstant.Auth.LOGIN)
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
    return VsResponseUtil.success(authService.login(request));
  }

  @Operation(summary = "API test")
  @PostMapping("auth/test")
  public String login(@ValidFileImage MultipartFile multipartFile) {
    return multipartFile.getContentType();
  }

  @Operation(summary = "API Logout")
  @PostMapping(UrlConstant.Auth.LOGOUT)
  public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    return VsResponseUtil.success(authService.logout(request, response, authentication));
  }

  @Operation(summary = "API forgotPassword")
  @PostMapping(UrlConstant.Auth.FORGOT_PASSWORD)
  public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto request) {
    return VsResponseUtil.success(authService.forgetPassword(request));
  }

  @Operation(summary = "API confirm OTP and change password")
  @PostMapping(UrlConstant.Auth.CONFIRM_OTP_AND_CHAGE_PASSWORD)
  public ResponseEntity<?> confirmOtpAndChangePassword(@Valid @RequestBody ConfirmOtpRequestDto request) {
    return VsResponseUtil.success(authService.confirmOtpAndChangePassword(request));
  }
}
