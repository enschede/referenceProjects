
package nl.marcenschede.springtest.stax.reader.valueobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://schema.marcenschede.nl/schema/v1_0}header"/>
 *         &lt;element name="itemlist" type="{http://schema.marcenschede.nl/schema/v1_0}itemlist"/>
 *         &lt;element name="footer" type="{http://schema.marcenschede.nl/schema/v1_0}footer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "itemlist",
    "footer"
})
@XmlRootElement(name = "data", namespace = "http://schema.marcenschede.nl/schema/v1_0")
public class Data {

    @XmlElement(namespace = "http://schema.marcenschede.nl/schema/v1_0", required = true)
    protected Header header;
    @XmlElement(namespace = "http://schema.marcenschede.nl/schema/v1_0", required = true)
    protected Itemlist itemlist;
    @XmlElement(namespace = "http://schema.marcenschede.nl/schema/v1_0", required = true)
    protected Footer footer;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the itemlist property.
     * 
     * @return
     *     possible object is
     *     {@link Itemlist }
     *     
     */
    public Itemlist getItemlist() {
        return itemlist;
    }

    /**
     * Sets the value of the itemlist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Itemlist }
     *     
     */
    public void setItemlist(Itemlist value) {
        this.itemlist = value;
    }

    /**
     * Gets the value of the footer property.
     * 
     * @return
     *     possible object is
     *     {@link Footer }
     *     
     */
    public Footer getFooter() {
        return footer;
    }

    /**
     * Sets the value of the footer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Footer }
     *     
     */
    public void setFooter(Footer value) {
        this.footer = value;
    }

}
