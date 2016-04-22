package com.example;

import javax.persistence.*;

@Entity
@Table(name = "TABLE_B")
@AttributeOverrides({
        @AttributeOverride(name = "id", column=@Column(name="B_ID")),
        @AttributeOverride(name = "id2", column=@Column(name="B_ID2"))})
public class TypeB extends Generic {

    private String naamB;

    public TypeB() {
        super();
    }

    public TypeB(Long id, String naamB) {
        super(id, null);
        this.naamB = naamB;
    }

    public String getNaamB() {
        return naamB;
    }

    public void setNaamB(String naamA) {
        this.naamB = naamA;
    }

}
