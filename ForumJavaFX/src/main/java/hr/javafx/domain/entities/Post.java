package hr.javafx.domain.entities;

public class Post {

    private Topic topic;
    private String title;
    private String content;
    private User author;

    public Post(Topic topic, String title, String content) {
        this.topic = topic;
        this.title = title;
        this.content = content;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
