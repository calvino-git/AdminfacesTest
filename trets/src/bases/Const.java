/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.controlleurs.BillOfLandingJpaController;
import jpa.controlleurs.ContainerJpaController;
import jpa.controlleurs.EscaleJpaController;
import jpa.controlleurs.GeneralInfoJpaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Admin
 */
public class Const {

    public static Connection conn = null;
    public static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("properties.config");
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final String PRE_MAN_EXP = "Manifest";
    public static final String PRE_MAN_IMP = "Manifest";
    public static final String PRE_MAN = "";
    public static final String SUB_REPORT = "./data/";
    public static final String MAIN_REPORT = "./data/general";
    public static final String BOL_REPORT = "./data/bols";
    public static final String CTN_REPORT = "./data/ctn";
    public static final String CARGO_DRIVER = PROPERTIES.getString("cargo.driver");
    public static final String CARGO_FULL_URL = PROPERTIES.getString("cargo.fullurl");
    public static final String CARGO_USER = PROPERTIES.getString("cargo.user");
    public static final String CARGO_PASSWORD = PROPERTIES.getString("cargo.password");
    public static final String DOSSIER_MANIFEST_ERR = PROPERTIES.getString("dossier.manifest.err");
    public static final String DOSSIER_MANIFEST_IN = PROPERTIES.getString("dossier.manifest.in");
    public static final String DOSSIER_MANIFEST_ARC = PROPERTIES.getString("dossier.manifest.arc");
    public static final String DOSSIER_MANIFEST_OUT = PROPERTIES.getString("dossier.manifest.out");
    public static final String DOSSIER_FTP_IN = PROPERTIES.getString("ftp.comingManifest");
    public static final String DOSSIER_FTP_ARC = PROPERTIES.getString("ftp.archiveManifeste");
    public static final String DOSSIER_DC = PROPERTIES.getString("dossier.dc");
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ASYCUDAPU");
    public static final GeneralInfoJpaController GIJC = new GeneralInfoJpaController(EMF);
    public static final BillOfLandingJpaController BOLJC = new BillOfLandingJpaController(EMF);
    public static final ContainerJpaController CTNJC = new ContainerJpaController(EMF);
    public static final EscaleJpaController ESCJC = new EscaleJpaController(EMF);
    public static final Logger LOG = LoggerFactory.getLogger("LOG_RECORD");
    public static final referenceTable REF = new referenceTable();

    public static final String QUERY_SG = "INSERT INTO GENERAL_INFO VALUES (SEQ_PAPN_GENERAL_INFO.nextval"
            + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("-", "") + "')";
    public static final String SEQ_SG = "select SEQ_PAPN_GENERAL_INFO.currval from dual ";

    public static final String QUERY_BOL = "INSERT INTO BILL_OF_LANDING VALUES (SEQ_PAPN_BILLOFLANDING.nextval"
            + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SEQ_PAPN_GENERAL_INFO.currval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SEQ_BOL = "select SEQ_PAPN_BILLOFLANDING.currval from dual ";

    public static final String QUERY_CTN = "INSERT INTO CONTAINER VALUES (SEQ_PAPN_CONTAINER.nextval"
            + ",?,?,?,?,SEQ_PAPN_BILLOFLANDING.currval,?,?,?,?,?,?)";
    public static final String SEQ_CTN = "select SEQ_PAPN_CONTAINER.currval from dual ";

    public static final String QUERY_CT = "INSERT INTO CONGO_TERMINAL VALUES (SEQ_PAPN_CONGO_TERMINAL.nextval"
            + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SEQ_CT = "select SEQ_PAPN_CONGO_TERMINAL.currval from dual ";

}
