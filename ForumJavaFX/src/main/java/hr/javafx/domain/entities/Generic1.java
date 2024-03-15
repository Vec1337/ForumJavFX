package hr.javafx.domain.entities;

public final class Generic1<T extends Entity>  {

    private T entity;

    public Generic1(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
