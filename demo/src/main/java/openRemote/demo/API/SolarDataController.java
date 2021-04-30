package openRemote.demo.API;

import openRemote.demo.Model.SolarData;
import openRemote.demo.Repository.SolarData_Repo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/solar")
public class SolarDataController {

    @Autowired
    private SolarData_Repo repository;

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    @PostMapping("/addData")
    public String AddData(@RequestBody SolarData data){
        repository.save(data);
        logger.info("/solar/addData");
       return "added data with id: " + data.id;
    }

    @GetMapping("/getData/{id}")
    public SolarData GetData(@PathVariable ObjectId id){
        logger.info("/solar/getData/" + id);
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/getData")
    public List<SolarData> GetData(){
        logger.info("/solar/getData");
        return repository.findAll();
    }

    @GetMapping("/pushData")
    public String PushData(){
        ImportCsvData();
        logger.info("/solar/importData");
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
