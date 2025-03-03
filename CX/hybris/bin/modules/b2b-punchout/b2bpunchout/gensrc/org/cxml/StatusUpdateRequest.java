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
    "documentReference",
    "status",
    "paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus",
    "extrinsic"
})
@XmlRootElement(name = "StatusUpdateRequest")
public class StatusUpdateRequest {

    @XmlElement(name = "DocumentReference")
    protected DocumentReference documentReference;
    @XmlElement(name = "Status", required = true)
    protected Status status;
    @XmlElements({
        @XmlElement(name = "PaymentStatus", type = PaymentStatus.class),
        @XmlElement(name = "SourcingStatus", type = SourcingStatus.class),
        @XmlElement(name = "InvoiceStatus", type = InvoiceStatus.class),
        @XmlElement(name = "DocumentStatus", type = DocumentStatus.class),
        @XmlElement(name = "IntegrationStatus", type = IntegrationStatus.class)
    })
    protected List<Object> paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété documentReference.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReference }
     *     
     */
    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    /**
     * Définit la valeur de la propriété documentReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReference }
     *     
     */
    public void setDocumentReference(DocumentReference value) {
        this.documentReference = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentStatus }
     * {@link SourcingStatus }
     * {@link InvoiceStatus }
     * {@link DocumentStatus }
     * {@link IntegrationStatus }
     * 
     * 
     */
    public List<Object> getPaymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus() {
        if (paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus == null) {
            paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus = new ArrayList<Object>();
        }
        return this.paymentStatusOrSourcingStatusOrInvoiceStatusOrDocumentStatusOrIntegrationStatus;
    }

    /**
     * Gets the value of the extrinsic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extrinsic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtrinsic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extrinsic }
     * 
     * 
     */
    public List<Extrinsic> getExtrinsic() {
        if (extrinsic == null) {
            extrinsic = new ArrayList<Extrinsic>();
        }
        return this.extrinsic;
    }

}
