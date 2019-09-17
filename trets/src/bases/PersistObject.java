/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import asycuda.awmds.Awmds;
import static bases.Const.*;
import dao.DbHandler;
import dao.Escale;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jpa.beans.BillOfLanding;
import jpa.beans.Container;
import jpa.beans.GeneralInfo;

/**
 *
 * @author Admin
 */
public class PersistObject {
    
    public static String getPortLibelle(String portCode, Statement stmt){
        String port = null;
                try {
                    ResultSet rst = stmt.executeQuery("select libelle from papn_locode where code like '" + portCode + "'");
                    if (rst.next()) {
                        port = rst.getString("libelle");
                        return port;
                    }
                } catch (SQLException ex) {
                    LOG.error(ex.getSQLState() + " : " + ex.getLocalizedMessage());
                    return portCode;
                }
                return portCode;
    }

    public static int manifestToDB(Awmds cargo, Escale escale) throws SQLException {
        Statement id = null;
        ResultSet resultSet = null;
        int id_gen;
        LOG.info("=======================================");
        LOG.info("========> DEBUT INSERTION to DB <======");

        try {
            conn = DbHandler.getDbConnection();
            id = conn.createStatement();
            Statement stmt = conn.createStatement();

            try (PreparedStatement insertGen = conn.prepareStatement(QUERY_SG)) {

//                insertGen.setInt(1, id_gen);
                conn.setAutoCommit(false);
                insertGen.setString(1, cargo.getGeneralSegment().getTransportInformation().getCarrier().getCarrierAddress());
                insertGen.setString(2, cargo.getGeneralSegment().getTransportInformation().getCarrier().getCarrierCode());
                insertGen.setString(3, cargo.getGeneralSegment().getTransportInformation().getCarrier().getCarrierName());
                insertGen.setString(4, cargo.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode());
                insertGen.setString(5, cargo.getGeneralSegment().getGeneralSegmentId().getDateOfArrival());
                insertGen.setString(6, cargo.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture());
                insertGen.setString(7, cargo.getGeneralSegment().getGeneralSegmentId().getDateOfLastDischarge());
                insertGen.setString(8, cargo.getGeneralSegment().getTransportInformation().getDateOfRegistration());
                insertGen.setString(9, cargo.getGeneralSegment().getTransportInformation().getIdentityOfTransporter());
                insertGen.setString(10, cargo.getGeneralSegment().getTransportInformation().getMasterInformation());
                insertGen.setString(11, REF.mode_trans.get(cargo.getGeneralSegment().getTransportInformation().getModeOfTransportCode()));
                insertGen.setString(12, cargo.getGeneralSegment().getTransportInformation().getNationalityOfTransporterCode());
                insertGen.setString(13, escale.getNumero());
                insertGen.setString(14, getPortLibelle(cargo.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode(), stmt));
                insertGen.setString(15, getPortLibelle(cargo.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode(), stmt));

                insertGen.setString(16, cargo.getGeneralSegment().getTransportInformation().getPlaceOfTransporter());
                insertGen.setString(17, cargo.getGeneralSegment().getTransportInformation().getRegistrationNumberOfTransportCode());
                insertGen.setString(18, cargo.getGeneralSegment().getTransportInformation().getShippingAgent() == null ? "" : cargo.getGeneralSegment().getTransportInformation().getShippingAgent().getShippingAgentCode());
                insertGen.setString(19, cargo.getGeneralSegment().getTransportInformation().getShippingAgent() == null ? "" : cargo.getGeneralSegment().getTransportInformation().getShippingAgent().getShippingAgentName());
                insertGen.setString(20, cargo.getGeneralSegment().getGeneralSegmentId().getTimeOfArrival());
                insertGen.setDouble(21, cargo.getGeneralSegment().getTonnage() == null ? 0.0 : cargo.getGeneralSegment().getTonnage().getTonnageGrossWeight());
                insertGen.setDouble(22, cargo.getGeneralSegment().getTonnage() == null ? 0.0 : cargo.getGeneralSegment().getTonnage().getTonnageNetWeight());
                insertGen.setDouble(23, cargo.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols());
                insertGen.setDouble(24, cargo.getGeneralSegment().getTotalsSegment().getTotalGrossMass());
                insertGen.setDouble(25, cargo.getGeneralSegment().getTotalsSegment().getTotalNumberOfContainers());
                insertGen.setDouble(26, cargo.getGeneralSegment().getTotalsSegment().getTotalNumberOfPackages());
                insertGen.setString(27, cargo.getGeneralSegment().getGeneralSegmentId().getVoyageNumber());
                insertGen.setInt(28, escale.getEscleunik());

                insertGen.executeUpdate();

            }
            resultSet = id.executeQuery(SEQ_SG);
            resultSet.next();
            id_gen = resultSet.getInt(1);
            LOG.info("===> SEGMENT GENERAL insere avec succes id = " + id_gen);
            LOG.info("===> NOMBRE DE BOLs : " + cargo.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols());
            LOG.info("===> NOMBRE DE CONTENEURS : " + cargo.getGeneralSegment().getTotalsSegment().getTotalNumberOfContainers());

            try (PreparedStatement insertBol = conn.prepareStatement(QUERY_BOL)) {

                int i = 1;

                for (Awmds.BolSegment bol : cargo.getBolSegment()) {
                    try {
//                        insertBol.setInt(1, ++id_bol);
                        insertBol.setString(1, " ");
                        insertBol.setString(2, REF.nature.get(bol.getBolId().getBolNature()));
                        insertBol.setString(3, bol.getBolId().getBolReference());
                        insertBol.setString(4, bol.getBolId().getBolTypeCode());
                        insertBol.setString(5, bol.getTradersSegment().getConsignee().getConsigneeAddress());
                        insertBol.setString(6, bol.getTradersSegment().getConsignee().getConsigneeCode());
                        insertBol.setString(7, bol.getTradersSegment().getConsignee().getConsigneeName());
                        insertBol.setString(8, "");
                        insertBol.setDouble(9, 0);
                        insertBol.setString(10, bol.getTradersSegment().getExporter().getExporterAddress());
                        insertBol.setString(11, bol.getTradersSegment().getExporter().getExporterCode());
                        insertBol.setString(12, bol.getTradersSegment().getExporter().getExporterName());
                        insertBol.setString(13, "");
                        insertBol.setDouble(14, 0.0);
                        insertBol.setString(15, bol.getGoodsSegment().getGoodsDescription());
                        insertBol.setDouble(16, bol.getGoodsSegment().getGrossMass());
//                        insertBol.setInt(17, id_gen);
                        insertBol.setString(17, bol.getGoodsSegment().getInformation());
                        insertBol.setString(18, "");
                        insertBol.setDouble(19, 0.0);
                        insertBol.setInt(20, bol.getBolId().getLineNumber().getValue());
                        insertBol.setString(21, bol.getLocation().getLocationCode());
                        insertBol.setString(22, bol.getLocation().getLocationInfo());
                        insertBol.setString(23, bol.getBolId().getMasterBolRefNumber());
                        insertBol.setString(24, bol.getTradersSegment().getNotify().getNotifyAddress());
                        insertBol.setString(25, bol.getTradersSegment().getNotify().getNotifyCode());
                        insertBol.setString(26, bol.getTradersSegment().getNotify().getNotifyName());
                        insertBol.setDouble(27, bol.getGoodsSegment().getNumOfCtnForThisBol());
                        insertBol.setDouble(28, bol.getGoodsSegment().getNumberOfPackages());
                        insertBol.setString(29, REF.pkg_table.get(bol.getGoodsSegment().getPackageTypeCode()));
                        insertBol.setString(30, getPortLibelle(bol.getLoadUnloadPlace().getPlaceOfLoadingCode(), stmt));
                        insertBol.setString(31, getPortLibelle(bol.getLoadUnloadPlace().getPlaceOfUnloadingCode(), stmt));
                        insertBol.setString(32, bol.getGoodsSegment().getShippingMarks());
                        insertBol.setString(33, "");
                        insertBol.setDouble(34, 0.0);
                        insertBol.setString(35, bol.getBolId().getUniqueCarrierReference());
                        insertBol.setDouble(36, bol.getGoodsSegment().getVolumeInCubicMeters() == null ? 0.0 : bol.getGoodsSegment().getVolumeInCubicMeters());

                        insertBol.executeUpdate();

                        resultSet = id.executeQuery(SEQ_BOL);
                        resultSet.next();
                        int id_bol = resultSet.getInt(1);
                        LOG.info("===> Bol N° " + i++ + " insere. ID = " + id_bol);
                        LOG.info("===> NOMBRE DE CONTENEURS " + bol.getGoodsSegment().getNumOfCtnForThisBol());
                        try (PreparedStatement insertCtn = conn.prepareStatement(QUERY_CTN)) {

                            int j = 1;
                            for (Awmds.BolSegment.CtnSegment ctn : bol.getCtnSegment()) {
                                try {
//                                    insertCtn.setInt(1, ++id_ctn);
                                    insertCtn.setString(1, ctn.getCtnReference());
                                    insertCtn.setString(2, String.valueOf(ctn.getEmptyFull()));
                                    insertCtn.setString(3, String.valueOf(ctn.getEmptyWeight()));
                                    insertCtn.setString(4, ctn.getGoodsWeight() == null ? "" : String.valueOf(ctn.getGoodsWeight()));
//                                    insertCtn.setInt(5, id_bol);
                                    insertCtn.setString(5, ctn.getMarks1());
                                    insertCtn.setString(6, ctn.getMarks2());
                                    insertCtn.setString(7, ctn.getMarks3());
                                    insertCtn.setString(8, String.valueOf(ctn.getNumberOfPackages()));
                                    insertCtn.setString(9, ctn.getSealingParty());
                                    insertCtn.setString(10, ctn.getTypeOfContainer());

                                    insertCtn.executeUpdate();
                                    resultSet = id.executeQuery(SEQ_CTN);
                                    resultSet.next();
                                    int id_ctn = resultSet.getInt(1);
                                    LOG.info("=> Conteneur N° " + j++ + " insere. ID = " + id_ctn);
                                    LOG.info("=> NOMBRE DE COLIS " + ctn.getNumberOfPackages());
                                } catch (SQLException ex) {
//                                    LOG.warn("CONTENEUR N° " + id_ctn);
                                    if (ex instanceof java.sql.SQLSyntaxErrorException) {
                                        if (ex.getMessage().contains("ORA-02289")) {
                                            LOG.error("ORA-02289: " + "SEQ_PAPN_CONTAINER " + "sequence does not exist");
                                            ex.printStackTrace();
                                        }
                                    }
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } catch (SQLException ex) {
//                        LOG.warn("BOL N° " + id_bol);
                        if (ex instanceof java.sql.SQLSyntaxErrorException) {
                            if (ex.getMessage().contains("ORA-02289")) {
                                LOG.error("ORA-02289: " + "SEQ_PAPN_BILLOFLANDING " + "sequence does not exist");
                                ex.printStackTrace();
                            }
                        }
                        ex.printStackTrace();
                    }
                }
            }
            conn.commit();
            LOG.info("========> FIN INSERTION to DB <======");
            LOG.info("=======================================");

            return id_gen;
        } catch (SQLException ex) {
////            LOG.warn("MANIFESTE " + id_gen);
            if (ex instanceof java.sql.SQLSyntaxErrorException) {
                if (ex.getMessage().contains("ORA-02289")) {
                    LOG.error("ORA-02289: " + "SEQ_PAPN_GENERAL_INFO " + "sequence does not exist");
                    ex.printStackTrace();
                }
            }
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Recuperation des informations generales du manifest La methode prend en
     * parametre un manifest complet et retourne un objet contenant les info
     * generales
     */
    public static GeneralInfo getGeneral(Awmds awmds) throws IOException, FileNotFoundException, InterruptedException {
        GeneralInfo general = new GeneralInfo();
        int j = 0;
        general.setCustomsOfficeCode(awmds.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode());
        general.setVoyageNumber(awmds.getGeneralSegment().getGeneralSegmentId().getVoyageNumber());
        general.setDateDeparture(awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture());
        general.setDateArrival(awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival());
        general.setDateLastDischarge(awmds.getGeneralSegment().getGeneralSegmentId().getDateOfLastDischarge());
        general.setTimeArrival(awmds.getGeneralSegment().getGeneralSegmentId().getTimeOfArrival());

        //recuperer les donnees du segment <TotalsSegment>
        general.setTotalNumberOfPackages(awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfPackages());
        general.setTotalNumberOfContainers(awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfContainers());
        general.setTotalGrossMass(awmds.getGeneralSegment().getTotalsSegment().getTotalGrossMass());
        general.setTotalNumberOfBols(awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols());

        //recuperation des donnes du segment <Transport_informations>
        //segment <Carrier>
        general.setCarrierName(awmds.getGeneralSegment().getTransportInformation().getCarrier().getCarrierName());
        general.setCarrierCode(awmds.getGeneralSegment().getTransportInformation().getCarrier().getCarrierCode());
        general.setCarrierAddress(awmds.getGeneralSegment().getTransportInformation().getCarrier().getCarrierAddress());

        general.setModeTransport(awmds.getGeneralSegment().getTransportInformation().getModeOfTransportCode());
        general.setIdentityTransporter(awmds.getGeneralSegment().getTransportInformation().getIdentityOfTransporter());
        general.setNationalityTransporter(awmds.getGeneralSegment().getTransportInformation().getNationalityOfTransporterCode());

        if (awmds.getGeneralSegment().getTransportInformation().getMasterInformation() == null) {
            general.setMasterInformation("DEFAULT");
        }

        if (awmds.getGeneralSegment().getTransportInformation().getPlaceOfTransporter() != null) {
            general.setPlaceOfTransporter(awmds.getGeneralSegment().getTransportInformation().getPlaceOfTransporter());
        }

        general.setRegistrationNumber(awmds.getGeneralSegment().getTransportInformation().getRegistrationNumberOfTransportCode());
        general.setDateOfRegistration(awmds.getGeneralSegment().getTransportInformation().getDateOfRegistration());

        //segment <Shipping agent>
        if (awmds.getGeneralSegment().getTransportInformation().getShippingAgent() != null) {
            general.setShippingAgentName(awmds.getGeneralSegment().getTransportInformation().getShippingAgent().getShippingAgentName());
            general.setShippingAgentCode(awmds.getGeneralSegment().getTransportInformation().getShippingAgent().getShippingAgentCode());
        }
        general.setPlaceOfDepartureCode(awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode());
        general.setPlaceOfDestinationCode(awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode());

        if (awmds.getGeneralSegment().getTonnage() != null) {
            general.setTonnageNetWeight(awmds.getGeneralSegment().getTonnage().getTonnageNetWeight());
            general.setTonnageGrossWeight(awmds.getGeneralSegment().getTonnage().getTonnageGrossWeight());
        }
        return general;
    }

    /**
     * La methode prend en parametre un objet manifest complet et un objet info
     * general puis retourne une liste de bill of landing
     *
     * @param awmds
     * @param general
     * @return
     */
    protected static List<BillOfLanding> getBol(Awmds awmds, GeneralInfo general) throws NullPointerException {
        ArrayList bols = new ArrayList();
        BillOfLanding bl = new BillOfLanding();

        awmds.getBolSegment().stream().forEach((bol) -> {
            try {
                bl.setBolNature(bol.getBolId().getBolNature());
                bl.setBolReference(bol.getBolId().getBolReference());
                bl.setBolTypeCode(bol.getBolId().getBolTypeCode());

                if (bol.getTradersSegment().getConsignee().getConsigneeAddress() != null) {
                    bl.setConsigneeAddress(bol.getTradersSegment().getConsignee().getConsigneeAddress());
                } else {
                    bl.setConsigneeAddress(" ");
                }

                bl.setConsigneeCode(bol.getTradersSegment().getConsignee().getConsigneeCode());
                bl.setConsigneeName(bol.getTradersSegment().getConsignee().getConsigneeName());

                if (!(bol.getValueSegment().getCustomsSegment() == null)) {
                    bl.setCustomsCurrency(bol.getValueSegment().getCustomsSegment().getCustomsCurrency());
                    bl.setCustomsValue(bol.getValueSegment().getCustomsSegment().getCustomsValue());
                }
                bl.setExporterAddress(bol.getTradersSegment().getExporter().getExporterAddress());
                bl.setExporterCode(bol.getTradersSegment().getExporter().getExporterCode());
                bl.setExporterName(bol.getTradersSegment().getExporter().getExporterName());
                bl.setGoodsDescription(bol.getGoodsSegment().getGoodsDescription());
                bl.setGrossMass(bol.getGoodsSegment().getGrossMass());
                bl.setInformation(bol.getGoodsSegment().getInformation());
                if (!(bol.getValueSegment().getInsuranceSegment() == null)) {
                    bl.setInsuranceCurrency(bol.getValueSegment().getInsuranceSegment().getInsuranceCurrency());
                    bl.setInsuranceValue(bol.getValueSegment().getInsuranceSegment().getInsuranceValue());
                }
                bl.setLineNumber(bol.getBolId().getLineNumber().getValue());
                bl.setLocationCode(bol.getLocation().getLocationCode());
                bl.setLocationInfo(bol.getLocation().getLocationInfo());
                bl.setMasterBolRefNumber(bol.getBolId().getMasterBolRefNumber());
                bl.setNotifyAddress(bol.getTradersSegment().getNotify().getNotifyAddress());
                bl.setNotifyCode(bol.getTradersSegment().getNotify().getNotifyCode());
                bl.setNotifyName(bol.getTradersSegment().getNotify().getNotifyName());
                bl.setNumberOfPackages((int) bol.getGoodsSegment().getNumberOfPackages());
                bl.setNumOfCtnForThisBol(bol.getGoodsSegment().getNumOfCtnForThisBol());
                bl.setPackageTypeCode(bol.getGoodsSegment().getPackageTypeCode());

                bl.setPlaceOfLoadingCode(bol.getLoadUnloadPlace().getPlaceOfLoadingCode());
                bl.setPlaceOfUnloadingCode(bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());

                bl.setShippingMarks(bol.getGoodsSegment().getShippingMarks());
                if (!(bol.getValueSegment().getTransportSegment() == null)) {
                    bl.setTransportCurrency(bol.getValueSegment().getTransportSegment().getTransportCurrency());
                    bl.setTransportValue(bol.getValueSegment().getTransportSegment().getTransportValue());
                }
                if (!(bol.getValueSegment().getFreightSegment() == null)) {
                    // bl.setPCindicator(bol.getValueSegment().getFreightSegment().getPCIndicator());
                    bl.setFreightCurrency(bol.getValueSegment().getFreightSegment().getFreightCurrency());
                    bl.setFreightValue(bol.getValueSegment().getFreightSegment().getFreightValue());
                }
                bl.setUniqueCarrierReference(bol.getBolId().getUniqueCarrierReference());
                if (bol.getGoodsSegment().getVolumeInCubicMeters() == null) {
                    bol.getGoodsSegment().setVolumeInCubicMeters(0.0);
                }
                bl.setVolumeInCubicMeters(bol.getGoodsSegment().getVolumeInCubicMeters());
                bl.setIdGeneral(general);
                ;
                bl.setIdBol(BOLJC.findLastId());
                BOLJC.create(bl);
                ////
                bl.setContainerCollection(getContainer(bol, bl));
                LOG.info(" Bol " + bl.getLineNumber() + "/" + awmds.getBolSegment().size() + " du Manifest " + bl.getIdGeneral().getId() + " a " + bl.getContainerCollection().size() + " Conteneurs");
                bols.add(bl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return bols;
    }

    /**
     * @Description @param bolsegment est un B/L donné dans le manifest
     * @param bol une instance contenant les donnees de bolsegment afin d etre
     * enregistrer dans la base de donnee
     * @return une liste de conteneurs que contient un B/L
     */
    protected static List<Container> getContainer(Awmds.BolSegment bolsegment, BillOfLanding bol) {
        ArrayList ctnrs = new ArrayList();
        Container newctn = new Container();
        /**
         * recuperation des donnees concernant les conteneurs contenus dans le
         * B/L bol depuis un instance de la classe JAXB Awmds.bolsegment qui
         * contient les donnees du xml manifest
         */
        bolsegment.getCtnSegment().stream().forEach((ctn) -> {
            try {

                newctn.setCtnReference(ctn.getCtnReference());
                newctn.setEmptyFull(String.valueOf(ctn.getEmptyFull()));
                newctn.setEmptyWeight(String.valueOf(ctn.getEmptyWeight()));
                if (ctn.getGoodsWeight() == null) {
                    ctn.setGoodsWeight(0.0);
                }
                newctn.setGoodsWeight(String.valueOf(ctn.getGoodsWeight()));
                newctn.setIdBol(bol);
                newctn.setMarks1(ctn.getMarks1());
                newctn.setMarks2(ctn.getMarks2());
                newctn.setMarks3(ctn.getMarks3());
                newctn.setNumberOfPackages(String.valueOf(ctn.getNumberOfPackages()));
                newctn.setSealingParty(ctn.getSealingParty());
                newctn.setTypeOfContainer(ctn.getTypeOfContainer());
                newctn.setIdCtn(CTNJC.findLastId());
                CTNJC.create(newctn);
                ctnrs.add(newctn); // 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return ctnrs;
    }

}
