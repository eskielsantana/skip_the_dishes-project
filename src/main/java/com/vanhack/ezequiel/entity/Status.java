package com.vanhack.ezequiel.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="status")
@NamedQueries({})

public class Status implements Serializable {

    public static final int NEW = 0;
    public static final int PAID = 1;
    public static final int CONFIRMED = 2;
    public static final int DELIVERED = 3;
    public static final int CANCELLED = 4;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    public Status() {}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
