package openRemote.demo.Model;

import lombok.Data;
import lombok.Generated;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Users")
public class UserModel {

    @Id
    @Generated
    public ObjectId userId;

    public String fullName;
    public String password;
    public int accessLevel;
}
