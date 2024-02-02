package com.energizer.auto_uz.exceptions;

import com.energizer.auto_uz.dto.errors.DataErrors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class UserNotCreatedException extends RuntimeException {
    private final DataErrors errors;
}
