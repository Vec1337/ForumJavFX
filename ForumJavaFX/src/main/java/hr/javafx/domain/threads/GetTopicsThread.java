package hr.javafx.domain.threads;

import hr.javafx.domain.entities.Topic;

import java.util.Set;

public class GetTopicsThread extends DatabaseThread implements Runnable{

    Set<Topic> result;

    @Override
    public void run() {
        result = super.getTopics();
    }

    public Set<Topic> getResult() {
        return result;
    }

}
