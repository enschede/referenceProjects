package com.example;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Generic {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id", nullable = false)
    private Long id2;

    public Generic(Long id, Long id2) {
        this.id = id;
        this.id2 = id2;
    }

    public Generic() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }
}
