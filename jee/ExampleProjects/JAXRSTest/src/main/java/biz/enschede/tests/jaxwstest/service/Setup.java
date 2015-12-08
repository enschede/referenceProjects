/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.enschede.tests.jaxwstest.service;

import biz.enschede.tests.jaxwstest.data.Customer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author marc
 */
@Singleton
@Startup
public class Setup {

    @EJB
    private CustomerFacadeREST customerFacade;

    @PostConstruct
    public void init() {
        Customer c1 = new Customer();
        c1.setName("Marc Enschede");
        c1.setAddress("Enschede");
        customerFacade.create(c1);

        Customer c2 = new Customer();
        c2.setName("Kitty Enschede");
        c2.setAddress("Enschede");
        customerFacade.create(c2);

        Customer c3 = new Customer();
        c3.setName("Maruice Enschede");
        c3.setAddress("Enschede");
        customerFacade.create(c3);

        Customer c4 = new Customer();
        c4.setName("Yvette Enschede");
        c4.setAddress("Enschede");
        customerFacade.create(c4);
    }

}
