package info.coremodding.api.net.ftp;

import info.coremodding.api.net.exception.BadFTPReplyException;
import info.coremodding.api.net.exception.FTPError;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author James
 *         Utils related to the apache FTP client
 */
public class FTPClientUtils
{
    
    /**
     * @param host
     *            The host to connect to
     * @param username
     *            The username to logon with
     * @param password
     *            The password to logon with
     * @param startdir
     *            The directory to start at
     * @return The FTP client
     * @throws IOException
     *             Something screwed up
     * @throws SocketException
     *             Something screwed up
     * @throws FTPError
     *             Something screwed up
     * @throws BadFTPReplyException
     *             Something screwed up
     */
    public static FTPClient generateFTPClient(String host, String username,
            String password, String startdir) throws SocketException,
            IOException, FTPError
    {
        FTPClient client = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        client.configure(config);
        client.connect(host);
        client.login(username, password);
        if (!FTPReply.isPositiveCompletion(client.getReply())) { throw new BadFTPReplyException(); }
        if (!client.changeWorkingDirectory(startdir)) { throw new FTPError(); }
        return client;
    }
    
    /**
     * @param host
     *            The host to connect to
     * @param username
     *            The username to logon with
     * @param password
     *            The password to logon with
     * @return The FTP client
     * @throws IOException
     *             Something screwed up
     * @throws SocketException
     *             Something screwed up
     * @throws BadFTPReplyException
     *             Something screwed up
     */
    public static FTPClient generateFTPClient(String host, String username,
            String password) throws SocketException, IOException,
            BadFTPReplyException
    {
        FTPClient client = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        client.configure(config);
        client.connect(host);
        client.login(username, password);
        if (!FTPReply.isPositiveCompletion(client.getReply())) { throw new BadFTPReplyException(); }
        return client;
    }
    
    /**
     * @param ftp
     *            The FTP client to change the directory on
     * @param dir
     *            The directory to go to
     * @throws FTPError
     *             Something went wrong!
     * @throws IOException
     *             Something went wrong!
     */
    public static void gotoDir(FTPClient ftp, String dir) throws FTPError,
            IOException
    {
        ftp.changeWorkingDirectory("/");
        if (!ftp.changeWorkingDirectory(dir)) { throw new FTPError(); }
    }
    
    /**
     * Download a single file from the FTP server
     * 
     * @param ftpClient
     *            an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param remoteFilePath
     *            path of the file on the server
     * @param savePath
     *            path of directory where the file will be stored
     * @return true if the file was downloaded successfully, false otherwise
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static boolean downloadSingleFile(FTPClient ftpClient,
            String remoteFilePath, String savePath) throws IOException
    {
        File downloadFile = new File(savePath);
        
        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists())
        {
            parentDir.mkdir();
        }
        
        @SuppressWarnings("resource")
        OutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(downloadFile));
        try
        {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (IOException ex)
        {
            throw ex;
        } finally
        {
            outputStream.close();
        }
    }
    
    /**
     * Download a whole directory from a FTP server.
     * 
     * @param ftpClient
     *            an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param parentDir
     *            Path of the parent directory of the current directory being
     *            downloaded.
     * @param currentDir
     *            Path of the current directory being downloaded.
     * @param saveDir
     *            path of directory where the whole remote directory will be
     *            downloaded and saved.
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static void downloadDirectory(FTPClient ftpClient, String parentDir,
            String currentDir, String saveDir) throws IOException
    {
        String dirToList = parentDir;
        if (!currentDir.equals(""))
        {
            dirToList += "/" + currentDir;
        }
        
        FTPFile[] subFiles = ftpClient.listFiles(dirToList);
        
        if (subFiles != null && subFiles.length > 0)
        {
            for (FTPFile aFile : subFiles)
            {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals(".."))
                {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath = parentDir + "/" + currentDir + "/"
                        + currentFileName;
                if (currentDir.equals(""))
                {
                    filePath = parentDir + "/" + currentFileName;
                }
                
                String newDirPath = saveDir + parentDir + File.separator
                        + currentDir + File.separator + currentFileName;
                if (currentDir.equals(""))
                {
                    newDirPath = saveDir + parentDir + File.separator
                            + currentFileName;
                }
                
                if (aFile.isDirectory())
                {
                    // create the directory in saveDir
                    File newDir = new File(newDirPath);
                    boolean created = newDir.mkdirs();
                    if (created)
                    {
                        System.out.println("CREATED the directory: "
                                + newDirPath);
                    } else
                    {
                        System.out.println("COULD NOT create the directory: "
                                + newDirPath);
                    }
                    
                    // download the sub directory
                    downloadDirectory(ftpClient, dirToList, currentFileName,
                            saveDir);
                } else
                {
                    // download the file
                    boolean success = downloadSingleFile(ftpClient, filePath,
                            newDirPath);
                    if (success)
                    {
                        System.out.println("DOWNLOADED the file: " + filePath);
                    } else
                    {
                        System.out.println("COULD NOT download the file: "
                                + filePath);
                    }
                }
            }
        }
    }
    
    /**
     * Upload a whole directory (including its nested sub directories and files)
     * to a FTP server.
     * 
     * @param ftpClient
     *            an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param remoteDirPath
     *            Path of the destination directory on the server.
     * @param _localParentDir
     *            Path of the local directory being uploaded.
     * @param remoteParentDir
     *            Path of the parent directory of the current directory on the
     *            server (used by recursive calls).
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static void uploadDirectory(FTPClient ftpClient,
            String remoteDirPath, String _localParentDir, String remoteParentDir)
            throws IOException
    {
        String localParentDir = _localParentDir;
        System.out.println("LISTING directory: " + localParentDir);
        
        File localDir = new File(localParentDir);
        File[] subFiles = localDir.listFiles();
        if (subFiles != null && subFiles.length > 0)
        {
            for (File item : subFiles)
            {
                String remoteFilePath = remoteDirPath + "/" + remoteParentDir
                        + "/" + item.getName();
                if (remoteParentDir.equals(""))
                {
                    remoteFilePath = remoteDirPath + "/" + item.getName();
                }
                
                if (item.isFile())
                {
                    // upload the file
                    String localFilePath = item.getAbsolutePath();
                    System.out.println("About to upload the file: "
                            + localFilePath);
                    boolean uploaded = uploadSingleFile(ftpClient,
                            localFilePath, remoteFilePath);
                    if (uploaded)
                    {
                        System.out.println("UPLOADED a file to: "
                                + remoteFilePath);
                    } else
                    {
                        System.out.println("COULD NOT upload the file: "
                                + localFilePath);
                    }
                } else
                {
                    // create directory on the server
                    boolean created = ftpClient.makeDirectory(remoteFilePath);
                    if (created)
                    {
                        System.out.println("CREATED the directory: "
                                + remoteFilePath);
                    } else
                    {
                        System.out.println("COULD NOT create the directory: "
                                + remoteFilePath);
                    }
                    
                    // upload the sub directory
                    String parent = remoteParentDir + "/" + item.getName();
                    if (remoteParentDir.equals(""))
                    {
                        parent = item.getName();
                    }
                    
                    localParentDir = item.getAbsolutePath();
                    uploadDirectory(ftpClient, remoteDirPath, localParentDir,
                            parent);
                }
            }
        }
    }
    
    /**
     * Upload a single file to the FTP server.
     * 
     * @param ftpClient
     *            an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param localFilePath
     *            Path of the file on local computer
     * @param remoteFilePath
     *            Path of the file on remote the server
     * @return true if the file was uploaded successfully, false otherwise
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static boolean uploadSingleFile(FTPClient ftpClient,
            String localFilePath, String remoteFilePath) throws IOException
    {
        File localFile = new File(localFilePath);
        
        @SuppressWarnings("resource")
        InputStream inputStream = new FileInputStream(localFile);
        try
        {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(remoteFilePath, inputStream);
        } finally
        {
            inputStream.close();
        }
    }
}
