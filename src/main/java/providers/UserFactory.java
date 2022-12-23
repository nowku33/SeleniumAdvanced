package providers;

import com.github.javafaker.Faker;
import models.User;
import org.yaml.snakeyaml.Yaml;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UserFactory {

    private static Logger log = Logger.getLogger(UserFactory.class.getName());

    public User getRandomUser() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName+lastName+"@gmail.com";
        String country = faker.country().name();
        String city = faker.address().cityName();
        String zipCode = faker.address().zipCode();

        return new UserBuilder(firstName,lastName,email).setCity(city).setCountry(country).
                setZipCode(zipCode).build();
    }

    public User getAlreadyRegisteredUser() {

        User user = getRandomUser();
        saveToYaml(user);

        return user;
    }

    private void saveToYaml(User user) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());
        data.put("email", user.getEmail());
        data.put("country", user.getCountry());
        data.put("city", user.getCity());
        data.put("zipCode", user.getZipCode());
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(data, writer);

        log.info(yaml.toString());
    }
}
