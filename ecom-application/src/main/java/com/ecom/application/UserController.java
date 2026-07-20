package com.ecom.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
//    @Autowired
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
        // another way is
        // return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
//        User user = userService.fetchUser(id);
//        if(user == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Added Successfully!");
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updatedUser(@PathVariable Long id,
                                              @RequestBody User updatedUser){

        boolean updated = userService.updateUser(id, updatedUser);
        if(updated)
            return ResponseEntity.ok("User added Successfully!");
        return ResponseEntity.notFound().build();
    }


}
