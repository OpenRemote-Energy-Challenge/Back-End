package openRemote.demo.addons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Logging {

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    public void Logger(String fullName, String apiCall, String ipAddress){
        logger.info(apiCall + " from level: " + fullName + " with address: " + ipAddress);
    }

    public String getIpAddress(HttpServletRequest request){
        return request.getRemoteAddr();
    }
}
