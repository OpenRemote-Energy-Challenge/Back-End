package openRemote.demo.addons;

import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Authorisation {

    @Autowired
    private UserRepository repository;

    public String IsAuthorised(ObjectId id, int level){
        UserModel user = repository.findById(id).orElse(null);

        if(user.accessLevel >= level)
            return(user.fullName);

        return null;
    }
}
