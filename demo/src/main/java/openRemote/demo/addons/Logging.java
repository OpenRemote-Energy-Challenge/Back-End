package openRemote.demo.addons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Logging {

    private static Logger logger = LogManager.getLogger("PropertiesConfig");

    public void Logger(String fullName, String apiCall, String ipAddress){
        logger.info(apiCall + " from level: " + fullName + " with address: " + ipAddress);
    }

    public String getIpAddress(HttpServletRequest request){
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
}
