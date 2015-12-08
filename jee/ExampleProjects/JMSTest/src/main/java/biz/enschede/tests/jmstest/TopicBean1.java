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
@MessageDriven(mappedName = "jms/t1", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/t1"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/t1")
})
public class TopicBean1 implements MessageListener {
    private static final Logger LOG = Logger.getLogger(TopicBean1.class.getName());
    
    public TopicBean1() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            String msg = ((TextMessage)message).getText();
            LOG.log(Level.INFO, "TopicBean1::onMessage::msg={0}", msg);
        } catch (JMSException ex) {
            Logger.getLogger(TopicBean1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
