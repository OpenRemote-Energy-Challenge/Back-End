package openRemote.demo.API;

import openRemote.demo.Model.LoginModel;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    @PostMapping("/login")
    public UserModel Login(@RequestBody LoginModel model){
        UserModel user = repository.FindByName(model);
        logger.info("/user/login to user: " + user.fullName);
        return user;
    }

    @PostMapping("/register/{userid}")
    public void Login(@RequestBody UserModel newUser, @PathVariable ObjectId userid){
        UserModel currUser = repository.findById(userid).orElse(null);
        if(currUser.accessLevel >= 2){
            logger.info("/user/register new user" + newUser.fullName);
            repository.insert(newUser);
        }
    }
}
