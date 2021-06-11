package openRemote.demo.Repository;

import openRemote.demo.Model.LoginModel;
import openRemote.demo.Model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, ObjectId> {

    UserModel FindByName(LoginModel model);
}
