/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.ejbtest.timer;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author marc
 */
@Stateless
@Interceptors(InterceptorBean.class)
public class InterceptedBean {
    private static final Logger LOG = Logger.getLogger(InterceptedBean.class.getName());

    public void go() {
        LOG.info("InterceptedBean::go started");
        
        return;
    }
}
