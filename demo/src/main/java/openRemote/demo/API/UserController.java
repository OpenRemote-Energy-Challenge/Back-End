package openRemote.demo.API;

import openRemote.demo.Model.LoginModel;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/Login")
    public UserModel Login(@RequestBody LoginModel model){
        return repository.FindByName(model);
    }
}
