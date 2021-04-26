package openRemote.demo.API;

import openRemote.demo.Model.EnergyData;
import openRemote.demo.Repository.EnergyDataRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyDataController {

    @Autowired
    private EnergyDataRepository repository;

    @PostMapping("/addData")
    public String AddData(@RequestBody EnergyData data){
        repository.save(data);
        return "added data with id: " + data.id;
    }

    @GetMapping("/getData/{id}")
    public EnergyData GetData(@PathVariable ObjectId id){
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/getData")
    public List<EnergyData> GetData(){
        return repository.findAll();
    }

}
