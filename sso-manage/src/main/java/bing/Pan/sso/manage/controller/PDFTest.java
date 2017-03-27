package bing.Pan.sso.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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
    public static final String DEST_MARK = "D:/hello_mark.pdf";


    private static String USER_PASS = "bing.Pan";

    private static String OWNER_PASS = "bing.Pan";



    public static void main(String[] args) throws IOException, DocumentException {

       createPdf();

       waterMark();

    }


    /**
     * 生成PDF文件
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf() throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);


        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream(HTML), Charset.forName("UTF-8"));
        document.close();
    }


    /**
     * PDF文件加水印
     * @throws IOException
     * @throws DocumentException
     */
    public static void  waterMark() throws IOException, DocumentException {

        // 待加水印的文件
        PdfReader reader = new PdfReader(DEST,OWNER_PASS.getBytes());
        // 加完水印的文件

        File file = new File(DEST_MARK);
        file.getParentFile().mkdirs();

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(DEST_MARK));

        int total = reader.getNumberOfPages() + 1;

        PdfContentByte content;
        // 设置字体
        //BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.EMBEDDED);
        BaseFont base = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // 水印文字
        String waterText = "水印文字！";
        int j = waterText.length(); // 文字长度
        char c = 0;
        int high = 0;// 高度
        // 循环对每页插入水印
        for (int i = 1; i < total; i++) {
            // 水印的起始
            high = 500;
            content = stamper.getUnderContent(i);
            // 开始
            content.beginText();
            // 设置颜色
            content.setColorFill(BaseColor.RED);
            // 设置字体及字号
            content.setFontAndSize(base, 18);
            // 设置起始位置
            content.setTextMatrix(400, 780);
            // 开始写入水印
            for (int k = 0; k < j; k++) {
                content.setTextRise(14);
                c = waterText.charAt(k);
                // 将char转成字符串
                content.showText(c + "");
                high -= 5;
            }
            content.endText();

        }
        stamper.close();
    }



}
