package openRemote.demo.addons;

import openRemote.demo.Model.SolarData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FileLoader {

    public void ImportCsvData(MultipartFile file){
        try {
            String line = "";

            BufferedReader br = new BufferedReader(new FileReader((File) file));
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
