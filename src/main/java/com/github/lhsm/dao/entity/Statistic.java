package com.github.lhsm.dao.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Statistic implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Redirect redirect;

    protected long count;

    public Statistic() {
    }

    public Statistic(Redirect redirect) {
        this.redirect = redirect;
    }

    public Long getId() {
        return id;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("redirect", redirect)
                .add("count", count)
                .toString();
    }

}
