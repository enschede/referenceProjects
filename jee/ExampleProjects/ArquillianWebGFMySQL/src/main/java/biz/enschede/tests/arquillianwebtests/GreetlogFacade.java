/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.arquillianwebtests;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marc
 */
@Stateless
public class GreetlogFacade extends AbstractFacade<Greetlog> {
    @PersistenceContext(unitName = "Greetlog")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GreetlogFacade() {
        super(Greetlog.class);
    }
    
}
