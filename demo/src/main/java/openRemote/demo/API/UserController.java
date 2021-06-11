package openRemote.demo.API;

import openRemote.demo.Model.LoginModel;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/Login")
    public UserModel Login(@RequestBody LoginModel model){
        return repository.FindByName(model);
    }

    @PostMapping("register/{userid}")
    public void Login(@RequestBody UserModel newUser, @PathVariable ObjectId userid){
        UserModel currUser = repository.findById(userid).orElse(null);
        if(currUser.accessLevel >= 2){
            repository.insert(newUser);
        }
    }
}
