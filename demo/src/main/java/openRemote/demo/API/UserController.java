package openRemote.demo.API;

import openRemote.demo.Model.LoginModel;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.UserRepository;
import openRemote.demo.addons.Authorisation;
import openRemote.demo.addons.Logging;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Logging logger;
    @Autowired
    private Authorisation auth;

    @PostMapping("/login")
    public UserModel Login(@RequestBody LoginModel model, HttpServletRequest request){
        UserModel user = repository.findByfullName(model.fullName).orElse(null);

        if(model.password.toString().equals(user.password.toString())) {
            String address = logger.getIpAddress(request);
            logger.Logger(user.fullName, "/user/register/", address);
            return user;
        }

        return null;
    }

    @PostMapping("/register/{userid}")
    public void Login(@RequestBody UserModel newUser, @PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 2);
        logger.Logger(name, "/user/register/", address);

        if(name != null)
            repository.insert(newUser);
    }

    @GetMapping("/getUser")
    public List<UserModel> getUser(){
        return repository.findAll();
    }

    @PostMapping("/addUser")
    public UserModel addUser(@RequestBody UserModel model){
        repository.save(model);
        return model;
    }
}
