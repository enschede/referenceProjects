package nl.marcenschede.springtest.stax.pain;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import nl.marcenschede.springtest.stax.pain.xsd.Authorisation1Choice;
import nl.marcenschede.springtest.stax.pain.xsd.BranchAndFinancialInstitutionIdentification4;
import nl.marcenschede.springtest.stax.pain.xsd.GroupHeader39;
import nl.marcenschede.springtest.stax.pain.xsd.PartyIdentification32;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by marc on 25/05/15.
 */
public class GroupHeader39Builder {

    private GroupHeader39 groupHeader39 = new GroupHeader39();

    public GroupHeader39Builder() {
    }
    
    public GroupHeader39Builder setMsgId(String msgId) {
        groupHeader39.setMsgId(msgId);
        return this;
    }

    public GroupHeader39Builder setNbOfTxs(String nbOfTxs) {
        groupHeader39.setNbOfTxs(nbOfTxs);
        return this;
    }

    public GroupHeader39Builder setCtrlSum(BigDecimal ctrlSum) {
        groupHeader39.setCtrlSum(ctrlSum);
        return this;
    }

    public GroupHeader39Builder setXmlGregorianCalendar(LocalDateTime localDateTime) {
        GregorianCalendar c = new GregorianCalendar();
        c.set(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth(), 0, 0);

        try {
            groupHeader39.setCreDtTm(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        
        return this;
    }

    public GroupHeader39Builder setNm(String name) {
        if(groupHeader39.getInitgPty()==null)
            groupHeader39.setInitgPty(new PartyIdentification32());
        
        PartyIdentification32 partyIdentification32 = groupHeader39.getInitgPty();
        partyIdentification32.setNm(name);
        
        return this;
    }

    public GroupHeader39 build() {
        return groupHeader39;
    }
    
    public static GroupHeader39Builder with() {
        return new GroupHeader39Builder();
    }
}
