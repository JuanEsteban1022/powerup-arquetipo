package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserRequestMapper userRequestMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        validateAge(userRequestDto.getBirthDate());

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userModel.setRole("OWNER");

        userPersistencePort.saveUser(userModel);
    }

    private void validateAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18){
            throw new IllegalArgumentException("El usuario debe ser mayor de edad");
        }
    }
}
