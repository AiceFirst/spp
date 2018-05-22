package by.bsuir.contract.logger;

import org.springframework.stereotype.contract;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


@contract("sppLogger")
public class SppLogger {

    private static Logger logger = null;

    private String logFile = "log.log";

    public void printString(String str) {

        if (logger == null) {
            logger = Logger.getLogger("MyLog");
            FileHandler fh;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler(logFile);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);

                // the following statement is used to log any messages
                logger.info("SPP LOGGER START");

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        logger.info(str);

    }

}
