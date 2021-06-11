package openRemote.demo.API;

import openRemote.demo.Model.LoginModel;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.UserRepository;
import openRemote.demo.addons.Authorisation;
import openRemote.demo.addons.Logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    private Logging logger;
    private Authorisation auth;

    @PostMapping("/login")
    public UserModel Login(@RequestBody LoginModel model, HttpServletRequest request){
        UserModel user = repository.FindByName(model);
        String address = logger.getIpAddress(request);
        logger.Logger(user.fullName, "/user/register/", address);
        return user;
    }

    @PostMapping("/register/{userid}")
    public void Login(@RequestBody UserModel newUser, @PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 2);
        logger.Logger(name, "/user/register/", address);

        if(name != null)
            repository.insert(newUser);
    }
}
