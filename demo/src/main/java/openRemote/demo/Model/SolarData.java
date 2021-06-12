package openRemote.demo.Model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Solar_Data")
public class SolarData {

    @Id
    @Generated
    public ObjectId id;

    public String timestamp;
    public String name;
    public String attribute_name;
    public double value;
}

