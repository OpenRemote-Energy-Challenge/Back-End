package com.openremote.model;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@ToString

@Document(collection="Solar_Data")
public class SolarData {

    @Id
    @Generated
    public UUID id;
    public String timestamp;
    public String name;
    public String attribute_name;
    public double value;
}
