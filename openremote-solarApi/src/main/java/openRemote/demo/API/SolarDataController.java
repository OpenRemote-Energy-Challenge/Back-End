package openRemote.demo.API;

import openRemote.demo.Model.SolarData;
import openRemote.demo.Repository.SolarData_Repo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/solar")
public class SolarDataController {
    @Autowired
    private SolarData_Repo repository;

    @PostMapping("/addData")
    public String AddData(@RequestBody SolarData data){
        repository.save(data);
       return "added data with id: " + data._id;
    }

    @GetMapping("/getData/{id}")
    public SolarData GetData(@PathVariable ObjectId id){
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/getData")
    public List<SolarData> GetData(){
        return repository.findAll();
    }

    @GetMapping("/pushData")
    public String PushData(){
        ImportCsvData();
        return "pushed csv data.";
    }

    public void ImportCsvData(){
        try {
            String line = "";

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/SolarData.csv"));
            while((line = br.readLine())!=null){
                String [] data = line.split(",");

                SolarData sd = new SolarData();
                sd.timestamp = data[0];
                sd.name = data[1];
                sd.attribute_name = data[2];
                sd.value = Double.parseDouble(data[3]);

                repository.save(sd);
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}