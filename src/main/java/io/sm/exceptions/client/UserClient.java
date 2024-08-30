package io.sm.exceptions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com/users",
        fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @GetMapping
    List<UserDTO> getAllUsers();

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO user);

    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user);

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
