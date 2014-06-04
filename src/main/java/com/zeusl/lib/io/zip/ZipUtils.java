package com.zeusl.lib.io.zip;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

/**
 * @author Zeus
 *         Some zip related utilities
 */
public class ZipUtils
{

    /**
     * @param directory
     *            The directory to tar
     * @param name
     *            The name of the file (example.zip)
     * @param location
     *            The tarred file's location (/home/exampleUser/Desktop/)
     * @throws IOException
     *             Oh no! Something went wrong!
     */
    public static void tar(String directory, String location, String name)
            throws IOException
    {
        Archiver archive = ArchiverFactory.createArchiver("tar", "gz");
        archive.create(name, new File(location), new File(directory));
    }

    /**
     * @param directory
     *            The directory to untar to
     * @param file
     *            The file to untar
     * @throws IOException
     *             Oh no! Something went wrong!
     */
    public static void untar(String directory, String file) throws IOException
    {
        Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
        archiver.extract(new File(file), new File(directory));
    }

    /**
     * @param directory
     *            The directory to extract to
     * @param file
     *            The file to unzip
     * @throws Exception
     *             There was an error!
     */
    public static void unzip(String directory, String file) throws Exception
    {
        ZipFile zipFile = new ZipFile(file);
        zipFile.extractAll(directory);
    }

    /**
     * @param directory
     *            The directory to zip
     * @param file
     *            The zipped file
     * @throws Exception
     *             There was an error!
     */
    public static void zip(String directory, String file) throws Exception
    {
        ZipFile zfile = new ZipFile(file);
        zfile.addFolder(file, new ZipParameters());
    }
}
