package com.openremote.resource;

import com.openremote.model.EnergyData;
import com.openremote.model.SolarData;
import com.openremote.repository.EnergyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public EnergyData GetData(@PathVariable UUID id){
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/getData")
    public List<EnergyData> GetData(){
        return repository.findAll();
    }

}
