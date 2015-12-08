/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.jmstest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author marc
 */
@MessageDriven(mappedName = "jms/q2", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class QueueBean2 implements MessageListener {
    private static final Logger LOG = Logger.getLogger(QueueBean2.class.getName());
    
    public QueueBean2() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            String msg = ((TextMessage) message).getText();
            LOG.log(Level.INFO, "QueueBean2::onMessage::msg={0}", msg);
        } catch (JMSException ex) {
            Logger.getLogger(QueueBean2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
