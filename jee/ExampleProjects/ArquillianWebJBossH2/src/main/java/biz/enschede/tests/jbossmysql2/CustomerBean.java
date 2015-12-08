/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.jbossmysql2;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author marc
 */
@ManagedBean
@RequestScoped
public class CustomerBean {

    @EJB
    private CustomerManagement customerManagement;
    private String name;
    
    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String store() {
        customerManagement.addCustomer(name);
        return null;
    }
}
