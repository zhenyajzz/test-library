package ex.smart.library.controller;

import ex.smart.library.model.User;
import ex.smart.library.repo.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class UserController {

    final
    UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping("/createUser")
    public User create(@RequestBody User user) {

        return userRepo.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User findUser = userRepo.findById(id).get();
        if (user == null) {
            throw new NoSuchElementException();
        }
        findUser.setUsername(user.getUsername());

        return userRepo.save(findUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

    @GetMapping("/user/{username}")
    public User findByUserName(@PathVariable String username) {
        return userRepo.findByUsername(username);
    }

}
