package com.energizer.auto_uz.dto.reques;

import com.energizer.auto_uz.validation.annotatons.ConfirmPassword;
import com.energizer.auto_uz.validation.annotatons.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@ConfirmPassword(message = "Пароли не совпадают")
public record RegisterRequest(
        @NotEmpty(message = "Имя не может быть пустым")
        @Size(min = 3, max = 30, message = "Имя должно быть в пределах от 3 до 30 символов")
        String username,

        @NotEmpty(message = "Пароль не может быть пустым")
        @Size(min = 3, max = 30, message = "Пароль должно быть в пределах от 3 до 30 символов")
        String password,

        String confirm_password,

        @Email(message = "Неверный формат электронной почты")
        @UniqueEmail(message = "Адрес электронной почты уже используется")
        String email
)
{}
