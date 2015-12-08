/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.jmstest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jws.WebService;

/**
 *
 * @author marc
 */
@Stateless
@WebService
public class Creator {
    @Resource(mappedName = "jms/t1")
    private Topic t1;
//    @Resource(mappedName = "jms/t1Factory")
//    private ConnectionFactory t1Factory;
    @Resource(mappedName = "jms/q1")
    private Queue q1;
    @Resource(mappedName = "jms/q1Factory")
    private ConnectionFactory q1Factory;
    private static final Logger LOG = Logger.getLogger(Creator.class.getName());

    public void send(final String msg) {
        try {
            LOG.log(Level.INFO, "Creator::send::msg={0}", msg);
            sendJMSMessageToQ1(msg);
            sendJMSMessageToT1(msg);
        } catch (JMSException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Message createJMSMessageForjmsQ1(Session session, String messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData);
        return tm;
    }

    private void sendJMSMessageToQ1(String messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = q1Factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(q1);
            messageProducer.send(createJMSMessageForjmsQ1(session, messageData));
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

    private Message createJMSMessageForjmsT1(Session session, String messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData);
        return tm;
    }

    private void sendJMSMessageToT1(String messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = q1Factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(t1);
            messageProducer.send(createJMSMessageForjmsT1(session, messageData));
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
