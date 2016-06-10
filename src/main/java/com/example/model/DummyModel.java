package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoPLaS
 */
@Entity
public class DummyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dummyModel")
    private List<DummyModelItem> items = new ArrayList<>();

    protected DummyModel() {
    }

    public DummyModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<DummyModelItem> getItems() {
        return items;
    }

    public void setItems(List<DummyModelItem> items) {
        this.items = items;
    }

    public void addItem(DummyModelItem item) {
        item.setDummyModel(this);
        getItems().add(item);
    }

    @Override
    public String toString() {
        return String.format(
                "DummyModel[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
