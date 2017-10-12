package Controllers;

import Client.VulaClient;
import Models.AuthStatus;
import Models.UploadAssignment;
import Models.UploadFile;
import Models.UploadResponse;
import Utilities.FedoraSession;
import Utilities.VulaSession;
import com.google.gson.Gson;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import responses.Resource;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.jsoup.Jsoup;
import responses.Assignment;
import responses.Attachment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author learnproject
 */
@RestController
public class AssignmentUploadController {

    private final AtomicLong counter = new AtomicLong();
    protected static SecureRandom random = new SecureRandom();
    private final String USERNAME_KEY = "_username";
    private final String PASSWORD_KEY = "_password";
    private final String BASE_URL = "https://vula.uct.ac.za/direct";
    public FedoraSession fsession;
    private UploadAssignment assignment;

    public String folder_name;
    List<String> filesListInDir = new ArrayList<String>();

    @RequestMapping("/assignment")
    public UploadResponse assignmentUpload(@RequestHeader("data") String data) throws Exception {

        Gson gson = new Gson();
        this.assignment = gson.fromJson(data, UploadAssignment.class);
        VulaSession vsession = new VulaSession(this.assignment._username, this.assignment._password);
        this.fsession = new FedoraSession("fedoraAdmin", "fedoraAdmin", vsession.request.getProfile(), vsession.client);

        folder_name = "Assignment/" + this.assignment._username + "/" + this.assignment.assignment.getTitle().replace(" ", "-");
        System.out.println(new File(folder_name).mkdirs());
        File file = new File(folder_name);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());

        System.out.println("+------------------------------------------------------------------------");
        System.out.println("Now sending upload response data....");
        System.out.println("+------------------------------------------------------------------------");
        
        String tmp = this.assignment.assignment.getTitle().replace(" ", "-");
        String assignment_title = Character.isDigit(tmp.charAt(0)) ? "A" + tmp.replace(" ", "-") : tmp.replace(" ", "-"); 
        return DownloadFiles(
                assignment_title.replace("-", " ").replace(":", " ").replace("[", " ").replace("]", " ").replace("(", " ").replace(")", " "),
                this.assignment,
                vsession.client,
                folder_name
        );
    }

    public UploadResponse ingest(String _pid, String _resourcename, String _mimeType, String _logmsg, File file, String folder_name) {
        try {
            try {
                String objectResponse = this.fsession.request.createObject(_pid, _pid.split("-")[1]);
            } catch (Exception e) {
            }

            //String _pid = objectResponse;
            String datastreamResponse = this.fsession.request.createDataStream(_resourcename, _mimeType, _pid, _resourcename, _logmsg, file);
            String datastream = datastreamResponse;
            if (datastream.trim().equals("201")) {
                FileUtils.deleteDirectory(new File(folder_name));
                //FileUtils.deleteQuietly(file);

                return new UploadResponse(201, "Object " + _resourcename + " uploaded successfully.");
            } else {
                return new UploadResponse(Integer.parseInt(datastream), "Error uploading file : "+_resourcename);
            }
        } catch (Exception e) {
            return new UploadResponse(500, "Error uploading file.");
        }

    }

    public UploadResponse DownloadFiles(String title, UploadAssignment assignment, VulaClient client, String folder_name) throws Exception {
        Attachment[] attachments = this.assignment.assignment.getAttachments();
        for (int i = 0; i < attachments.length; i++) {
            URL url = new URL(attachments[i].getUrl());
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("Cookie", client.getSession());
            InputStream in = conn.getInputStream();
            FileUtils.copyToFile(in, new File(folder_name + "/" + attachments[i].getName()));
        }

        createIntructionsFile(assignment.assignment, new File(folder_name + "/" + "instructions.pdf"));

        File dir = new File(folder_name);
        String zipDirName = folder_name + ".zip";

        zipDirectory(dir, zipDirName);
        System.out.println("Printing status for assignment upload..... : " + this.assignment.pid + " T "+title+" F "+folder_name);
        return ingest(this.assignment.pid, title, "application/zip", "Uploaded", new File(zipDirName), folder_name);
    }

    private static void createIntructionsFile(Assignment assignment, File file) {

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(file));

            //open
            document.open();

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(18);
            Paragraph title = new Paragraph(assignment.getTitle(), f);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph p = new Paragraph();
            p.add(Jsoup.parse(assignment.getInstructions()).text());

            document.add(p);
            //close
            document.close();

            System.out.println("Done");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void zipDirectory(File dir, String zipDirName) {
        try {
            populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String filePath : filesListInDir) {
                System.out.println("Zipping " + filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method populates all the files in a directory to a List
     *
     * @param dir
     * @throws IOException
     */
    private void populateFilesList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                filesListInDir.add(file.getAbsolutePath());
            } else {
                populateFilesList(file);
            }
        }
    }

    /**
     * This method compresses the single file to zip format
     *
     * @param file
     * @param zipFileName
     */
    private static void zipSingleFile(File file, String zipFileName) {
        try {
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            //add a new Zip Entry to the ZipOutputStream
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            //read the file and write to ZipOutputStream
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            //Close the zip entry to write to zip file
            zos.closeEntry();
            //Close resources
            zos.close();
            fis.close();
            fos.close();
            System.out.println(file.getCanonicalPath() + " is zipped to " + zipFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
