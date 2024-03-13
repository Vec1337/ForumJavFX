package hr.javafx.domain.entities;

public class Post {
    Topic topic;
    String title;
    String content;

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
}
