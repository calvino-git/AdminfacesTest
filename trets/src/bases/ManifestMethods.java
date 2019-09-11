/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import java.io.File;
import asycuda.awmds.Awmds;
import dao.DbHandler;
import dao.Escale;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static bases.Const.conn;
import static bases.Const.LOG;
import static bases.Const.PROPERTIES;
import static bases.XmlObject.xmlToAwmds;
import java.time.LocalTime;
/**
 *
 * @author CALVINO
 */
public class ManifestMethods {
    public static Awmds validationManifest(File fileXML,boolean isManifest, String DOSSIER_MANIFEST_ERR ) {
        isManifest = true;
        if (fileXML.length() == 0 || !fileXML.getName().toLowerCase().endsWith(".xml")) {
            isManifest = false;
            return null;
        }
        Awmds awmds = xmlToAwmds(fileXML);
        if(awmds == null) return awmds;
        String BUREAU = awmds.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode();
        if (!("DD147".equals(BUREAU) || "DD141".equals(BUREAU))) {
            LOG.warn("Le bureau " + awmds.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode() + " ne concerne pas le PAPN.");
            isManifest = false;
        }
        if (isManifest == false) {
            LOG.info(fileXML.getName() 
                    + "Manifest NON VALIDE : LIEU DE DEPART - "
                    + awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode()
                    + " et LIEU D'ARRIVEE - "
                    + awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode());
            
            //Deplacement du fichier vers le dossier des manifestes non valide
            fileXML.renameTo(new File(DOSSIER_MANIFEST_ERR + fileXML.getName()));
           
        }
        return isManifest == false ? null : awmds;
    }
    
    public static Boolean isManifest(Awmds manifeste) {
        return isManifestImport(manifeste) || isManifestExport(manifeste);

    }

    public static Boolean isManifestExport(Awmds manifeste) {
        return manifeste.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGHTM") || manifeste.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNR") || manifeste.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNP");

    }

    public static Boolean isManifestImport(Awmds manifeste) {
        return manifeste.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGHTM") || manifeste.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNR") || manifeste.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNP");

    }
    
    public static LocalDate dateFormater(String date) {
        return LocalDate.parse(date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10), DateTimeFormatter.BASIC_ISO_DATE);
    }
    
    public static void copyInfoManifest(Escale newEscale, Awmds man, String trafic) {
        newEscale.setNavire(man.getGeneralSegment().getTransportInformation().getIdentityOfTransporter());
        newEscale.setVoyage(man.getGeneralSegment().getGeneralSegmentId().getVoyageNumber());
        newEscale.setDateDepart(man.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture());
        newEscale.setDateArrivee(man.getGeneralSegment().getGeneralSegmentId().getDateOfArrival());
        newEscale.setNumero("");
        newEscale.setEscleunik(0);
        newEscale.setTrafic(trafic);

        LOG.info("==> Navire : " + newEscale.getNavire());
        LOG.info("==> Numéro de voyage : " + newEscale.getVoyage());
        LOG.info("==> Date de départ : " + newEscale.getDateDepart().substring(0, 4) + newEscale.getDateDepart().substring(5, 7) + newEscale.getDateDepart().substring(8, 10));
        LOG.info("==> Date d'arrivée : " + newEscale.getDateArrivee().substring(0, 4) + newEscale.getDateArrivee().substring(5, 7) + newEscale.getDateArrivee().substring(8, 10));
    }
    
    public static Escale searchEscaleForExport(Escale escaleCible) {
        conn = DbHandler.getDbConnection();
        List<Escale> data = new ArrayList<>();
        String queryString = "SELECT escale.escleunik,  "
                + "escale.navire Navire,  "
                + "escale.numero Numero,  "
                + "escale.voyage Voyage,  "
                + "escale.agent Consignataire,  "
                + "to_char(to_date(escale.DATE_PASSE_ENTREE,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') DateArrivee,  "
                + "to_char(to_date(escale.depart,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') DateDepart,  "
                + "to_char(to_date(escale.depart_effectif,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') DateDepartEff  "
                + "FROM escale   "
                + "WHERE regexp_replace(regexp_replace(regexp_replace(regexp_replace(regexp_replace(escale.navire,'MT\\W',''),'MV\\W',''),'MTS\\W',''),'M/V\\W',''),'\\W','') like ?  "
                + "and to_char(to_date(escale.depart_effectif,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') BETWEEN ? AND ?";

        if (conn != null) {
            try {
                LOG.info("===> Connexion à la base de donnnée avec succès.");
                PreparedStatement pst = conn.prepareStatement(queryString);
                LOG.info("********************************************");

                String debut = dateFormater(escaleCible.getDateDepart()).minusDays(7).format(DateTimeFormatter.BASIC_ISO_DATE);
                String fin = dateFormater(escaleCible.getDateDepart()).plusDays(7).format(DateTimeFormatter.BASIC_ISO_DATE);

                LOG.info("Recherche des escales probable du navire " + escaleCible.getNavire().replace("-", " ").replace(" ", "")
                        + " sur la période du " + debut + " au " + fin);

                pst.setString(1, escaleCible.getNavire().replace("MT ", "").replace("MV ", "").replace("MTS ", "").replace("M/V ", "").replace(" ", "") + "%");
                pst.setString(2, debut);
                pst.setString(3, fin);
                ResultSet rst = pst.executeQuery();
                while (rst.next()) {
                    data.add(new Escale(rst.getString("Voyage"), rst.getString("Navire"), rst.getString("DateArrivee"), rst.getString("DateDepart"), rst.getString("Numero"), rst.getInt("escleunik"), "EXPORT"));
                }
                LOG.info("********************************************");
                LOG.info("Nombre d'escales trouvés : " + data.size());
                LOG.info("********************************************");
                conn.close();

                data.forEach(esc -> {
//                    if (esc.getNavire().contains(escaleCible.getNavire())) {
                    LOG.info("Navire :" + esc.getNavire() + "/ " + escaleCible.getNavire());
                    LOG.info("Voyage :" + esc.getVoyage() + "/ " + escaleCible.getVoyage());
                    LOG.info("Date départ :" + esc.getDateDepart() + "/ " + escaleCible.getDateDepart());
                    LOG.info("Date Arrivée :" + esc.getDateArrivee() + "/ " + escaleCible.getDateArrivee());
                    LOG.info("Numero Escale :" + esc.getNumero() + "/ " + escaleCible.getNumero());
                    LOG.info("Escleunik :" + esc.getEscleunik() + "/ ");
                    
                    escaleCible.setEscleunik(esc.getEscleunik());
                    escaleCible.setNumero(esc.getNumero());
//                    }
                    LOG.info("********************************************");
                });
            } catch (SQLException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
        }
        return escaleCible;
    }

    public static Escale searchEscaleForImport(Escale escaleCible) {
        conn = DbHandler.getDbConnection();
        List<Escale> data = new ArrayList<>();
        String queryString = "SELECT escale.escleunik,  "
                + "escale.navire Navire,  "
                + "escale.numero Numero,  "
                + "escale.voyage Voyage,  "
                + "escale.agent Consignataire,  "
                + "to_char(to_date(escale.DATE_PASSE_ENTREE,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') DateArrivee,  "
                + "to_char(to_date(escale.depart,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') DateDepart,  "
                + "to_char(to_date(escale.depart_effectif,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') DateDepartEff  "
                + "FROM escale   "
                + "WHERE regexp_replace(regexp_replace(regexp_replace(regexp_replace(regexp_replace(escale.navire,'MT\\W',''),'MV\\W',''),'MTS\\W',''),'M/V\\W',''),' ','') like ?  "
                + "and to_char(to_date(escale.DATE_PASSE_ENTREE,'YYYYMMDD','NLS_DATE_LANGUAGE=AMERICAN'),'YYYYMMDD') BETWEEN ? AND ?";

        if (conn != null) {
            try {
                LOG.info("===> Connexion à la base de donnnée avec succès.");
                PreparedStatement pst = conn.prepareStatement(queryString);
                LOG.info("********************************************");

                String debut = dateFormater(escaleCible.getDateArrivee()).minusDays(3).format(DateTimeFormatter.BASIC_ISO_DATE);
                String fin = dateFormater(escaleCible.getDateArrivee()).plusDays(3).format(DateTimeFormatter.BASIC_ISO_DATE);

                LOG.info("Recherche des escales probable du navire " + escaleCible.getNavire().replace("-", " ").replace(" ", "")
                        + " sur la période du " + debut + " au " + fin);

                pst.setString(1, escaleCible.getNavire().replace("MT ", "").replace("MV ", "").replace("MTS ", "").replace("M/V ", "").replace(" ", "") + "%");
                pst.setString(2, debut);
                pst.setString(3, fin);
                ResultSet rst = pst.executeQuery();
                while (rst.next()) {
                    data.add(new Escale(rst.getString("Voyage"), rst.getString("Navire"), rst.getString("DateArrivee"), rst.getString("DateDepart"), rst.getString("Numero"), rst.getInt("escleunik"),"IMPORT"));
                }
                LOG.info("********************************************");
                LOG.info("Nombre d'escales trouvés : " + data.size());
                LOG.info("********************************************");
                conn.close();

                data.forEach(esc -> {
//                    if (esc.getNavire().contains(escaleCible.getNavire())) {
                    LOG.info("Navire :" + esc.getNavire() + "/ " + escaleCible.getNavire());
                    LOG.info("Voyage :" + esc.getVoyage() + "/ " + escaleCible.getVoyage());
                    LOG.info("Date départ :" + esc.getDateDepart() + "/ " + escaleCible.getDateDepart());
                    LOG.info("Date Arrivée :" + esc.getDateArrivee() + "/ " + escaleCible.getDateArrivee());
                    LOG.info("Numero Escale :" + esc.getNumero() + "/ " + escaleCible.getNumero());
                    LOG.info("Escleunik :" + esc.getEscleunik() + "/ ");

                    escaleCible.setEscleunik(esc.getEscleunik());
                    escaleCible.setNumero(esc.getNumero());
//                    }
                    LOG.info("********************************************");
                });
            } catch (SQLException ex) {
                LOG.info(ex.getLocalizedMessage());
            }
        }
        return escaleCible;
    }

    public static String[] getManLocalEscaleDirectoryForExport(Escale escale) {
        String str = Const.DOSSIER_MANIFEST_OUT;
        String str2 = Const.DOSSIER_DC;
//		Escale escale = null;
//		try {
//			escale = ManifestService.getInfoEscale(escleunik);
//		} catch (SQLException e) {
//			LOG.error(e);
//			e.printStackTrace();
//		}
        str = str + File.separator;
        str2 = str2 + File.separator;
        if (escale.getNumero() != null) {
            String dateDepart = escale.getDateDepart();
            // String day =dateArrivee.substring(6,8);
            String month = dateDepart.substring(5, 7);
            String year = dateDepart.substring(0, 4);
            String numeroEscale = escale.getNumero();
            String navire = escale.getNavire().replace(" ", "_");
            String trafic = escale.getTrafic();
            str = str + year + File.separator + month + File.separator + trafic + File.separator +  navire + File.separator
                    + numeroEscale;
            str2 = str2 + year + File.separator + month + File.separator + trafic + File.separator + navire + File.separator
                    + numeroEscale;
        }
        Path path = Paths.get(str);
        Path path2 = Paths.get(str2);
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
                LOG.info(str + " creé avec succes.");
            } catch (IOException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
        }
        if (!Files.isDirectory(path2)) {
            try {
                Files.createDirectories(path2);
                LOG.info(str2 + " creé avec succes.");
            } catch (IOException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
        }
        String[] strOut = new String[2];
        strOut[0] = str;
        strOut[1] = str2;
        return strOut;

    }

    public static String[] getManLocalEscaleDirectoryForImport(Escale escale) {
        String str = PROPERTIES.getString("dossier.manifest.out");
        String str2 = PROPERTIES.getString("dossier.dc");
//		Escale escale = null;
//		try {
//			escale = ManifestService.getInfoEscale(escleunik);
//		} catch (SQLException e) {
//			LOG.error(e);
//			e.printStackTrace();
//		}
        str = str + File.separator;
        str2 = str2 + File.separator;
        if (escale.getNumero() != null) {
            String dateArrivee = escale.getDateArrivee();
            // String day =dateArrivee.substring(6,8);
            String month = dateArrivee.substring(5, 7);
            String year = dateArrivee.substring(0, 4);
            String numeroEscale = escale.getNumero();
            String navire = escale.getNavire().replace(" ", "_");
            String trafic = escale.getTrafic();
            str = str + File.separator + year + File.separator + month + File.separator + trafic + File.separator + navire + File.separator
                    + numeroEscale;
            str2 = str2 + File.separator + year + File.separator + month + File.separator + trafic + File.separator + navire + File.separator
                    + numeroEscale;
        }
        Path path = Paths.get(str);
        Path path2 = Paths.get(str2);
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
                LOG.info(str + " creé avec succes.");
            } catch (IOException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
        }
        if (!Files.isDirectory(path2)) {
            try {
                Files.createDirectories(path2);
                LOG.info(str2 + " creé avec succes.");
            } catch (IOException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
        }
        String[] strOut = new String[2];
        strOut[0] = str;
        strOut[1] = str2;
        return strOut;

    }

    public static String[] renameManifest(Awmds man, String sourceName) {
        LOG.info("Generation du nom complet...");
        String[] str;
        String[] trim = sourceName.split("-");
        Escale newEscale = new Escale();
        if (isManifestExport(man)) {
//            String str1 = strManifestName.getName().substring(PRE_MAN_EXP.length());
            LOG.info("MANIFESTE EXPORT ");
            copyInfoManifest(newEscale, man, "EXPORT");
            
            str = getManLocalEscaleDirectoryForExport(searchEscaleForExport(newEscale));
            //getETB(newEscale.getNumero());
            if (sourceName.startsWith("Manifest-PAPN-EXP")) {
                str[0] = str[0] + File.separator + "Manifest-PAPN-EXP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + trim[trim.length-2]
                        + "-" + trim[trim.length-1];
                str[1] = str[1] + File.separator + "Manifest-PAPN-EXP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" +  trim[trim.length-2]
                        + "-" + trim[trim.length-1];
                return str;
            } else {
                str[0] = str[0] + File.separator + "Manifest-PAPN-EXP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "")
                        + ".xml";
                str[1] = str[1] + File.separator + "Manifest-PAPN-EXP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "")
                        + ".xml";
                return str;
            }
        } else if (isManifestImport(man)) {
            LOG.info("MANIFESTE IMPORT ");
            copyInfoManifest(newEscale, man, "IMPORT");
            if (sourceName.startsWith("Manifest-PAPN-IMP")) {
                newEscale.setEscleunik(Integer.valueOf(trim[3]));
                newEscale.setNumero(trim[4]);
                str = getManLocalEscaleDirectoryForImport(newEscale);
                str[0] = str[0] + File.separator + sourceName;
                str[1] = str[1] + File.separator + sourceName;
                return str;
            } //            else if (sourceName.startsWith("Manifest-PAPN-")) {
            //                newEscale.setEscleunik(Integer.valueOf(trim[3]));
            //                newEscale.setNumero(trim[4]);
            //                str = getManLocalEscaleDirectoryForImport(newEscale);
            //                str[0] = str[0] + File.separator + sourceName.substring(0, 13) + "-IMP-" + sourceName.substring(sourceName.length() - 54);
            //                str[1] = str[1] + File.separator + sourceName.substring(0, 13) + "-IMP-" + sourceName.substring(sourceName.length() - 54);
            //                return str;
            //            } 
            else if (sourceName.startsWith("Manifest-PAPN-EXP")) {
                str = getManLocalEscaleDirectoryForImport(searchEscaleForImport(newEscale));
                str[0] = str[0] + File.separator + "Manifest-PAPN-IMP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" +  trim[trim.length-2]
                        + "-" + trim[trim.length-1];
                str[1] = str[1] + File.separator + "Manifest-PAPN-IMP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" +  trim[trim.length-2]
                        + "-" + trim[trim.length-1];

                return str;
            } else {
                str = getManLocalEscaleDirectoryForImport(searchEscaleForImport(newEscale));
                str[0] = str[0] + File.separator + "Manifest-PAPN-IMP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "")
                        + ".xml";
                str[1] = str[1] + File.separator + "Manifest-PAPN-IMP-" + newEscale.getEscleunik() + "-" + newEscale.getNumero()
                        + "-" + dateFormater(newEscale.getDateDepart()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + dateFormater(newEscale.getDateArrivee()).format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                        + "-" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "")
                        + ".xml";
                return str;
            }
        } else {
            return null;
        }

    }
    //Take the instance of Awmds object and save into a xml file in a specific location
   
    

}
