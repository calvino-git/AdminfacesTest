/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import asycuda.awmds.Awmds;
import static bases.Const.LOG;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Admin
 */
public class XmlObject {

    private static JAXBContext jc;
    private static Unmarshaller unmarshaller;
    private static Marshaller marshaller;
    
    public static Awmds xmlToAwmds(File xmlFichier) {
        LOG.info("Chargement du fichier XML en objet AWMDS");
        try {
            jc = JAXBContext.newInstance(Awmds.class);
            unmarshaller = jc.createUnmarshaller();
            return (Awmds) unmarshaller.unmarshal(xmlFichier);
        } catch (JAXBException ue) {
            LOG.error("Fichier XML non valide \n" + ue.getMessage());
        }
        return null;
    }
    public static void AwmdsToXml(Awmds awmds, File file) {
        try {
            jc = JAXBContext.newInstance(Awmds.class);
            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshalling and saving XML to the file.
            marshaller.marshal(awmds, file);

        } catch (JAXBException e) { // catches ANY exception
            LOG.info("JAXBException error code : " + e.getErrorCode() + " " + e.getMessage());
        }
    }
}
