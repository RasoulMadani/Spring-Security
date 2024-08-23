package com.security.springSecurity.dto;

import com.security.springSecurity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
public record UserResponse(int id, String username, List<Role> role) implements Serializable {
}
