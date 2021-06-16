package openRemote.demo.Model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Energy_Data")
public class EnergyData {

    @Id
    @Generated
    public ObjectId id;

    public String timestamp;
    public String name;
    public int uv;

    public int timestamp_year;
    public int timestamp_month;
    public int timestamp_week;
    public int timestamp_day;
    public int timestamp_hour;
    public int timestamp_minute;
    public int timestamp_dayofweek;
}
