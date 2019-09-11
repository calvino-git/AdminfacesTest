/*
 * To searchPortTRB this license header, choose License Headers in Project Properties.
 * To searchPortTRB this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import asycuda.awmds.Awmds;
import static bases.Const.LOG;
import jpa.beans.CongoTerminal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author calvin0
 */
public class PortDestination2 {

    /**
     * @param args the command line arguments
     */
    static int test = 0;
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ASYCUDAPU");
    private static final EntityManager EM = EMF.createEntityManager();
    private static List<CongoTerminal> listCtnr;
    private static LocalDate date;
    private static String dateFin;
    private static String dateDeb;

    public static void searchPortTRB(Awmds manifest) {
        // TODO code application logic here
        TypedQuery<CongoTerminal> query = manifest.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode().equals("CGPNR") ? EM.createQuery("SELECT c FROM CongoTerminal c WHERE c.numCtn = :numCtn and c.dateDep between :dateDeb and :dateFin", CongoTerminal.class) : manifest.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode().equals("CGPNR") ? EM.createQuery("SELECT c FROM CongoTerminal c WHERE c.numCtn = :numCtn and c.dateArr between :dateDeb and :dateFin", CongoTerminal.class) : null;

        LOG.info("######### VOYAGE N° " + manifest.getGeneralSegment().getGeneralSegmentId().getVoyageNumber() + "###########");

        referenceTable ref = new referenceTable();
        manifest.getBolSegment().forEach(bol -> {
            test = 0;
            //au cas d'un interversion des ports
//            if (bol.getBolId().getBolNature().equals("22") || bol.getBolId().getBolNature().equals("29")) {
//                String tmp = bol.getLoadUnloadPlace().getPlaceOfLoadingCode();
//                bol.getLoadUnloadPlace().setPlaceOfLoadingCode(bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());
//                bol.getLoadUnloadPlace().setPlaceOfUnloadingCode(tmp);
//            }
            if (bol.getBolId().getBolNature().equals("28") || bol.getBolId().getBolNature().equals("29")) {
                LOG.info("#######################");
                LOG.info("BOL N°" + manifest.getBolSegment().indexOf(bol) + " => REF : " + bol.getBolId().getBolReference());
                LOG.info("Nature : " + ref.nature.get(bol.getBolId().getBolNature()).toUpperCase());
                LOG.info("##############################################################");
                date = LocalDate.parse(bol.getBolId().getBolNature().equals("28") ? manifest.getGeneralSegment().getGeneralSegmentId().getDateOfArrival().substring(0, 10) : manifest.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture().substring(0, 10));

                dateDeb = String.valueOf(date.minusDays(3)).replaceAll("-", "");
                dateFin = String.valueOf(date.plusDays(3)).replaceAll("-", "");

                for (Awmds.BolSegment.CtnSegment ctn : bol.getCtnSegment()) {
                    if (query != null) {
                        listCtnr = query.setParameter("numCtn", ctn.getCtnReference()).setParameter("dateDeb", "20190701").setParameter("dateFin", "20190731").getResultList();
                        if (!listCtnr.isEmpty()) {
                            LOG.info("Resultat de " + ctn.getCtnReference() + " : " + listCtnr.size());
                            boolean isFound = false;
                            for (CongoTerminal ctnr : listCtnr) {
                                if (ctnr.getNumCtn().equals(ctn.getCtnReference()) /*&& row.getCell(4).getRawValue().equals(manifest.getGeneralSegment().getGeneralSegmentId().getVoyageNumber())*/) {
                                    LOG.info("Conteneur XML : POL / POD = " + ctn.getCtnReference() + " : " + bol.getLoadUnloadPlace().getPlaceOfLoadingCode() + "/" + bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());
                                    bol.getLoadUnloadPlace().setPlaceOfLoadingCode(bol.getLoadUnloadPlace().getPlaceOfLoadingCode() == null ? "CGPNR" : bol.getLoadUnloadPlace().getPlaceOfLoadingCode());
                                    if (bol.getLoadUnloadPlace().getPlaceOfLoadingCode().equals("CGPNR") && ctnr.getTrafic().equals("T") && ctnr.getPol() != null) {
                                        if (ctnr.getMouvement().equals("EMBA")) {
                                            bol.getLoadUnloadPlace().setPlaceOfLoadingCode(ctnr.getPol());
                                            isFound = true;
                                        }
//                                        LOG.info("PORT DEPART SYDONIA : " + bol.getLoadUnloadPlace().getPlaceOfLoadingCode());
//                                        LOG.info("PORT DEPART CONGO TERMINAL : " + ctnr.getPol());
//                                        //bol.getLoadUnloadPlace().setPlaceOfUnloadingCode(row.getCell(4).getStringCellValue());
//                                        LOG.info("PORT ARRIVE SYDONIA : " + bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());
//                                        LOG.info("PORT ARRIVE CONGO TERMINAL : " + ctnr.getPod());
//                                        LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                    } else if (bol.getLoadUnloadPlace().getPlaceOfUnloadingCode().equals("CGPNR") && ctnr.getTrafic().equals("T") && ctnr.getPol() != null) {
                                        //bol.getLoadUnloadPlace().setPlaceOfLoadingCode(row.getCell(3).getStringCellValue());
                                        if (ctnr.getMouvement().equals("DEBA")) {
                                            bol.getLoadUnloadPlace().setPlaceOfUnloadingCode(ctnr.getPod());
                                            isFound = true;
                                        }
//                                        LOG.info("PORT DEPART SYDONIA : " + bol.getLoadUnloadPlace().getPlaceOfLoadingCode());
//                                        LOG.info("PORT DEPART CONGO TERMINAL : " + ctnr.getPol());
//                                        //bol.getLoadUnloadPlace().setPlaceOfUnloadingCode(row.getCell(4).getStringCellValue());
//                                        LOG.info("PORT ARRIVE SYDONIA : " + bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());
//                                        LOG.info("PORT ARRIVE CONGO TERMINAL : " + ctnr.getPod());
//                                        LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                    } else {
                                        LOG.info("Conteneur N° " + ctnr.getNumCtn() + " NON TROUVÉ");
                                    }

                                    test = 1;
                                }
                                //                            LOG.info("##############################################################");
                                if (test == 1) {
                                    break;
                                }
                                if (isFound = true) {
                                    break;
                                }
                            }
                        }
                    } else {
                        continue;
                    }
                    if (test == 1) {
                        break;
                    }
                }
                LOG.info("Bol " + bol.getBolId().getBolReference() + ": " + bol.getLoadUnloadPlace().getPlaceOfLoadingCode() + " => " + bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());
            }
        });
    }

}
