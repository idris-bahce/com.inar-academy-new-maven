package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {

        try {
            // Location of properties
            String path = System.getProperty("user.dir") + "/Configuration.properties";
            // Getting this file as a stream
            FileInputStream input = new FileInputStream(path);
            // Creating an object of Properties class
            configFile = new Properties();
            //  Loading information from the FileInputStream object into the Properties object with the load method.
            configFile.load(input);
            // close the input FileInputStream
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    /**
     * This method returns property value from configuration.properties file
     *
     * @param keyName property name
     * @return property value
     */
    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
    }


}
