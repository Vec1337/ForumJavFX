package hr.javafx.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Change implements Serializable {

    private String field;
    private String oldValue;
    private String newValue;
    private LocalDateTime timestamp;

    public Change(String field, String oldValue, String newValue, LocalDateTime timestamp) {
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.timestamp = timestamp;
    }


}
