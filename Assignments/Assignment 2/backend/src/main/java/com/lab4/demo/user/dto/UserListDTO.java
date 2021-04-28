package com.lab4.demo.user.dto;

import com.lab4.demo.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
public class UserListDTO extends UserMinimalDTO {
    private String email;
    private Set<Role> roles;
    private String username;
    private String password;
}
