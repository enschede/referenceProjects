/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.ejbtest.timer;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author marc
 */
@Stateless
public class InterceptorBean {
    private static final Logger LOG = Logger.getLogger(InterceptorBean.class.getName());

    @AroundInvoke
    public Object invocation(InvocationContext c) throws Exception {
        LOG.info(("Before execution of intercepted method"));
        
        Object result = c.proceed();
        
        LOG.info(("After execution of intercepted method"));
        
        return result;
    }
    
}
