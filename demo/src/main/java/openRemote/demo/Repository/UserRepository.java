package openRemote.demo.Repository;

import openRemote.demo.Model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, ObjectId> {
    @Query("{fullName : ?0, password : ?1}")
    UserModel findUserByUsernameAndPassword(String fullName, String password);
}
