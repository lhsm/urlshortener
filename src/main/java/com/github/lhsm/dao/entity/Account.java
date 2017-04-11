package com.github.lhsm.dao.entity;

import com.google.common.base.MoreObjects;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account implements Persistable<String> {

    @Id
    private String id;

    private String password;

    protected Account() {
    }

    public Account(String id) {
        this.id = id;
    }

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password", password)
                .toString();
    }

}
