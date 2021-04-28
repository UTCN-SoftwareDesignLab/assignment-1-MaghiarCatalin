package com.lab4.demo.user;

import com.lab4.demo.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.ENTITY;
import static com.lab4.demo.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> findAll() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserListDTO create(@RequestBody UserListDTO user) {
        return userService.create(user);
    }


    @PutMapping(ENTITY)
    public UserListDTO edit(@PathVariable Integer id, @RequestBody UserListDTO user) {
        return userService.update(id, user);
    }

    @DeleteMapping()
    public void deleteAll() {
        userService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
