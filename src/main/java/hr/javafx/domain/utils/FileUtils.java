package hr.javafx.domain.utils;

import hr.javafx.domain.entities.User;
import hr.javafx.domain.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static String USERS_TEXT_FILE_NAME = "dat/users.txt";

    public static List<User> readUsersFromFile() {


        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_TEXT_FILE_NAME))) {
            String line;
            while (Optional.ofNullable(line = reader.readLine()).isPresent()) {

                Integer id = Integer.parseInt(line);
                String username = reader.readLine();
                String password = reader.readLine();
                String userRole = reader.readLine();

                UserRole role = UserRole.GUEST;

                if(userRole.toUpperCase() == "ADMIN") {
                    role = UserRole.ADMIN;
                } else if(userRole.toUpperCase() == "GUEST") {
                    role = UserRole.GUEST;
                }

                Optional<User> newUserOptional = Optional.of(new User(id, username, password, role));

                if(newUserOptional.isPresent()) {
                    User newUser = newUserOptional.get();
                    users.add(newUser);
                }
            }

        } catch(IOException e) {
            String message = "Greska prilikom citanja kategorija iz datoteke!";
            System.out.println(message);
            logger.error(message, e);
        }

        return users;
    }


}
