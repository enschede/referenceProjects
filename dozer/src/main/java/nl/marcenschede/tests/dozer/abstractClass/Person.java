package nl.marcenschede.tests.dozer.abstractClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marc on 30/09/15.
 */
public class Person {
    
    private String name;
    private List<Voorkeur> voorkeuren = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Voorkeur> getVoorkeuren() {
        return voorkeuren;
    }

    public void setVoorkeuren(List<Voorkeur> voorkeuren) {
        this.voorkeuren = voorkeuren;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", voorkeuren=" + voorkeuren +
                '}';
    }
}
