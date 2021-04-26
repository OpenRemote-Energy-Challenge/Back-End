package openRemote.demo.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@ToString

@Document(collection="Solar_Data")
public class SolarData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public ObjectId id;
    public String timestamp;
    public String name;
    public String attribute_name;
    public double value;
}

