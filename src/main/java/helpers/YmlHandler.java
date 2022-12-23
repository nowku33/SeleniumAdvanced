package helpers;

import models.User;
import org.junit.jupiter.api.ClassOrderer;
import providers.YmlFiles;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class YmlHandler {

    private static final Logger log = Logger.getLogger(ClassOrderer.ClassName.class.getName());

    private static String file = null;

    public YmlHandler(YmlFiles file) {

                this.file = file.fileName;

        }


    public void saveToYml(String propertyName, String propertyValue) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put(propertyName, propertyValue);

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(file);
        yaml.dump(data, writer);
    }

    public void saveToYml(User user) throws IOException {

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(file);
        yaml.dump(user, writer);
    }

    public String getProperty(String propertyName) throws IOException {

        try {
            Yaml yaml = new Yaml();
            FileReader reader = new FileReader(file);
            Map<String, Object> ymlContent = yaml.load(reader);
            return ymlContent.get(propertyName).toString();
        } catch (IOException e) {
            throw new FileNotFoundException("Could not find the data file: "+file+", specify a valid path.");
        }

    }
    //TODO Przetestowac ponizsza metode
    public Map<String, String> readDataFromYml() throws FileNotFoundException {
        try {
            Yaml yaml = new Yaml();
            FileReader reader = new FileReader(file);
            Map<String, String> ymlContent = yaml.load(reader);
            return ymlContent;
        } catch (IOException e) {
            throw new FileNotFoundException("Could not find the data file: "+file+", specify a valid path.");
        }
    }
}
