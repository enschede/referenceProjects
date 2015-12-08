/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.enschede.tests.jmstest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author marc
 */
@MessageDriven(mappedName = "jms/q1", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class QueueBean1 implements MessageListener {
    private static final Logger LOG = Logger.getLogger(QueueBean1.class.getName());

    @Resource(mappedName = "jms/q2")
    private Queue q2;
    @Resource(mappedName = "jms/q2Factory")
    private ConnectionFactory q2Factory;

    public QueueBean1() {
    }

    @Override
    public void onMessage(Message message) {

        try {
            String msg = ((TextMessage) message).getText();
            LOG.log(Level.INFO, "QueueBean1::onMessage::msg={0}", msg);
            sendJMSMessageToQ2(msg + "*");
        } catch (JMSException ex) {
            Logger.getLogger(QueueBean1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Message createJMSMessageForjmsQ2(Session session, String messageData) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData);
        return tm;
    }

    private void sendJMSMessageToQ2(String messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = q2Factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(q2);
            messageProducer.send(createJMSMessageForjmsQ2(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
