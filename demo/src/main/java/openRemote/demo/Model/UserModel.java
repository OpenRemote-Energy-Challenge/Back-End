package openRemote.demo.Model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Users")
public class UserModel {

    @Id
    public ObjectId Id;
    public String fullName;
    public String password;
    public int accessLevel;
}
