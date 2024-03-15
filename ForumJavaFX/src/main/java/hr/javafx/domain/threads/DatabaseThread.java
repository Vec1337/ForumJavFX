package hr.javafx.domain.threads;

import hr.javafx.domain.entities.Post;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.utils.DatabaseUtils;

import java.util.List;
import java.util.Set;

public abstract class DatabaseThread {

    boolean activeConnectionWithDatabase = false;

    public synchronized Set<Topic> getTopics() {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        activeConnectionWithDatabase = true;

        Set<Topic> topicSet = DatabaseUtils.getTopics();

        activeConnectionWithDatabase = false;

        notifyAll();

        return topicSet;

    }

    public synchronized Set<Post> getPosts() {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        activeConnectionWithDatabase = true;

        Set<Post> postSet = DatabaseUtils.getPosts();

        activeConnectionWithDatabase = false;

        notifyAll();

        return postSet;

    }

}
