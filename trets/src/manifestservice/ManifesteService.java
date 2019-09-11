/*
 * To searchPortTRB this license header, choose License Headers in Project Properties.
 * To searchPortTRB this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manifestservice;

import asycuda.awmds.Awmds;
import asycuda.awmds.ObjectFactory;
import static bases.Const.*;
import bases.ManifestMethods;
import static bases.PersistObject.manifestToDB;
import base.PortDestination;
import bases.PortDestination2;
import static bases.XmlObject.AwmdsToXml;
import dao.DbHandler;
import dao.Escale;
import jpa.beans.GeneralInfo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.beans.BillOfLanding;
import jpa.beans.Container;
import jpa.controlleurs.EscaleJpaController;
import jpa.controlleurs.GeneralInfoJpaController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fonts.InvalidFontException;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author calvin0
 */
public class ManifesteService {

    //VARIABLES
    private static Connection conn;
    private static String queryString;
    private static String reportFilename;
    private static String mois;
    private static int id_manifest;
    private static boolean isManifest;
    private static HashMap<String, Object> reportParameters = new HashMap<>();
    private static JasperPrint jasperPrint;
    private static Awmds manifest;
    private static JasperReport jasperManiReport;
    private static FileWriter fileWriter = null;
    private static FTPClient ftp = new FTPClient();
    private static Connection connection;
    private static File jrprint;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASYCUDAPU");
    static ObjectFactory obj = new ObjectFactory();

    public static void main(String[] args) {
//        try {
//            //        allManifest();
////            importCongoTerminal.main(args);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        runAction();
    }

    static void runAction() {
        //        importXml();
        String[] destXmlManifest = new String[2];
        String[] destPdfManifest = new String[2];
        File listManifest = new File(DOSSIER_MANIFEST_IN);
        File[] destXmlManifestFile = new File[2], destPdfManifestFile = new File[2];
        while (true) {
//            if (FtpClient.connect(ftp, false)) {
////                LOG.info("CONNECTED : " + ftp.isConnected()) ;
//                //Recuperation des manifestes s'ils existent
//                FtpClient.listFTPFiles(ftp, DOSSIER_FTP_IN, DOSSIER_MANIFEST_IN, DOSSIER_FTP_ARC);
//
//                // deconnexion au FTP DOUANE
//                FtpClient.disconnect(ftp);
//            }
            boolean mois = false;
            if (listManifest.listFiles() != null && listManifest.listFiles().length > 0) {
                for (File manifestFile : listManifest.listFiles()) {
                    LOG.info("FICHIER " + manifestFile.getName());

                    //Validation du fichier Manifest: renvoie 
                    manifest = ManifestMethods.validationManifest(manifestFile, isManifest, DOSSIER_MANIFEST_ERR);

                    if (manifest.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNR") && manifest.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture().startsWith("2019")) {
                        mois = true;
                    }
                    if (manifest.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNR") && manifest.getGeneralSegment().getGeneralSegmentId().getDateOfArrival().startsWith("2019")) {
                        mois = true;
                    }
                    mois = true;
                    if (manifest != null && mois) {
                        destXmlManifest = ManifestMethods.renameManifest(manifest, manifestFile.getName());

                        LOG.info("Formatage du nom terminé.");

                        if (destXmlManifest != null) {
                            LOG.info(destXmlManifest[0]);
                            destPdfManifest[0] = destXmlManifest[0].substring(0, destXmlManifest[0].length() - 4) + ".pdf";
                            destPdfManifest[1] = destXmlManifest[1].substring(0, destXmlManifest[1].length() - 4) + ".pdf";
                            destXmlManifestFile[0] = new File(destXmlManifest[0]);
                            destPdfManifestFile[0] = new File(destPdfManifest[0]);
                            destXmlManifestFile[1] = new File(destXmlManifest[1]);
                            destPdfManifestFile[1] = new File(destPdfManifest[1]);

                            LOG.info("============ DEBUT TRATITEMENT  ============");

//                            LOG.info("Nouveau ");
//                            ManifestMethods.AwmdsToXml(manifest, destXmlManifestFile[0]);
//                            LOG.info(" Manifest classé : " + destXmlManifest[0]);
//                    try {
//                        fileWriter = new FileWriter(destXmlManifestFile);
//                        fileWriter.flush();
//                        fileWriter.close();
//                    } catch (IOException ex) {
//                        LOG.error(ex.getLocalizedMessage());
//                    }
                            try {
                                Class.forName(CARGO_DRIVER);
                            } catch (ClassNotFoundException ex) {
                                LOG.error(ex.getMessage());
                            }
                            try {
                                connection = DriverManager.getConnection(CARGO_FULL_URL, CARGO_USER, CARGO_PASSWORD);
                                LOG.info("===> CONNECTION :" + !connection.isClosed());
                            } catch (SQLException ex) {
                                LOG.error(ex.getMessage());
                            }

                            try {
                                id_manifest = persistenceXML(manifest, destXmlManifestFile[0]);
                                Awmds manif = getManifeste(id_manifest);
                                AwmdsToXml(manif, destXmlManifestFile[0]);
                            } catch (SQLException ex) {
                                LOG.error("Quelque chose a mal tourne du cote de la DB");
//                                manifestFile.renameTo(new File(Config.PROPERTIES.getString("dossier.manifest.err") + manifestFile.getName()));
//                                manifestFile.delete();
                                continue;
                            }
                            try {
                                generationPDF(id_manifest, destXmlManifestFile[0], destPdfManifestFile[0]);
                            } catch (JRException | SQLException | IOException ex) {
//                                manifestFile.renameTo(new File(Config.PROPERTIES.getString("dossier.manifest.err") + manifestFile.getName()));
//                                manifestFile.delete();
                                continue;
                            }
                            LOG.info(destXmlManifestFile[0].getAbsolutePath());
                            LOG.info(destXmlManifestFile[1].getAbsolutePath());
                            try {
                                if (destXmlManifestFile[1].exists()) {
                                    destXmlManifestFile[1].delete();
                                }
                                Files.copy(destXmlManifestFile[0].toPath(), destXmlManifestFile[1].toPath());
                                LOG.info("=======================================");
                                LOG.info("=============> DEBUT COPIE <===========");
                                LOG.info("===> Copie " + destXmlManifestFile[0].getAbsolutePath() + " vers " + destXmlManifestFile[1].getAbsolutePath());
                                if (destPdfManifestFile[1].exists()) {
                                    destPdfManifestFile[1].delete();
                                }
                                Files.copy(destPdfManifestFile[0].toPath(), destPdfManifestFile[1].toPath());
                                LOG.info("===> Copie " + destPdfManifestFile[0].getAbsolutePath() + " vers " + destPdfManifestFile[1].getAbsolutePath());
                                LOG.info("==============> FIN COPIE <============");
                                LOG.info("=======================================");
                                LOG.info("=======================================");
                                LOG.info("==========> DEBUT DEPLACEMENT <========");
                                LOG.info("===> Deplacement du contenu du dossier " + DOSSIER_MANIFEST_IN + " vers " + DOSSIER_MANIFEST_ARC);
                                File target = null;
                                if (manifest.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNR")) {
                                    target = new File(DOSSIER_MANIFEST_ARC + File.separator + manifest.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture().substring(0, 7) + File.separator + manifestFile.getName());
                                }
                                if (manifest.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNR")) {
                                    target = new File(DOSSIER_MANIFEST_ARC + File.separator + manifest.getGeneralSegment().getGeneralSegmentId().getDateOfArrival().substring(0, 7) + File.separator + manifestFile.getName());
                                }

                                if (target != null && Files.deleteIfExists(target.toPath())) {
                                    LOG.info(target.getAbsolutePath() + " supprimé.");
                                }

                                if (target != null && Files.move(manifestFile.toPath(), target.toPath()).toFile().exists()) {
                                    if (manifestFile.exists()) {
                                        LOG.info(target.toPath() + " déplacé...");
                                    }
                                }
                                LOG.info("===========> FIN DEPLACEMENT <=========");
                                LOG.info("=======================================");
//                                if (Files.move(manifestFile.toPath(), target.toPath()).toFile().exists()) {
//                                    if (manifestFile.exists()) {
//                                        manifestFile.delete();
//                                    }
//                                    LOG.info("===> " + manifestFile.getAbsolutePath() + " archivé vers " + target.getAbsolutePath());
//                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

//                if (!manifestFile.getName().equals(destXmlManifest)) {
//                    if (manifestFile.delete()) {
//                        LOG.info(manifestFile.getName() + " supprimé ");
//                    }
//                }
                            LOG.info(destXmlManifest[1]);
                        } else {
                            continue;
                        }
                    }
                }
            }
//            else {
//                LOG.info("===> Dossier " + Config.PROPERTIES.getString("dossier.manifest.in") + " vide.");
//                LOG.info("===> En attente de nouveaux manifestes.");
//                System.out.print("Relance dans 60 secondes : ");
//                for (int i = 0; i < 60; i++) {
//
//                    try {
//                        System.out.print("*");
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        LOG.trace(ex.getMessage());
//                    }
//                }
//                LOG.info("");
//            }

        }
    }

    public static void allManifest() {
        try {
            conn = DbHandler.getDbConnection();
            String query = "select id from general_info";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            ResultSet list = stmt.getResultSet();
            while (list.next()) {
                LOG.info("" + list.getInt(1));
                GeneralInfo awmds = GIJC.findGeneralInfo(list.getInt(1));

            }
        } catch (SQLException ex) {
            LOG.info("");
        }

    }

    public static void generationPDF(int id, File xmlFile, File savefile) throws SQLException, IOException, JRException {
        if (id == 0) {
            throw new NullPointerException("id_manifest est egale à 0. verifier la connection avec DB");
        }
        setReportFilename(xmlFile.getName().substring(1, xmlFile.getName().length() - 4));
        EscaleJpaController ejc = new EscaleJpaController(emf);;
        String code = null;
        try {
            Double id_escale = Double.valueOf(xmlFile.getName().substring(18, 23));
            jpa.beans.Escale esc = ejc.findEscale(BigDecimal.valueOf(id_escale));
            code = esc.getNumero() + " du " + esc.getArrivee() + " au " + esc.getDepart();
        } catch (NumberFormatException ex) {
            LOG.error("ID ESCALE NON CONFORME : " + xmlFile.getName().substring(18, 23));
            code = "_________________ DU ________________ AU ________________";
        }

        String papn = xmlFile.getName();
        getReportParameters().put("id", id);
        getReportParameters().put("papn", papn);
        getReportParameters().put("code", code);
        reportParameters.put("SUBREPORT_DIR", SUB_REPORT);
        try {
            LOG.info("=======================================");
            LOG.info("==>DEBUT COMPILATION JRXML to JASPER<==");
            if (Files.notExists(new File(CTN_REPORT + ".jasper").toPath())) {
                JasperCompileManager.compileReportToFile(CTN_REPORT + ".jrxml", CTN_REPORT + ".jasper");
            }
            if (Files.notExists(new File(BOL_REPORT + ".jasper").toPath())) {
                JasperCompileManager.compileReportToFile(BOL_REPORT + ".jrxml", BOL_REPORT + ".jasper");
            }
//            JasperCompileManager.compileReportToFile(MAIN_REPORT + ".jrxml", MAIN_REPORT +  ".jasper");
            jasperManiReport = JasperCompileManager.compileReport(MAIN_REPORT + ".jrxml");
            LOG.info("===>FIN COMPILATION JRXML to JASPER<===");
            LOG.info("=======================================");
        } catch (JRException ex) {
            ex.printStackTrace();
            LOG.error("===> La compilation JRXML to JASPER a rencontré un problème [" + ex.getMessageKey() + "]: " + ex.getMessage());
            throw ex;
        }

        // Load the JDBC driver
        if (connection == null) {
            try {
                LOG.info("=======================================");
                LOG.info("========> CONNEXION A CARGO DB <=======");
                connection = DriverManager.getConnection(CARGO_FULL_URL, CARGO_USER, CARGO_PASSWORD);
                LOG.info("=========> CONNEXION ETABLIE <=========");
                LOG.info("=======================================");
            } catch (SQLException ex) {
                LOG.error("===> Probleme de connexion a la base de donnée " + ex.getSQLState() + "\n" + ex.getMessage());
                throw ex;
            }
        }

        try {
            LOG.info("=======================================");
            LOG.info("====>DEBUT CREATION DU FICHIER PDF<====");
            fileWriter = new FileWriter(savefile);
            fileWriter.flush();
            fileWriter.close();
            LOG.info("===> Création du fichier " + savefile.getName() + " avec succès!");
            LOG.info("=====>FIN CREATION DU FICHIER PDF<=====");
            LOG.info("=======================================");
        } catch (IOException ex) {
            LOG.error("Probleme d'ecriture du fichier " + savefile.getAbsolutePath() + "\n" + ex.getMessage());
            throw ex;
        }

        setReportFilename(savefile.getAbsolutePath());

        LOG.info("=======================================");
        LOG.info("====>DEBUT RENDU JASPER to JRPRINT<====");
        jrprint = new File(getReportFilename() + ".jrprint");
        try {
            JasperFillManager.fillReportToFile(jasperManiReport, jrprint.getAbsolutePath(), reportParameters, connection);
            LOG.info("=====>FIN RENDU JASPER to JRPRINT<=====");
            LOG.info("=======================================");
        } catch (JRException ex) {
            LOG.error("Probleme de conversion JASPER to JRPRINT [" + ex.getMessageKey() + "] : " + ex.getMessage());
            throw ex;
        } catch (InvalidFontException ex) {
            LOG.warn(" Error loading font family ");
        }
        try {
            LOG.info("=======================================");
            LOG.info("=====>PHASE FINAL DU FICHIER PDF<======");
            LOG.info("===> Fichier PDF : " + savefile.getAbsolutePath());
            JasperExportManager.exportReportToPdfFile(jrprint.getAbsolutePath(), savefile.getAbsolutePath());
            LOG.info("===============> TERMINE <=============");
            LOG.info("=======================================");
            jrprint.delete();
        } catch (JRException e) {
            if (e.getMessage().contains("Permission denied")) {
                LOG.error("Vous n'avez pas le droit d'écrire ce repertoire");
            }
            throw e;
        }
        connection.close();
        if (connection.isClosed()) {
            LOG.info("DB deconnecté.");
        }
    }

    public static int persistenceXML(Awmds awmds, File xmlFile) throws SQLException {
        int id = 0;
        LOG.info("=======================================");
        LOG.info("===========>DEBUT PERSISTENCE XML<===========");

        awmds.getBolSegment().forEach(bol -> {
            try {
                bol.getLoadUnloadPlace().setPlaceOfLoadingCode(bol.getLoadUnloadPlace().getPlaceOfLoadingCode() == null ? "CGPNR" : bol.getLoadUnloadPlace().getPlaceOfLoadingCode());
                bol.getLoadUnloadPlace().setPlaceOfUnloadingCode(bol.getLoadUnloadPlace().getPlaceOfUnloadingCode() == null ? "CGPNR" : bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());

                if (bol.getBolId().getBolNature().equals("28") && awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNR")) {
                    bol.getBolId().setBolNature("29");
                }
            } catch (NullPointerException ex) {
                if (bol.getBolId().getBolReference() != null) {
                    LOG.info("BOL : " + bol.getBolId().getBolReference());
                }
                if (bol.getBolId().getBolReference() == null && bol.getBolId().getBolNature() == null) {
                    LOG.info("BOL : " + bol.getBolId().getBolReference() + " SUPPRIME.");
                    awmds.getBolSegment().remove(bol);
                }
            }
        });
        //Determination du port de destination en transbordement 
        if (awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNR") || awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNP") || awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGHTM")) {
            mois = awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture().substring(0, 4) + awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture().substring(5, 7);
            LOG.info("Date de depart:" + awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture() + "\n Mois : " + mois);
        } else if (awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNR") || awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNP") || awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGHTM")) {
            mois = awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival().substring(0, 4) + awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival().substring(5, 7);
            LOG.info("Date d'arrivée:" + awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival() + "\n Mois : " + mois);
        }
        String[] trim = xmlFile.getName().split("-");
        if (trim[4].isEmpty()) {
            trim[4] = "0";
        }
        Escale escale = new Escale("", "", "", "", trim[4], Integer.valueOf(trim[3]), "");
        if (!mois.equals(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE).substring(0, 6))) {
            LOG.info("===> Recherche dans le fichier de CONGO TERMINAL : " + mois);
            id = ManifesteService.exists(awmds);
            if (id == 0) {
                try {
                    String arrival = awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival();
                    String depart = awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture();
                    awmds.getGeneralSegment().getGeneralSegmentId().setDateOfArrival("2019-07-01");
                    awmds.getGeneralSegment().getGeneralSegmentId().setDateOfDeparture("2019-07-31");
                    PortDestination2.searchPortTRB(awmds);
                    awmds.getGeneralSegment().getGeneralSegmentId().setDateOfArrival(arrival);
                    awmds.getGeneralSegment().getGeneralSegmentId().setDateOfDeparture(depart);
                    id = manifestToDB(awmds, escale);// after stored in db, the pk id of the current manifest is returned

                } catch (SQLException ex) {
                    LOG.error("Integration du manifeste a recontre un probleme [" + ex.getSQLState() + "] " + ex.getMessage());
                }
            } else {
                String arrival = awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival();
                String depart = awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture();
                awmds.getGeneralSegment().getGeneralSegmentId().setDateOfArrival("2019-07-01");
                awmds.getGeneralSegment().getGeneralSegmentId().setDateOfDeparture("2019-07-31");
                PortDestination.searchPortTRB(awmds, id);
                awmds.getGeneralSegment().getGeneralSegmentId().setDateOfArrival(arrival);
                awmds.getGeneralSegment().getGeneralSegmentId().setDateOfDeparture(depart);
            }
        } else {
            LOG.info("===> Recherche dans le fichier de CONGO TERMINAL : " + mois);
            id = ManifesteService.exists(awmds);
            if (id == 0) {
                try {
                    id = manifestToDB(awmds, escale);// after stored in db, the pk id of the current manifest is returned
                } catch (SQLException ex) {
                    LOG.error("Integration du manifeste a recontre un probleme [" + ex.getSQLState() + "] " + ex.getMessage());
                }
            }
        }
//            MYWORKS.clear();
//            String name = xmlFile.getParent() + "/" + renameManifest(awmds, xmlFile.getName()) + ".xml";
//            LOG.info(name);
//            maniFile = new File(name);
//            maniFile = new File(xmlFile.getAbsolutePath().substring(0, xmlFile.getAbsolutePath().length() - 4) + ".xml");
        LOG.info("===> Enregistrement du nouveau XML traité");
//        AwmdsToXml(awmds, xmlFile);
//        
        LOG.info("===========>FIN PERSISTENCE XML<===========");
        LOG.info("===========================================");
        return id;
    }

    public static String getETB(String numero) throws SQLException {
        conn = DbHandler.getDbConnection();
        List<Escale> data = new ArrayList();
        queryString = "SELECT la_ligne  "
                + "FROM escales_douanes_papn  "
                + "WHERE substr(nom_fichier,11,10) like ?  ";
        String laligne = "";
        if (conn != null) {
            LOG.info("DB connected");
            PreparedStatement pst = conn.prepareStatement(queryString);
            LOG.info("********************************************");

            pst.setString(1, numero);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                laligne = rst.getString("la_ligne");
            }
//            LOG.info("*****************ETB************************");
//            LOG.info("********************************************");
//            LOG.info(laligne);
//            LOG.info("********************************************");
            conn.close();
            LOG.info("********************************************");
        }
        return laligne;
    }

    private static Awmds getManifeste(int id_manifest) {
        Awmds man = new Awmds();
        conn = DbHandler.getDbConnection();
        try {
            Statement stmt = conn.createStatement();
            GeneralInfoJpaController gjc = new GeneralInfoJpaController(emf);
            GeneralInfo gen = gjc.findGeneralInfo(id_manifest);
            Awmds.GeneralSegment sg = xtractSegmentGeneral(gen, stmt);
            man.setGeneralSegment(sg);
            gen.getBillOfLandingCollection().stream().map((bol) -> {
                Awmds.BolSegment bl = xtractBol(bol, stmt);
                bol.getContainerCollection().stream().map((ctn) -> xtractctn(ctn)).forEachOrdered((ctnr) -> {
                    bl.getCtnSegment().add(ctnr);
                });
                return bl;
            }).forEachOrdered((bl) -> {
                man.getBolSegment().add(bl);
            });
            return man;
        } catch (SQLException ex) {
            LOG.error("Erreur de connexion " + ex.getSQLState() + " " + ex.getMessage());
            return null;
        }
    }

    private static int exists(Awmds awmds) {
        conn = DbHandler.getDbConnection();
        try {
            ResultSet rst = conn.createStatement().executeQuery("select id from general_info where "
                    + "CUSTOMS_OFFICE_CODE like '"
                    + awmds.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode()
                    + "' and VOYAGE_NUMBER like '"
                    + awmds.getGeneralSegment().getGeneralSegmentId().getVoyageNumber()
                    + "' and DATE_DEPARTURE like '"
                    + awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture()
                    + "'");
            if (rst.next()) {
                return rst.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManifesteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public static String getReportFilename() {
        return reportFilename;
    }

    public static void setReportFilename(String reportFilename) {
        reportFilename = reportFilename;
    }

    public static HashMap<String, Object> getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(HashMap<String, Object> reportParameters) {
        this.reportParameters = reportParameters;
    }

    public static Awmds.GeneralSegment xtractSegmentGeneral(GeneralInfo sgDb, Statement stmt) {
        Awmds.GeneralSegment sgXml = obj.createAwmdsGeneralSegment();
        sgXml.setGeneralSegmentId(obj.createAwmdsGeneralSegmentGeneralSegmentId());
        sgXml.getGeneralSegmentId().setCustomsOfficeCode(sgDb.getCustomsOfficeCode());
        sgXml.getGeneralSegmentId().setVoyageNumber(sgDb.getVoyageNumber());
        sgXml.getGeneralSegmentId().setDateOfDeparture(sgDb.getDateDeparture());
        sgXml.getGeneralSegmentId().setDateOfArrival(sgDb.getDateArrival());
        sgXml.getGeneralSegmentId().setTimeOfArrival(sgDb.getTimeArrival());

        sgXml.setTransportInformation(obj.createAwmdsGeneralSegmentTransportInformation());
        sgXml.getTransportInformation().setCarrier(obj.createAwmdsGeneralSegmentTransportInformationCarrier());
        sgXml.getTransportInformation().getCarrier().setCarrierCode(sgDb.getCarrierCode());
        sgXml.getTransportInformation().getCarrier().setCarrierName(sgDb.getCarrierName());
        sgXml.getTransportInformation().getCarrier().setCarrierAddress(sgDb.getCarrierAddress());

        sgXml.getTransportInformation().setIdentityOfTransporter(sgDb.getIdentityTransporter());
        sgXml.getTransportInformation().setMasterInformation(sgDb.getMasterInformation());
        sgXml.getTransportInformation().setModeOfTransportCode(sgDb.getModeTransport());

        sgXml.getTransportInformation().setNationalityOfTransporterCode(sgDb.getNationalityTransporter());
        sgXml.getTransportInformation().setPlaceOfTransporter(sgDb.getPlaceOfTransporter());

        sgXml.setLoadUnloadPlace(obj.createAwmdsGeneralSegmentLoadUnloadPlace());
        System.out.println(sgDb.getPlaceOfDepartureCode());
        try {
            ResultSet rst = stmt.executeQuery("select code from papn_locode where libelle like '" + sgDb.getPlaceOfDepartureCode() + "'");
            if (rst.next()) {
                sgXml.getLoadUnloadPlace().setPlaceOfDepartureCode(rst.getString("code"));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getSQLState() + " : " + ex.getLocalizedMessage());
            LOG.error("SG ID : " + sgDb.getId());
        }
        try {
            ResultSet rst = stmt.executeQuery("select code from papn_locode where libelle like '" + sgDb.getPlaceOfDestinationCode() + "'");
            if (rst.next()) {
                sgXml.getLoadUnloadPlace().setPlaceOfDestinationCode(rst.getString("code"));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getSQLState() + " : " + ex.getLocalizedMessage());
            LOG.error("SG ID : " + sgDb.getId());
        }
        sgXml.setTotalsSegment(obj.createAwmdsGeneralSegmentTotalsSegment());
        sgXml.getTotalsSegment().setTotalGrossMass(sgDb.getTotalGrossMass());
        sgXml.getTotalsSegment().setTotalNumberOfBols(sgDb.getTotalNumberOfBols());
        sgXml.getTotalsSegment().setTotalNumberOfContainers(sgDb.getTotalNumberOfContainers());
        sgXml.getTotalsSegment().setTotalNumberOfPackages(sgDb.getTotalNumberOfPackages());

        sgXml.setTonnage(obj.createAwmdsGeneralSegmentTonnage());
        System.out.println(sgDb.getTonnageGrossWeight());

        sgXml.getTonnage().setTonnageGrossWeight(sgDb.getTonnageGrossWeight());
        sgXml.getTonnage().setTonnageNetWeight(sgDb.getTonnageNetWeight());

//        afficher(sgDb.toString());
        return sgXml;
    }

    public static Awmds.BolSegment xtractBol(BillOfLanding bol, Statement stmt) {
        Awmds.BolSegment ab = obj.createAwmdsBolSegment();
        ab.setBolId(obj.createAwmdsBolSegmentBolId());
        switch (bol.getBolNature()) {
            case "IMP":
                ab.getBolId().setBolNature("23");
            case "TRBI":
                ab.getBolId().setBolNature("28");
            case "EXP":
                ab.getBolId().setBolNature("22");
            case "TRBE":
                ab.getBolId().setBolNature("29");
            case "TRANSIT":
                ab.getBolId().setBolNature("24");
            default:
                ab.getBolId().setBolNature(bol.getBolNature());
        }

        ab.getBolId().setBolReference(bol.getBolReference());
        ab.getBolId().setBolTypeCode(bol.getBolTypeCode());
        ab.getBolId().setLineNumber(obj.createAwmdsBolSegmentBolIdLineNumber());
        ab.getBolId().getLineNumber().setValue(bol.getLineNumber());

        ab.setLoadUnloadPlace(obj.createAwmdsBolSegmentLoadUnloadPlace());
        try {
            ResultSet rst = stmt.executeQuery("select code from papn_locode where libelle like '" + bol.getPlaceOfLoadingCode() + "'");
            if (rst.next()) {
                ab.getLoadUnloadPlace().setPlaceOfLoadingCode(rst.getString("code"));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getSQLState() + " : " + ex.getLocalizedMessage());
            LOG.error("BOL ID : " + bol.getIdBol());
        }
        try {
            ResultSet rst = stmt.executeQuery("select code from papn_locode where libelle like '" + bol.getPlaceOfUnloadingCode() + "'");
            if (rst.next()) {
                ab.getLoadUnloadPlace().setPlaceOfUnloadingCode(rst.getString("code"));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getSQLState() + " : " + ex.getLocalizedMessage());
            LOG.error("BOL ID : " + bol.getIdBol());
        }
        ab.setLocation(obj.createAwmdsBolSegmentLocation());
        ab.getLocation().setLocationCode(bol.getLocationCode());
        ab.getLocation().setLocationInfo(bol.getLocationInfo());

        ab.setTradersSegment(obj.createAwmdsBolSegmentTradersSegment());
        ab.getTradersSegment().setConsignee(obj.createAwmdsBolSegmentTradersSegmentConsignee());
        ab.getTradersSegment().getConsignee().setConsigneeName(bol.getConsigneeName());
        ab.getTradersSegment().getConsignee().setConsigneeAddress(bol.getConsigneeAddress());
        ab.getTradersSegment().setExporter(obj.createAwmdsBolSegmentTradersSegmentExporter());
        ab.getTradersSegment().getExporter().setExporterName(bol.getExporterName());
        ab.getTradersSegment().getExporter().setExporterAddress(bol.getExporterAddress());
        ab.getTradersSegment().setNotify(obj.createAwmdsBolSegmentTradersSegmentNotify());
        ab.getTradersSegment().getNotify().setNotifyName(bol.getNotifyName());
        ab.getTradersSegment().getNotify().setNotifyAddress(bol.getNotifyAddress());

        ab.setGoodsSegment(obj.createAwmdsBolSegmentGoodsSegment());
        ab.getGoodsSegment().setGoodsDescription(bol.getGoodsDescription());
        ab.getGoodsSegment().setGrossMass(bol.getGrossMass());
        ab.getGoodsSegment().setNumOfCtnForThisBol((int) bol.getNumOfCtnForThisBol());
        ab.getGoodsSegment().setNumberOfPackages((int) bol.getNumberOfPackages());

        REF.pkg_table.entrySet().stream().filter((entry) -> (entry.getValue().equalsIgnoreCase(bol.getPackageTypeCode()))).forEachOrdered((entry) -> {
            ab.getGoodsSegment().setPackageTypeCode(entry.getKey());
        });
        ab.getGoodsSegment().setShippingMarks(bol.getShippingMarks());
        ab.getGoodsSegment().setVolumeInCubicMeters(bol.getVolumeInCubicMeters());
        ab.setValueSegment(obj.createAwmdsBolSegmentValueSegment());
//        afficher(bol.toString());
        return ab;
    }

    public static Awmds.BolSegment.CtnSegment xtractctn(Container ctn) {
        Awmds.BolSegment.CtnSegment abc = obj.createAwmdsBolSegmentCtnSegment();

        abc.setCtnReference(ctn.getCtnReference());
        abc.setTypeOfContainer(ctn.getTypeOfContainer());
        abc.setNumberOfPackages(Integer.valueOf(ctn.getNumberOfPackages()));
        abc.setEmptyFull(ctn.getEmptyFull());
        abc.setEmptyWeight(ctn.getEmptyWeight().isEmpty() || ctn.getEmptyWeight().equals("null") ? 0.0 : Double.valueOf(ctn.getEmptyWeight()));
        abc.setGoodsWeight(ctn.getGoodsWeight().isEmpty() || ctn.getEmptyWeight().equals("null") ? 0.0 : Double.valueOf(ctn.getGoodsWeight()));
        abc.setMarks1(ctn.getMarks1());
        abc.setMarks2(ctn.getMarks2());
        abc.setMarks3(ctn.getMarks3());
        abc.setSealingParty(ctn.getSealingParty());
//        afficher(ctn.toString());
        return abc;
    }
}
