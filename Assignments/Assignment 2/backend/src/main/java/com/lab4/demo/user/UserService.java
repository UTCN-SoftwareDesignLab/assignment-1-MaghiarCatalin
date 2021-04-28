package com.lab4.demo.user;

import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.mapper.UserMapper;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    private User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public List<UserListDTO> findAll(){
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public UserListDTO create(UserListDTO user) {
        return userMapper.toDTO(userRepository.save(
                userMapper.fromDTO(user)
        ));
    }

    public UserListDTO update(Integer id, UserListDTO userListDTO) {
        User user = findById(id);
        user.setRoles(userListDTO.getRoles());
        user.setUsername(userListDTO.getUsername());
        user.setEmail(userListDTO.getEmail());
        user.setPassword(userListDTO.getPassword());
        return userMapper.toDTO(
                userRepository.save(user)
        );
    }
}
