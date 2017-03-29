package bing.Pan.sso.manage.test;

import java.io.*;
import java.nio.charset.Charset;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
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

    public static final String WORD_DOC = "D:/CFCA电子签章产品需求说明书.doc";
    public static final String PDF_DOC = "D:/测试合同.pdf";
    public static final String DEST_MARK = "D:/hello_mark.pdf";

    private static String USER_PASS = "bing.Pan";
    private static String OWNER_PASS = "bing.Pan";



    public static void main(String[] args) throws IOException, DocumentException {
        htmlConvertPDF();
        waterMark();
        office2PDF(WORD_DOC,PDF_DOC);
    }


    /**
     * 基于openOffice word转PDF
     * @param input  待转换的word文档路径
     * @param output 生成PDF路径带文件名
     * @return
     * @throws IOException
     */
    public static int office2PDF(String input, String output) throws IOException {
        File file = new File(output);
        file.createNewFile();

        File inputFile = new File(input);
        File outputFile = new File(output);

        String OpenOffice_HOME = "c:/Program Files (x86)/OpenOffice 4/program/soffice.exe";// 这里是OpenOffice的安装目录,

        Process pro = null;
        try {
            // 启动OpenOffice的服务
            String command = OpenOffice_HOME+ " -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
            pro = Runtime.getRuntime().exec(command);
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();

            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);

            connection.disconnect();
            // 封闭OpenOffice服务的进程
            pro.destroy();

            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pro.destroy();
        }
        return 1;
    }

    /**
     * html文件转换成PDF文件
     * @throws IOException
     * @throws DocumentException
     */
    public static void htmlConvertPDF() throws IOException, DocumentException {
        File file = new File(PDF_DOC);
        file.getParentFile().mkdirs();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        //设置打开密码   USER_PASS，OWNER_PASS 参数为空时这表示打开不需要密码
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream(WORD_DOC), Charset.forName("UTF-8"));
        document.close();
    }


    /**
     * PDF文件加水印
     * @throws IOException
     * @throws DocumentException
     */
    public static void  waterMark() throws IOException, DocumentException {

        // 待加水印的文件
        //PdfReader reader = new PdfReader(PDF_DOC,OWNER_PASS.getBytes());
        PdfReader reader = new PdfReader(PDF_DOC);
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
