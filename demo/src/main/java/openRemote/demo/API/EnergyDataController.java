package openRemote.demo.API;

import openRemote.demo.Model.EnergyData;
import openRemote.demo.Repository.EnergyDataRepository;
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
    private EnergyDataRepository repository;

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    @PostMapping("/addData")
    public String AddData(@RequestBody EnergyData data){
        repository.save(data);
        logger.info("/energy/addData");
        return "added data with id: " + data.id;
    }

    @GetMapping("/getData/{id}")
    public EnergyData GetData(@PathVariable ObjectId id){
        logger.info("/energy/getData/" + id);
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/getData")
    public List<EnergyData> GetData(){
        logger.info("/energy/getData");
        return repository.findAll();
    }

}
