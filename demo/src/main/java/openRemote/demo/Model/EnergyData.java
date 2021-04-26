package openRemote.demo.Model;

import lombok.Generated;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document("Energy_Data")
public class EnergyData {

    @Id
    @Generated
    public ObjectId id;
    public String timestamp;
    public String name;
    public String attribute_name;
    public double wattage;
}
