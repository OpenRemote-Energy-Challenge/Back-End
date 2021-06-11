package openRemote.demo.API;

import openRemote.demo.Model.EnergyData;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.EnergyDataRepository;
import openRemote.demo.Repository.UserRepository;
import openRemote.demo.addons.Authorisation;
import openRemote.demo.addons.Logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyDataController {

    @Autowired
    private EnergyDataRepository energyRepo;

    private Logging logger;
    private Authorisation auth;

    @PostMapping("/addData/{id}")
    public String AddData(@RequestBody EnergyData data, @PathVariable ObjectId id, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(id, 1);
        logger.Logger(name, "/energy/addData/{id}", address);

        if(name != null) {
            energyRepo.save(data);
            return "added data with id: " + data.id;
        }
        return "data error: unauthorized";
    }

    @GetMapping("/getData/{objectid}/{userid}")
    public EnergyData GetData(@PathVariable ObjectId objectid, @PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 1);
        logger.Logger(name, "/energy/getData/{objectid}", address);

        if(name != null)
            return energyRepo.findById(objectid).orElse(null);

        return null;
    }

    @GetMapping("/getData/{userid}")
    public List<EnergyData> GetData(@PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 1);
        logger.Logger(name, "/energy/getData/all", address);

        if(name != null)
            return energyRepo.findAll();

        return null;
    }

}
