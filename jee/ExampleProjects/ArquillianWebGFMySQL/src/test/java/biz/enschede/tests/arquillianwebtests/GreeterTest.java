/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.enschede.tests.arquillianwebtests;

import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author marc
 */
@RunWith(Arquillian.class)
public class GreeterTest {

    @EJB
    private Greeter greeter;

    @Deployment
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, Greeter.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(war.toString(true));

        return war;
    }

    @Test
    public void greeterTest() {

        Assert.assertEquals("Hello Marc!!", greeter.greet("Marc"));

    }
}
