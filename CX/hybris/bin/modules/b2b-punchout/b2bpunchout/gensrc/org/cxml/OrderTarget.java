//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "phoneOrEmailOrFaxOrURLOrOtherOrderTarget"
})
@XmlRootElement(name = "OrderTarget")
public class OrderTarget {

    @XmlElements({
        @XmlElement(name = "Phone", required = true, type = Phone.class),
        @XmlElement(name = "Email", required = true, type = Email.class),
        @XmlElement(name = "Fax", required = true, type = Fax.class),
        @XmlElement(name = "URL", required = true, type = URL.class),
        @XmlElement(name = "OtherOrderTarget", required = true, type = OtherOrderTarget.class)
    })
    protected List<Object> phoneOrEmailOrFaxOrURLOrOtherOrderTarget;

    /**
     * Gets the value of the phoneOrEmailOrFaxOrURLOrOtherOrderTarget property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneOrEmailOrFaxOrURLOrOtherOrderTarget property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneOrEmailOrFaxOrURLOrOtherOrderTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Phone }
     * {@link Email }
     * {@link Fax }
     * {@link URL }
     * {@link OtherOrderTarget }
     * 
     * 
     */
    public List<Object> getPhoneOrEmailOrFaxOrURLOrOtherOrderTarget() {
        if (phoneOrEmailOrFaxOrURLOrOtherOrderTarget == null) {
            phoneOrEmailOrFaxOrURLOrOtherOrderTarget = new ArrayList<Object>();
        }
        return this.phoneOrEmailOrFaxOrURLOrOtherOrderTarget;
    }

}
