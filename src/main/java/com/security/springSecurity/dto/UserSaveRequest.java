package com.security.springSecurity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record  UserSaveRequest(
        @NotEmpty @Size(min = 5) String username,
        @NotEmpty @Size(min = 8) String password)
{

}
