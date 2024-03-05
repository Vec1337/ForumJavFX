package hr.javafx.domain.utils;


import hr.javafx.domain.exceptions.TopicIsNullException;
import hr.javafx.domain.records.Post;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static String USERS_TEXT_FILE_NAME = "dat/users.txt";
    private static String TOPICS_TEXT_FILE_NAME = "dat/topics.txt";
    private static String POSTS_TEXT_FILE_NAME = "dat/posts.txt";


    //User
    public static List<User> readUsersFromFile() {


        List<User> users = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_TEXT_FILE_NAME))) {
            String line;
            while (Optional.ofNullable(line = reader.readLine()).isPresent()) {

                Integer id = Integer.parseInt(line);
                String username = reader.readLine();
                String passwordHash = reader.readLine();
                String userRole = reader.readLine();

                UserRole role = UserRole.GUEST;

                if(userRole.equalsIgnoreCase("ADMIN")) {
                    role = UserRole.ADMIN;
                }


                Optional<User> newUserOptional = Optional.of(new User(id, username, passwordHash, role));

                if(newUserOptional.isPresent()) {
                    User newUser = newUserOptional.get();
                    users.add(newUser);
                }
            }

        } catch(IOException e) {
            String message = "Error while reading users from a file.";
            System.out.println(message);
            logger.error(message, e);
        }

        return users;
    }

    public static Integer getNextUserId() {
        List<User> userList = readUsersFromFile();

        Integer id = userList.stream().map(user -> user.getId()).max((u1, u2) -> u1.compareTo(u2)).get();

        return id+1;
    }

    public static void saveUsersToFile(List<User> userList) {
        File userFile = new File(USERS_TEXT_FILE_NAME);

        try(PrintWriter pw = new PrintWriter(userFile)) {

            for(User user : userList) {
                pw.println(user.getId());
                pw.println(user.getUsername());
                pw.println(user.getPasswordHash());
                pw.println(user.getRole().toString());
            }
        }

        catch (IOException e) {
            String message = "Error while writing users to a file.";
            System.out.println(message);
            logger.error(message, e);
        }
     }

     //Topic

    public static List<Topic> readTopicsFromFile() {


        List<Topic> topics = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TOPICS_TEXT_FILE_NAME))) {
            String line;
            while (Optional.ofNullable(line = reader.readLine()).isPresent()) {

                String name = line;
                String description = reader.readLine();

                Optional<Topic> newTopicOptional = Optional.of(new Topic.Builder(name).withDescription(description).build());

                if(newTopicOptional.isPresent()) {
                    Topic newTopic = newTopicOptional.get();
                    topics.add(newTopic);
                }
            }

        } catch(IOException e) {
            String message = "Error while reading topics from a file.";
            System.out.println(message);
            logger.error(message, e);
        }

        return topics;
    }

    public static void saveTopicsToFile(List<Topic> topicList) {
        File userFile = new File(TOPICS_TEXT_FILE_NAME);

        try(PrintWriter pw = new PrintWriter(userFile)) {

            for(Topic topic : topicList) {
                pw.println(topic.getName());
                pw.println(topic.getDescription());

            }
        }

        catch (IOException e) {
            String message = "Error while writing topics to a file.";
            System.out.println(message);
            logger.error(message, e);
        }
    }


    //Post

    public static List<Post> readPostFromFile() {


        List<Post> posts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(POSTS_TEXT_FILE_NAME))) {
            String line;
            while (Optional.ofNullable(line = reader.readLine()).isPresent()) {

                String topicString = line;
                String title = reader.readLine();
                String content = reader.readLine();

                List<Topic> topicList = readTopicsFromFile();
                Topic topic = null;

                for(Topic t : topicList) {
                    if(Objects.equals(t.getName(), topicString)) {
                        topic = t;
                    }
                }


                if(topic == null) {
                    throw new TopicIsNullException("Topic is null");
                }

                Optional<Post> newPostOptional = Optional.of(new Post(topic, title, content));

                if(newPostOptional.isPresent()) {
                    Post newPost = newPostOptional.get();
                    posts.add(newPost);
                }
            }

        } catch(IOException e) {
            String message = "Error while reading posts from a file.";
            System.out.println(message);
            logger.error(message, e);
        }
        catch (TopicIsNullException e) {
            logger.error(e.getMessage());
        }

        return posts;
    }

    public static void savePostToFile(List<Post> postList) {
        File userFile = new File(POSTS_TEXT_FILE_NAME);

        try(PrintWriter pw = new PrintWriter(userFile)) {

            for(Post post : postList) {
                pw.println(post.topic().getName());
                pw.println(post.title());
                pw.println(post.content());

            }
        }

        catch (IOException e) {
            String message = "Error while writing posts to a file.";
            System.out.println(message);
            logger.error(message, e);
        }
    }
}
