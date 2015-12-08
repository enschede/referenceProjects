package biz.enschede.tests.jbossmysql2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marc
 */
@Stateless
public class CustomerManagement {
    private static final Logger LOG = Logger.getLogger(CustomerManagement.class.getName());

    @EJB
    private CustomerFacade customerFacade;

    public void addCustomer(String name) {
        LOG.log(Level.INFO, "Entered with name = {0}", name);
        
        Customer customer = new Customer();
        customer.setName(name);
        customerFacade.create(customer);
        
        for(Customer c : customerFacade.findAll() )
            LOG.log(Level.INFO, "Entry is {0}", c.getName());
        
    }
}
