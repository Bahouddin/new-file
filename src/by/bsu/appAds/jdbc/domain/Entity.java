package by.bsu.appAds.jdbc.domain;

abstract public class Entity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer identity) {
        this.id = identity;
    }
}