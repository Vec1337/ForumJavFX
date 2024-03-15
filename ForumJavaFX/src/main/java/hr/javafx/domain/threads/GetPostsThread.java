package hr.javafx.domain.threads;

import hr.javafx.domain.entities.Post;
import hr.javafx.domain.entities.Topic;

import java.util.Set;

public class GetPostsThread extends DatabaseThread implements Runnable{

    Set<Post> result;

    @Override
    public void run() {
        result = super.getPosts();
    }

    public Set<Post> getResult() {
        return result;
    }

}
