package info.coremodding.api.net.ftp;

import info.coremodding.api.io.IOUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.URL;

/**
 * @author James An FTP connection
 */
class FTPConnection {

    private FTPClient ftp = null;

    /**
     * @param host The host
     * @param user The user
     * @param pwd  The password
     * @throws Exception Something screwed up!
     */
    public FTPConnection(String host, String user, String pwd) throws Exception {
        this.ftp = new FTPClient();
        this.ftp.addProtocolCommandListener(new PrintCommandListener(
                new PrintWriter(System.out)));
        int reply;
        this.ftp.connect(host);
        reply = this.ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.ftp.disconnect();
            throw new Exception("Error in connecting to FTP Server");
        }
        this.ftp.login(user, pwd);
        this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
        this.ftp.enterLocalPassiveMode();
    }

    /**
     * Disconnect from the server
     */
    public void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }
    }

    /**
     * @param remoteFilePath The path on the server
     * @param localFilePath  The path to save to
     */
    public void downloadFile(String remoteFilePath, String localFilePath) {
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            this.ftp.retrieveFile(remoteFilePath, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param remoteFilePath The path on the server
     * @return The file
     */
    public String[] downloadFileToMemory(String remoteFilePath) {
        try (FileOutputStream fos = new FileOutputStream("..\\temp.file")) {
            this.ftp.retrieveFile(remoteFilePath, fos);
            String[] file = IOUtils.readFile("..\\temp.file");
            if (!(new File("..\\temp.file").delete())) {
                System.out.println("There may have been an error!");
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param localFileFullName The file full name here
     * @param fileName          The file name on the server
     * @param hostDir           The host directory
     * @throws Exception Something screwed up!
     */
    public void uploadFile(String localFileFullName, String fileName,
                           String hostDir) throws Exception {
        try (InputStream input = new FileInputStream(
                new File(localFileFullName))) {
            this.ftp.storeFile(hostDir + fileName, input);
        }
    }

    /**
     * @param url      The URL to put on the server
     * @param fileName The file name on the server
     * @param hostDir  The host directory
     * @throws Exception Something screwed up!
     */
    public void uploadFileURL(String url, String fileName, String hostDir)
            throws Exception {
        try (InputStream input = new URL(url).openStream()) {
            this.ftp.storeFile(hostDir + fileName, input);
        }
    }
}
