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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse"
})
@XmlRootElement(name = "Response")
public class Response {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "Status", required = true)
    protected Status status;
    @XmlElements({
        @XmlElement(name = "ProfileResponse", type = ProfileResponse.class),
        @XmlElement(name = "PunchOutSetupResponse", type = PunchOutSetupResponse.class),
        @XmlElement(name = "ProviderSetupResponse", type = ProviderSetupResponse.class),
        @XmlElement(name = "GetPendingResponse", type = GetPendingResponse.class),
        @XmlElement(name = "SubscriptionListResponse", type = SubscriptionListResponse.class),
        @XmlElement(name = "SubscriptionContentResponse", type = SubscriptionContentResponse.class),
        @XmlElement(name = "SupplierListResponse", type = SupplierListResponse.class),
        @XmlElement(name = "SupplierDataResponse", type = SupplierDataResponse.class),
        @XmlElement(name = "AuthResponse", type = AuthResponse.class),
        @XmlElement(name = "DataResponse", type = DataResponse.class),
        @XmlElement(name = "OrganizationDataResponse", type = OrganizationDataResponse.class)
    })
    protected List<Object> profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     * Gets the value of the profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProfileResponse }
     * {@link PunchOutSetupResponse }
     * {@link ProviderSetupResponse }
     * {@link GetPendingResponse }
     * {@link SubscriptionListResponse }
     * {@link SubscriptionContentResponse }
     * {@link SupplierListResponse }
     * {@link SupplierDataResponse }
     * {@link AuthResponse }
     * {@link DataResponse }
     * {@link OrganizationDataResponse }
     * 
     * 
     */
    public List<Object> getProfileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse() {
        if (profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse == null) {
            profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse = new ArrayList<Object>();
        }
        return this.profileResponseOrPunchOutSetupResponseOrProviderSetupResponseOrGetPendingResponseOrSubscriptionListResponseOrSubscriptionContentResponseOrSupplierListResponseOrSupplierDataResponseOrAuthResponseOrDataResponseOrOrganizationDataResponse;
    }

}
