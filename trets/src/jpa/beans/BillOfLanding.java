/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "BILL_OF_LANDING", catalog = "", schema = "PPNCARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillOfLanding.findAll", query = "SELECT b FROM BillOfLanding b")
    , @NamedQuery(name = "BillOfLanding.findByIdBol", query = "SELECT b FROM BillOfLanding b WHERE b.idBol = :idBol")
    , @NamedQuery(name = "BillOfLanding.findByPcIndicator", query = "SELECT b FROM BillOfLanding b WHERE b.pcIndicator = :pcIndicator")
    , @NamedQuery(name = "BillOfLanding.findByBolNature", query = "SELECT b FROM BillOfLanding b WHERE b.bolNature = :bolNature")
    , @NamedQuery(name = "BillOfLanding.findByBolReference", query = "SELECT b FROM BillOfLanding b WHERE b.bolReference = :bolReference")
    , @NamedQuery(name = "BillOfLanding.findByBolTypeCode", query = "SELECT b FROM BillOfLanding b WHERE b.bolTypeCode = :bolTypeCode")
    , @NamedQuery(name = "BillOfLanding.findByConsigneeAddress", query = "SELECT b FROM BillOfLanding b WHERE b.consigneeAddress = :consigneeAddress")
    , @NamedQuery(name = "BillOfLanding.findByConsigneeCode", query = "SELECT b FROM BillOfLanding b WHERE b.consigneeCode = :consigneeCode")
    , @NamedQuery(name = "BillOfLanding.findByConsigneeName", query = "SELECT b FROM BillOfLanding b WHERE b.consigneeName = :consigneeName")
    , @NamedQuery(name = "BillOfLanding.findByCustomsCurrency", query = "SELECT b FROM BillOfLanding b WHERE b.customsCurrency = :customsCurrency")
    , @NamedQuery(name = "BillOfLanding.findByCustomsValue", query = "SELECT b FROM BillOfLanding b WHERE b.customsValue = :customsValue")
    , @NamedQuery(name = "BillOfLanding.findByExporterAddress", query = "SELECT b FROM BillOfLanding b WHERE b.exporterAddress = :exporterAddress")
    , @NamedQuery(name = "BillOfLanding.findByExporterCode", query = "SELECT b FROM BillOfLanding b WHERE b.exporterCode = :exporterCode")
    , @NamedQuery(name = "BillOfLanding.findByExporterName", query = "SELECT b FROM BillOfLanding b WHERE b.exporterName = :exporterName")
    , @NamedQuery(name = "BillOfLanding.findByFreightCurrency", query = "SELECT b FROM BillOfLanding b WHERE b.freightCurrency = :freightCurrency")
    , @NamedQuery(name = "BillOfLanding.findByFreightValue", query = "SELECT b FROM BillOfLanding b WHERE b.freightValue = :freightValue")
    , @NamedQuery(name = "BillOfLanding.findByGoodsDescription", query = "SELECT b FROM BillOfLanding b WHERE b.goodsDescription = :goodsDescription")
    , @NamedQuery(name = "BillOfLanding.findByGrossMass", query = "SELECT b FROM BillOfLanding b WHERE b.grossMass = :grossMass")
    , @NamedQuery(name = "BillOfLanding.findByInformation", query = "SELECT b FROM BillOfLanding b WHERE b.information = :information")
    , @NamedQuery(name = "BillOfLanding.findByInsuranceCurrency", query = "SELECT b FROM BillOfLanding b WHERE b.insuranceCurrency = :insuranceCurrency")
    , @NamedQuery(name = "BillOfLanding.findByInsuranceValue", query = "SELECT b FROM BillOfLanding b WHERE b.insuranceValue = :insuranceValue")
    , @NamedQuery(name = "BillOfLanding.findByLineNumber", query = "SELECT b FROM BillOfLanding b WHERE b.lineNumber = :lineNumber")
    , @NamedQuery(name = "BillOfLanding.findByLocationCode", query = "SELECT b FROM BillOfLanding b WHERE b.locationCode = :locationCode")
    , @NamedQuery(name = "BillOfLanding.findByLocationInfo", query = "SELECT b FROM BillOfLanding b WHERE b.locationInfo = :locationInfo")
    , @NamedQuery(name = "BillOfLanding.findByMasterBolRefNumber", query = "SELECT b FROM BillOfLanding b WHERE b.masterBolRefNumber = :masterBolRefNumber")
    , @NamedQuery(name = "BillOfLanding.findByNotifyAddress", query = "SELECT b FROM BillOfLanding b WHERE b.notifyAddress = :notifyAddress")
    , @NamedQuery(name = "BillOfLanding.findByNotifyCode", query = "SELECT b FROM BillOfLanding b WHERE b.notifyCode = :notifyCode")
    , @NamedQuery(name = "BillOfLanding.findByNotifyName", query = "SELECT b FROM BillOfLanding b WHERE b.notifyName = :notifyName")
    , @NamedQuery(name = "BillOfLanding.findByNumOfCtnForThisBol", query = "SELECT b FROM BillOfLanding b WHERE b.numOfCtnForThisBol = :numOfCtnForThisBol")
    , @NamedQuery(name = "BillOfLanding.findByNumberOfPackages", query = "SELECT b FROM BillOfLanding b WHERE b.numberOfPackages = :numberOfPackages")
    , @NamedQuery(name = "BillOfLanding.findByPackageTypeCode", query = "SELECT b FROM BillOfLanding b WHERE b.packageTypeCode = :packageTypeCode")
    , @NamedQuery(name = "BillOfLanding.findByPlaceOfLoadingCode", query = "SELECT b FROM BillOfLanding b WHERE b.placeOfLoadingCode = :placeOfLoadingCode")
    , @NamedQuery(name = "BillOfLanding.findByPlaceOfUnloadingCode", query = "SELECT b FROM BillOfLanding b WHERE b.placeOfUnloadingCode = :placeOfUnloadingCode")
    , @NamedQuery(name = "BillOfLanding.findByShippingMarks", query = "SELECT b FROM BillOfLanding b WHERE b.shippingMarks = :shippingMarks")
    , @NamedQuery(name = "BillOfLanding.findByTransportCurrency", query = "SELECT b FROM BillOfLanding b WHERE b.transportCurrency = :transportCurrency")
    , @NamedQuery(name = "BillOfLanding.findByTransportValue", query = "SELECT b FROM BillOfLanding b WHERE b.transportValue = :transportValue")
    , @NamedQuery(name = "BillOfLanding.findByUniqueCarrierReference", query = "SELECT b FROM BillOfLanding b WHERE b.uniqueCarrierReference = :uniqueCarrierReference")
    , @NamedQuery(name = "BillOfLanding.findByVolumeInCubicMeters", query = "SELECT b FROM BillOfLanding b WHERE b.volumeInCubicMeters = :volumeInCubicMeters")})
public class BillOfLanding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BOL", nullable = false)
    private Integer idBol;
    @Size(max = 255)
    @Column(name = "PC_INDICATOR", length = 255)
    private String pcIndicator;
    @Size(max = 255)
    @Column(name = "BOL_NATURE", length = 255)
    private String bolNature;
    @Size(max = 255)
    @Column(name = "BOL_REFERENCE", length = 255)
    private String bolReference;
    @Size(max = 255)
    @Column(name = "BOL_TYPE_CODE", length = 255)
    private String bolTypeCode;
    @Size(max = 255)
    @Column(name = "CONSIGNEE_ADDRESS", length = 255)
    private String consigneeAddress;
    @Size(max = 255)
    @Column(name = "CONSIGNEE_CODE", length = 255)
    private String consigneeCode;
    @Size(max = 255)
    @Column(name = "CONSIGNEE_NAME", length = 255)
    private String consigneeName;
    @Size(max = 255)
    @Column(name = "CUSTOMS_CURRENCY", length = 255)
    private String customsCurrency;
    @Size(max = 20)
    @Column(name = "CUSTOMS_VALUE", length = 20)
    private Double customsValue;
    @Size(max = 255)
    @Column(name = "EXPORTER_ADDRESS", length = 255)
    private String exporterAddress;
    @Size(max = 255)
    @Column(name = "EXPORTER_CODE", length = 255)
    private String exporterCode;
    @Size(max = 255)
    @Column(name = "EXPORTER_NAME", length = 255)
    private String exporterName;
    @Size(max = 255)
    @Column(name = "FREIGHT_CURRENCY", length = 255)
    private String freightCurrency;
    @Size(max = 20)
    @Column(name = "FREIGHT_VALUE", length = 20)
    private Double freightValue;
    @Size(max = 255)
    @Column(name = "GOODS_DESCRIPTION", length = 255)
    private String goodsDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GROSS_MASS", nullable = false)
    private double grossMass;
    @Size(max = 255)
    @Column(length = 255)
    private String information;
    @Size(max = 255)
    @Column(name = "INSURANCE_CURRENCY", length = 255)
    private String insuranceCurrency;
    @Size(max = 20)
    @Column(name = "INSURANCE_VALUE", length = 20)
    private Double insuranceValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;
    @Size(max = 255)
    @Column(name = "LOCATION_CODE", length = 255)
    private String locationCode;
    @Size(max = 255)
    @Column(name = "LOCATION_INFO", length = 255)
    private String locationInfo;
    @Size(max = 255)
    @Column(name = "MASTER_BOL_REF_NUMBER", length = 255)
    private String masterBolRefNumber;
    @Size(max = 255)
    @Column(name = "NOTIFY_ADDRESS", length = 255)
    private String notifyAddress;
    @Size(max = 255)
    @Column(name = "NOTIFY_CODE", length = 255)
    private String notifyCode;
    @Size(max = 255)
    @Column(name = "NOTIFY_NAME", length = 255)
    private String notifyName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_OF_CTN_FOR_THIS_BOL", nullable = false)
    private double numOfCtnForThisBol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBER_OF_PACKAGES", nullable = false)
    private double numberOfPackages;
    @Size(max = 255)
    @Column(name = "PACKAGE_TYPE_CODE", length = 255)
    private String packageTypeCode;
    @Size(max = 255)
    @Column(name = "PLACE_OF_LOADING_CODE", length = 255)
    private String placeOfLoadingCode;
    @Size(max = 255)
    @Column(name = "PLACE_OF_UNLOADING_CODE", length = 255)
    private String placeOfUnloadingCode;
    @Size(max = 255)
    @Column(name = "SHIPPING_MARKS", length = 255)
    private String shippingMarks;
    @Size(max = 255)
    @Column(name = "TRANSPORT_CURRENCY", length = 255)
    private String transportCurrency;
    @Size(max = 20)
    @Column(name = "TRANSPORT_VALUE", length = 20)
    private Double transportValue;
    @Size(max = 255)
    @Column(name = "UNIQUE_CARRIER_REFERENCE", length = 255)
    private String uniqueCarrierReference;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VOLUME_IN_CUBIC_METERS", nullable = false)
    private double volumeInCubicMeters;
    @JoinColumn(name = "ID_GENERAL", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private GeneralInfo idGeneral;
    @OneToMany(mappedBy = "idBol")
    private Collection<Container> containerCollection;

    public BillOfLanding() {
    }

    public BillOfLanding(Integer idBol) {
        this.idBol = idBol;
    }

    public BillOfLanding(Integer idBol, double grossMass, Integer lineNumber, double numOfCtnForThisBol, double numberOfPackages, double volumeInCubicMeters) {
        this.idBol = idBol;
        this.grossMass = grossMass;
        this.lineNumber = lineNumber;
        this.numOfCtnForThisBol = numOfCtnForThisBol;
        this.numberOfPackages = numberOfPackages;
        this.volumeInCubicMeters = volumeInCubicMeters;
    }

    public Integer getIdBol() {
        return idBol;
    }

    public void setIdBol(Integer idBol) {
        this.idBol = idBol;
    }

    public String getPcIndicator() {
        return pcIndicator;
    }

    public void setPcIndicator(String pcIndicator) {
        this.pcIndicator = pcIndicator;
    }

    public String getBolNature() {
        return bolNature;
    }

    public void setBolNature(String bolNature) {
        this.bolNature = bolNature;
    }

    public String getBolReference() {
        return bolReference;
    }

    public void setBolReference(String bolReference) {
        this.bolReference = bolReference;
    }

    public String getBolTypeCode() {
        return bolTypeCode;
    }

    public void setBolTypeCode(String bolTypeCode) {
        this.bolTypeCode = bolTypeCode;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getExporterAddress() {
        return exporterAddress;
    }

    public void setExporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
    }

    public String getExporterCode() {
        return exporterCode;
    }

    public void setExporterCode(String exporterCode) {
        this.exporterCode = exporterCode;
    }

    public String getExporterName() {
        return exporterName;
    }

    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }

    public String getFreightCurrency() {
        return freightCurrency;
    }

    public void setFreightCurrency(String freightCurrency) {
        this.freightCurrency = freightCurrency;
    }

    public Double getFreightValue() {
        return freightValue;
    }

    public void setFreightValue(Double freightValue) {
        this.freightValue = freightValue;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public double getGrossMass() {
        return grossMass;
    }

    public void setGrossMass(double grossMass) {
        this.grossMass = grossMass;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getInsuranceCurrency() {
        return insuranceCurrency;
    }

    public void setInsuranceCurrency(String insuranceCurrency) {
        this.insuranceCurrency = insuranceCurrency;
    }

    public Double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(Double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getMasterBolRefNumber() {
        return masterBolRefNumber;
    }

    public void setMasterBolRefNumber(String masterBolRefNumber) {
        this.masterBolRefNumber = masterBolRefNumber;
    }

    public String getNotifyAddress() {
        return notifyAddress;
    }

    public void setNotifyAddress(String notifyAddress) {
        this.notifyAddress = notifyAddress;
    }

    public String getNotifyCode() {
        return notifyCode;
    }

    public void setNotifyCode(String notifyCode) {
        this.notifyCode = notifyCode;
    }

    public String getNotifyName() {
        return notifyName;
    }

    public void setNotifyName(String notifyName) {
        this.notifyName = notifyName;
    }

    public double getNumOfCtnForThisBol() {
        return numOfCtnForThisBol;
    }

    public void setNumOfCtnForThisBol(double numOfCtnForThisBol) {
        this.numOfCtnForThisBol = numOfCtnForThisBol;
    }

    public double getNumberOfPackages() {
        return numberOfPackages;
    }

    public void setNumberOfPackages(double numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }

    public String getPackageTypeCode() {
        return packageTypeCode;
    }

    public void setPackageTypeCode(String packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
    }

    public String getPlaceOfLoadingCode() {
        return placeOfLoadingCode;
    }

    public void setPlaceOfLoadingCode(String placeOfLoadingCode) {
        this.placeOfLoadingCode = placeOfLoadingCode;
    }

    public String getPlaceOfUnloadingCode() {
        return placeOfUnloadingCode;
    }

    public void setPlaceOfUnloadingCode(String placeOfUnloadingCode) {
        this.placeOfUnloadingCode = placeOfUnloadingCode;
    }

    public String getShippingMarks() {
        return shippingMarks;
    }

    public void setShippingMarks(String shippingMarks) {
        this.shippingMarks = shippingMarks;
    }

    public String getTransportCurrency() {
        return transportCurrency;
    }

    public void setTransportCurrency(String transportCurrency) {
        this.transportCurrency = transportCurrency;
    }

    public Double getTransportValue() {
        return transportValue;
    }

    public void setTransportValue(Double transportValue) {
        this.transportValue = transportValue;
    }

    public String getUniqueCarrierReference() {
        return uniqueCarrierReference;
    }

    public void setUniqueCarrierReference(String uniqueCarrierReference) {
        this.uniqueCarrierReference = uniqueCarrierReference;
    }

    public double getVolumeInCubicMeters() {
        return volumeInCubicMeters;
    }

    public void setVolumeInCubicMeters(double volumeInCubicMeters) {
        this.volumeInCubicMeters = volumeInCubicMeters;
    }

    public GeneralInfo getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(GeneralInfo idGeneral) {
        this.idGeneral = idGeneral;
    }

    @XmlTransient
    public Collection<Container> getContainerCollection() {
        return containerCollection;
    }

    public void setContainerCollection(Collection<Container> containerCollection) {
        this.containerCollection = containerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBol != null ? idBol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillOfLanding)) {
            return false;
        }
        BillOfLanding other = (BillOfLanding) object;
        if ((this.idBol == null && other.idBol != null) || (this.idBol != null && !this.idBol.equals(other.idBol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.BillOfLanding[ idBol=" + idBol + " ]";
    }
    
}
