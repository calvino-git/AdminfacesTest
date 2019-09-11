/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import static bases.Const.LOG;
import static bases.Const.PROPERTIES;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author Admin
 */
public class FtpClient {

    public static void downloadFTPFile(FTPClient ftp, String source, String localdestination, String ftpMoveFileDestination) {
        try (FileOutputStream fos = new FileOutputStream(localdestination)) {
            ftp.retrieveFile(source, fos);
            if (ftpMoveFileDestination != null && !"".equals(ftpMoveFileDestination)) {
                boolean resp = ftp.rename(source, ftpMoveFileDestination);
                ftp.deleteFile(source);
                LOG.info(source + " supprimé.");
                //resp=(resp)?this.ftp.deleteFile(source):false;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<String> listFTPFiles( FTPClient ftp, String directory, String localDirectory, String ftpMoveFileDirectory) {
        File manDoss = new File(localDirectory);
        List<String> arrayFileNames = new ArrayList<>();
        FTPFile[] files;

        if (manDoss.exists() == false) {
            manDoss.mkdirs();
        }

        try {
            files = ftp.listFiles(directory);

            if (files.length > 0) {
                LOG.info("FTP server - dossier des manifestes: " + directory);
                LOG.info("Fichiers trouvés : " + files.length);
                for (FTPFile file : files) {
                    if (file.getSize() == 0) {
                        ftp.rename(directory + "/" + file.getName(), ftpMoveFileDirectory + "/" + file.getName());
                        continue;
                    }
                    LOG.info(directory + file.getName());
                    String details = file.getName();
//			String numeroEscale = Mani;
//			if (etbFileNames.containsKey(numeroEscale)) {
                    downloadFTPFile(ftp, directory + "/" + details, localDirectory + File.separator + details, ftpMoveFileDirectory + "/" + details);
                    arrayFileNames.add(localDirectory + File.separator + details);

                    LOG.info(details + " envoyé dans le dossier " + localDirectory);
                }
            }
        } catch (Exception ex) {
            LOG.info("Problème d'accès au dossier : " + directory + "\n" + ex.getMessage());
        }
        return arrayFileNames;
    }

    public static boolean connect(FTPClient ftp, Boolean sendFile) {
        boolean connect = false;
        try {
            int reply;
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            conf.setServerLanguageCode("fr");
            ftp.configure(conf);
            ftp.connect(PROPERTIES.getString("ftp.host"), Integer.valueOf(PROPERTIES.getString("ftp.port")));
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }

            connect = ftp.login(PROPERTIES.getString("ftp.user"), PROPERTIES.getString("ftp.password"));
            if (!connect) {
                LOG.warn("FTP connecté");
                ftp.disconnect();
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (sendFile) {
                ftp.enterLocalActiveMode();
            } else {
                ftp.enterLocalPassiveMode();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
//            LOG.error(e.getLocalizedMessage());
        }
        return connect;
    }

    public static void disconnect(FTPClient ftp) {
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
    }

}
