package model.builder;

import model.Client;
import model.User;
import view.DTO.UserDTO;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder setId(int id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setRole(String role) {
        user.setRole(role);
        return this;
    }

    public User build() {
        return user;
    }


    public User buildFromDTO(UserDTO userDTO) {
        User user = new UserBuilder()
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword())
                .setRole(userDTO.getRole())
                .build();

        return user;
    }
}
