package openRemote.demo.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Document(collection="Solar_Data")
public class SolarData {

    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
        public String timestamp;
        public String name;
        public String attribute_name;
        public double value;
    }

