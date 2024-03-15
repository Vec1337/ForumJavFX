package hr.javafx.domain.utils;

import hr.javafx.domain.entities.Post;
import hr.javafx.domain.entities.Topic;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class DatabaseUtils {

    private static final String DATABASE_FILE = "conf/database.properties";

    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String databaseUrl = svojstva.getProperty("databaseUrl");
        String username = svojstva.getProperty("username");
        String password = svojstva.getProperty("password");

        Connection veza = DriverManager.getConnection(databaseUrl, username, password);

        return veza;

    }


    public static Set<Topic> getTopics() {
        Set<Topic> topicSet = new HashSet<>();

        try(Connection connection = connectToDatabase()) {

            String sqlQuery = "SELECT * FROM TOPICS;";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlQuery);

            ResultSet rs = stmt.getResultSet();

            while(rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");

                Topic topic = new Topic.Builder(name).withDescription(description).build();
                topicSet.add(topic);
            }

        }
        catch (SQLException | IOException ex) {
            String message = "Error while getting topics from database!";
            System.out.println(ex);
            System.out.println(message);
        }
        return topicSet;
    }

    public static Integer getTopicID(String topicName) {
        Integer id = 0;
        try(Connection connection = connectToDatabase()) {

            String insertCategorySql = "SELECT * FROM TOPICS WHERE NAME = ?;";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);
            pstmt.setString(1, topicName);;

            System.out.println(topicName);

            pstmt.execute();;

            ResultSet rs = pstmt.getResultSet();

            while(rs.next()) {
                 id = rs.getInt("ID");
                 return id;
            }

        }
        catch (SQLException | IOException ex) {
            String message = "Error while getting topics from database!";
            System.out.println(ex);
            System.out.println(message);
        }
        return id;
    }

    public static Topic getTopicByID(Integer topicID) {

        try(Connection connection = connectToDatabase()) {

            String insertCategorySql = "SELECT * FROM TOPICS WHERE ID = ?;";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);
            pstmt.setString(1, String.valueOf(topicID));;


            pstmt.execute();;

            ResultSet rs = pstmt.getResultSet();

            while(rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");

                Topic topic = new Topic.Builder(name).withDescription(description).build();

                return topic;
            }

        }
        catch (SQLException | IOException ex) {
            String message = "Error while getting topics from database!";
            System.out.println(ex);
            System.out.println(message);
        }
        return null;
    }

    public static void saveTopic(Topic topic) {
        try (Connection connection = connectToDatabase()) {

            String insertCategorySql = "INSERT INTO TOPICS(NAME, DESCRIPTION) VALUES(?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);
            pstmt.setString(1, topic.getName());
            pstmt.setString(2, topic.getDescription());

            pstmt.execute();

        }
        catch (SQLException | IOException ex) {
            String message = "Error while adding topic to database!";
            System.out.println(ex);
            System.out.println(message);
        }
    }
    public static void changeTopicName(String newName, String oldName) {
        try (Connection connection = connectToDatabase()) {

            String insertCategorySql = "UPDATE Topics SET NAME = ? WHERE NAME = ?;";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);
            pstmt.setString(1, newName);
            pstmt.setString(2, oldName);

            pstmt.execute();

        }
        catch (SQLException | IOException ex) {
            String message = "Error while change topic in database!";
            System.out.println(ex);
            System.out.println(message);
        }
    }

    public static void deleteTopic(String name) {
        try (Connection connection = connectToDatabase()) {

            String insertCategorySql = "DELETE FROM TOPICS WHERE NAME = ?;";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);
            pstmt.setString(1, name);


            pstmt.execute();

        }
        catch (SQLException | IOException ex) {
            String message = "Error while change topic in database!";
            System.out.println(ex);
            System.out.println(message);
        }
    }

    public static void changeTopicDescription(String newDescription, String oldDescription) {
        try (Connection connection = connectToDatabase()) {

            String insertCategorySql = "UPDATE Topics SET DESCRIPTION = ? WHERE DESCRIPTION = ?;";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);
            pstmt.setString(1, newDescription);
            pstmt.setString(2, oldDescription);

            pstmt.execute();

        }
        catch (SQLException | IOException ex) {
            String message = "Error while changing topic in database!";
            System.out.println(ex);
            System.out.println(message);
        }
    }


    public static Set<Post> getPosts() {
        Set<Post> postSet = new HashSet<>();

        try(Connection connection = connectToDatabase()) {

            String sqlQuery = "SELECT * FROM POSTS;";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlQuery);

            ResultSet rs = stmt.getResultSet();

            while(rs.next()) {
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Integer topicId = rs.getInt("TOPIC_ID");

                Topic topic = getTopicByID(topicId);

                Post post = new Post(topic, title, content);

                postSet.add(post);
            }

        }
        catch (SQLException | IOException ex) {
            String message = "Error while getting posts from database!";
            System.out.println(ex);
            System.out.println(message);
        }
        return postSet;
    }

    public static void savePost(Post post) {
        try (Connection connection = connectToDatabase()) {

            String insertCategorySql = "INSERT INTO POSTS(TOPIC_ID, TITLE, CONTENT) VALUES(?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(insertCategorySql);

            Integer id = getTopicID(post.getTopic().getName());

            System.out.println(id);

            pstmt.setString(1, String.valueOf(id));
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());

            pstmt.execute();

        }
        catch (SQLException | IOException ex) {
            String message = "Error while adding post to database!";
            System.out.println(ex);
            System.out.println(message);
        }
    }

}
