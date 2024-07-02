package hr.java.car.domain;

public enum CarTypeCateogry {

    FUEL(1), ELECTRIC(2);

    private Integer code;

    private CarTypeCateogry(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
