package openRemote.demo.API;

import openRemote.demo.Model.EnergyData;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.EnergyDataRepository;
import openRemote.demo.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyDataController {

    @Autowired
    private EnergyDataRepository energyRepo;

    @Autowired
    private UserRepository userRepo;

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    @PostMapping("/addData/{id}")
    public String AddData(@RequestBody EnergyData data, @PathVariable ObjectId id){
        UserModel user = userRepo.findById(id).orElse(null);
        if(user.accessLevel >= 1) {
            energyRepo.save(data);
            logger.info("/energy/addData by acceslevel " + user.accessLevel);
            return "added data with id: " + data.id;
        }
        return "data error: unauthorized";
    }

    @GetMapping("/getData/{objectid}/{userid}")
    public EnergyData GetData(@PathVariable ObjectId objectid, @PathVariable ObjectId userid){
        UserModel user = userRepo.findById(userid).orElse(null);
        if(user.accessLevel >= 1) {
            logger.info("/energy/getData/" + objectid + "by acceslevel " + user.accessLevel);
            return energyRepo.findById(objectid).orElse(null);
        }
        return null;
    }

    @GetMapping("/getData/{userid}")
    public List<EnergyData> GetData(@PathVariable ObjectId userid){
        UserModel user = userRepo.findById(userid).orElse(null);
        if(user.accessLevel >= 1) {
            logger.info("/energy/getData/ by acceslevel " + user.accessLevel);
            return energyRepo.findAll();
        }
        return null;
    }

}
