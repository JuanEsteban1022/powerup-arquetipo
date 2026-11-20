package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.UserRequestDto;

public interface IUserServicePort {
    void saveUser(UserRequestDto userRequestDto);
}