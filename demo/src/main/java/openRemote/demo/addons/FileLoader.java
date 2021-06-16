package openRemote.demo.addons;

import openRemote.demo.Model.EnergyData;
import openRemote.demo.Model.SolarData;
import openRemote.demo.Repository.EnergyDataRepository;
import openRemote.demo.Repository.SolarData_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FileLoader {

    @Autowired
    private SolarData_Repo repoSolar;
    @Autowired
    private EnergyDataRepository repoEnergy;

    public void ImportCsvDataSolar(MultipartFile file){
        try {
            String line = "";

            BufferedReader br = new BufferedReader(new FileReader(convert(file)));
            while((line = br.readLine())!=null){
                String [] data = line.split(",");

                SolarData sd = new SolarData();
                sd.timestamp = data[0];
                sd.name = data[1];
                sd.attribute_name = data[2];
                sd.value = Double.parseDouble(data[3]);

                repoSolar.save(sd);
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ImportCsvDataEnergy(MultipartFile file) {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(convert(file)));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                EnergyData sd = new EnergyData();
                sd.timestamp = data[0];
                sd.name = data[1];
                sd.uv = Integer.parseInt(data[2]);

                sd.timestamp_year = Integer.parseInt(data[3]);
                sd.timestamp_month = Integer.parseInt(data[4]);
                sd.timestamp_week = Integer.parseInt(data[5]);
                sd.timestamp_day = Integer.parseInt(data[6]);
                sd.timestamp_hour = Integer.parseInt(data[7]);
                sd.timestamp_minute = Integer.parseInt(data[8]);
                sd.timestamp_dayofweek = Integer.parseInt(data[9]);
                repoEnergy.save(sd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
