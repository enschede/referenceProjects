/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.arquillianwebtests;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marc
 */
@Stateless
public class Greeter {

    @EJB
    private GreetlogFacade greetlogFacade;
    
    public String greet(String name) {
        String greet = "Hello " + name + "!!";
        
        Greetlog greetlog = new Greetlog();
        greetlog.setGreeting(greet);
        greetlogFacade.create(greetlog);
        
        return greet;
    }

}
