package com.security.springSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
public record UserResponse(int id, String username, String role) implements Serializable {
}
