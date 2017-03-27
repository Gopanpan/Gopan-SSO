package bing.Pan.sso.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/27 11:21
 * @desc :
 */
public class PDFTest {


    public static final String HTML = "D:/hello.html";
    public static final String DEST = "D:/hello.pdf";


    private static String USER_PASS = "bing.Pan";

    private static String OWNER_PASS = "bing.Pan";



    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PDFTest().createPdf(DEST);

    }


    public void createPdf(String file) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);


        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream(HTML), Charset.forName("UTF-8"));
        document.close();
    }




}
