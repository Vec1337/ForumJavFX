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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOldValue() {
        return oldValue;
    }


    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
