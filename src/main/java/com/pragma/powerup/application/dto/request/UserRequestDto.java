package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^[0-9]+$", message = "DNI debe ser numérico solamente")
    private String dni;

    @Pattern(regexp = "^\\+?[0-9]{1,13}$", message = "El número de teléfono debe tener un máximo de 13 caracteres y puede comenzar con +.")
    private String phone;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate birthDate;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
}
