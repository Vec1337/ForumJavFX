package hr.javafx.domain.entities;

import java.io.Serializable;

public  class Topic implements Serializable {

    private String name;
    private String description;

    /*
    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

     */

    public static class Builder {
        private String name;
        private String description;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Topic build() {
            Topic topic = new Topic();

            topic.name = this.name;
            topic.description = this.description;

            return topic;
        }
    }

    private Topic() {

    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
