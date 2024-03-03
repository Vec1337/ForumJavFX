package hr.javafx.domain.records;

import hr.javafx.domain.entities.Topic;

public record Post(Topic topic, String title, String content) {
}
