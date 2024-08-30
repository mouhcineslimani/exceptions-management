package io.sm.exceptions.client;
import io.sm.exceptions.dtos.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.fetchAllUsers();
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO user = userService.fetchUserById(id);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO user) {
        UserDTO createdUser = userService.addUser(user);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        UserDTO updatedUser = userService.modifyUser(id, user);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(null));
    }
}
