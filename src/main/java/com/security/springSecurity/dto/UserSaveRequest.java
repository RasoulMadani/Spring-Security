package com.security.springSecurity.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record UserSaveRequest(String username, String password) {
}
