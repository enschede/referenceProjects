package com.example;

import javax.persistence.*;

@Entity
@Table(name = "TABLE_A")
@AttributeOverrides({
        @AttributeOverride(name = "id", column=@Column(name="A_ID")),
        @AttributeOverride(name = "id2", column=@Column(name="A_ID2", nullable = false))})
public class TypeA extends Generic {

    @Column(name = "naampjeA")
    private String naamA;

    public TypeA() {
        super();
    }

    public TypeA(Long id, Long id2, String naamA) {
        super(id, id2);
        this.naamA = naamA;
    }

    public String getNaamA() {
        return naamA;
    }

    public void setNaamA(String naamA) {
        this.naamA = naamA;
    }

}
