package Managed;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerMaster {

    public static String mensaje = "";
    public static int tipo = 1;
    private final static String archivoPropertiesLog4j = "log4j.properties";
    private final static Logger logger = Logger.getLogger(LoggerMaster.class);

    // 1 info
    // 2 error    
    public static void logger(String mensaje, int tipo) {
        init();
        switch (tipo) {
            case 1:
                logger.info(mensaje);
                break;
            case 2:
                logger.error(mensaje);
                break;
        }
    }

    private static void init() {
        File log4jfile = new File(archivoPropertiesLog4j);
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
    }
}