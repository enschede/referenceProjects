/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.enschede.tests.arquillianwebtests;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author marc
 */
@ManagedBean
@RequestScoped
public class GreeterBean {

    @EJB
    private Greeter greeter;

    private String name;

    /**
     * Creates a new instance of GreeterBean
     */
    public GreeterBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String greet() {
        greeter.greet(name);

        return null;
    }
}
