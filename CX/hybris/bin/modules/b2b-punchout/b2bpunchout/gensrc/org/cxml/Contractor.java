//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "contractorIdentifier",
    "contact"
})
@XmlRootElement(name = "Contractor")
public class Contractor {

    @XmlElement(name = "ContractorIdentifier", required = true)
    protected ContractorIdentifier contractorIdentifier;
    @XmlElement(name = "Contact", required = true)
    protected Contact contact;

    /**
     * Obtient la valeur de la propriété contractorIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link ContractorIdentifier }
     *     
     */
    public ContractorIdentifier getContractorIdentifier() {
        return contractorIdentifier;
    }

    /**
     * Définit la valeur de la propriété contractorIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractorIdentifier }
     *     
     */
    public void setContractorIdentifier(ContractorIdentifier value) {
        this.contractorIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété contact.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Définit la valeur de la propriété contact.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

}
