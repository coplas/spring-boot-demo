package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author CoPLaS
 */
@Entity
public class DummyModelItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DUMMYMODEL_ID", foreignKey = @ForeignKey(name = "FK_DUMMYMODELITEM_DUMMYMODEL_ID"))
    @NotNull
    private DummyModel dummyModel;
    private String phone;
    private String email;

    protected DummyModelItem() {
    }

    public DummyModelItem(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DummyModel getDummyModel() {
        return dummyModel;
    }

    public void setDummyModel(DummyModel dummyModel) {
        this.dummyModel = dummyModel;
    }

    @Override
    public String toString() {
        return String.format(
                "DummyModelItem[id=%d, phone='%s', email='%s']",
                id, phone, email);
    }

}
