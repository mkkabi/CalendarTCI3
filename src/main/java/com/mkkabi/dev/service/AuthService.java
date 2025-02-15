package com.mkkabi.dev.service;

import com.mkkabi.dev.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}