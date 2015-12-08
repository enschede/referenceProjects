/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.enschede.tests.ejbtest.timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author marc
 */
@Stateless
public class TimerBean {

    private static final Logger LOG = Logger.getLogger(TimerBean.class.getName());

    @EJB
    private InterceptedBean interceptedBean;

    @Schedule(second = "0", minute = "0", hour = "*")
    public void timerBean() {
        LOG.info("TimerBean::timerBean started");

        interceptedBean.go();

        PrintStream p;
        try {
            File f = new File("output.txt");
            p = new PrintStream(f);
            p.println("Data to file");
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TimerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
