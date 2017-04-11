package com.github.lhsm.dao.entity;

import com.google.common.base.MoreObjects;
import org.springframework.http.HttpStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "url"})})
public class Redirect implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String url;

    private HttpStatus type;

    protected Redirect() {
    }

    public Redirect(Account account, String url, HttpStatus type) {
        this.account = account;
        this.url = url;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getUrl() {
        return url;
    }

    public HttpStatus getType() {
        return type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("account", account)
                .add("url", url)
                .add("type", type)
                .toString();
    }

}
