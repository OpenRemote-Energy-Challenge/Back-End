package openRemote.demo.API;

import openRemote.demo.Model.SolarData;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.SolarData_Repo;
import openRemote.demo.Repository.UserRepository;
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
    private SolarData_Repo solarRepo;

    @Autowired
    private UserRepository userRepo;

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    @PostMapping("/addData/{userid}")
    public String AddData(@RequestBody SolarData data, @PathVariable ObjectId userid){
        UserModel user = userRepo.findById(userid).orElse(null);
        if(user.accessLevel >= 1) {
            solarRepo.save(data);
            logger.info("/solar/addData");
            return "added data with id: " + data.id;
        }

        return "data error: unauthorized";
    }

    @GetMapping("/getData/{objectid}/{userid}")
    public SolarData GetData(@PathVariable ObjectId objectid, @PathVariable ObjectId userid){
        UserModel user = userRepo.findById(userid).orElse(null);
        if(user.accessLevel >= 1) {
            logger.info("/solar/getData/" + objectid);
            return solarRepo.findById(objectid).orElse(null);
        }

        return null;
    }

    @GetMapping("/getData/{userid}")
    public List<SolarData> GetData(@PathVariable ObjectId userid){
        UserModel user = userRepo.findById(userid).orElse(null);
        if(user.accessLevel >= 1) {
            logger.info("/solar/getData");
            return solarRepo.findAll();
        }

        return null;
    }

    @GetMapping("/pushData/{userid}")
    public String PushData(@PathVariable ObjectId userid){
        UserModel user = userRepo.findById(userid).orElse(null);
        if(user.accessLevel >= 1) {
            ImportCsvData();
            logger.info("/solar/importData");
            return "pushed csv data.";
        }

        return "data error: unauthorized";
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
