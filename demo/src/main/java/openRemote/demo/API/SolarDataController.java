package openRemote.demo.API;

import openRemote.demo.Model.SolarData;
import openRemote.demo.Model.UserModel;
import openRemote.demo.Repository.SolarData_Repo;
import openRemote.demo.Repository.UserRepository;
import openRemote.demo.addons.Authorisation;
import openRemote.demo.addons.FileLoader;
import openRemote.demo.addons.Logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    private Logging logger;
    @Autowired
    private Authorisation auth;
    @Autowired
    private FileLoader loader;

    @PostMapping("/addData/{userid}")
    public String AddData(@RequestBody SolarData data, @PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 1);
        logger.Logger(name, "/solar/addData/", address);

        if(name != null) {
            solarRepo.save(data);
            return "added data with id: " + data.id;
        }

        return "data error: unauthorized";
    }

    @GetMapping("/getData/{objectid}/{userid}")
    public SolarData GetData(@PathVariable ObjectId objectid, @PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 1);
        logger.Logger(name, "/solar/getData/{objectid}", address);

        if(name != null)
            return solarRepo.findById(objectid).orElse(null);

        return null;
    }

    @GetMapping("/getData/{userid}")
    public List<SolarData> GetData(@PathVariable ObjectId userid, HttpServletRequest request){
        String address = logger.getIpAddress(request);
        String name = auth.IsAuthorised(userid, 1);
        logger.Logger(name, "/solar/getData/", address);

        if(name != null)
            return solarRepo.findAll();

        return null;
    }

    @GetMapping("/pushData")
    public String PushData(@RequestParam("file")MultipartFile file , HttpServletRequest request){
        String address = logger.getIpAddress(request);
        logger.Logger("user", "/solar/pushData/", address);
        if(file != null) {
            loader.ImportCsvData(file);
            return "pushed csv data";
        }

        return "ERROR: file is null";
    }
}
