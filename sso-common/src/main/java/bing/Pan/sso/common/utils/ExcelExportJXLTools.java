package bing.Pan.sso.common.utils;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan  15923508369@163.com .
 * @date :2016/12/19 17:58
 * @desc :excel文件导出工具类 依赖jxl.jar
 */
public class ExcelExportJXLTools {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //每个sheet页最大容量
    private static final Integer sheetCapacity = 60000;

    /**
     * excel 通用文件导出
     * @param fileName          导出文件名称
     * @param sheetName         sheet页名称（如果在导出时出现了多个sheet页 ，sheet名称自动追加成sheetName-xx）
     * @param excelTitle        excel文件title 各cell名称（加载excel文件下载需的通用静态属性 ExportConstantData 类中的静态属性）
     * @param titleStyleWidth   excel文件title 各cell宽度（通用静态属性）
     * @param exportList        需要导出的实体 list集合，类型为List<Object>,不能具体类型，会出现类型转换异常 （DeclaredFields）,
     *                           导出该集合中的所有字段，并按实体中属性顺序创建excel文件内容
     * @param response
     * @throws WriteException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void ExcelExportWithFormat(String fileName, String sheetName, List<String> excelTitle, List<Integer> titleStyleWidth, List<Object> exportList,
                                             HttpServletResponse response) throws IOException, WriteException, IllegalAccessException, InterruptedException, ServiceException {
        int exportListSize = exportList.size();
        if(StringUtils.isEmpty(exportList) || exportListSize ==0){
            throw new ServiceException(ResponseCode.SERVE_UNKNOWN_ERROR);
        }

        //如果导出内容大于6000条，那么创建多个sheet页
        //最终生成的sheet个数
        int sheetCount = 1;
        if(exportListSize > sheetCapacity){
            sheetCount = exportListSize % sheetCapacity == 0? exportListSize / sheetCapacity: exportListSize / sheetCapacity + 1;
        }
        logger.info("------------------------------------------------------");
        logger.info("导出的数据长度为："+ exportListSize);
        logger.info("导出的excel sheet页为："+ sheetCount);
        logger.info("------------------------------------------------------");
        OutputStream os = response.getOutputStream();
        response.reset();

        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
        response.setContentType("application/msexcel");
        WritableWorkbook workbook = Workbook.createWorkbook(os);

        for (int x =0; x < sheetCount; x ++){
            String currentSheetName = String.format("%s%s%s",sheetName,"_",(x+1));
            List<Object> currentExportList;
            if(x == sheetCount -1){
                currentExportList = exportList.subList(x * sheetCapacity, exportList.size());
            }else{
                currentExportList = exportList.subList(x * sheetCapacity, (x+1) * sheetCapacity);
            }
            logger.info("执行线程的数据长度："+ currentExportList.size());

            dealWriteExcelContent(workbook,currentSheetName,excelTitle,titleStyleWidth,currentExportList,x);

        }

        workbook.write();
        workbook.close();

    }

    private void dealWriteExcelContent(WritableWorkbook workbook, String sheetName, List<String> excelTitle, List<Integer> titleStyleWidth,
                                       List<Object> exportList, int sheetNo) throws WriteException, IllegalAccessException {


        WritableSheet sheet = workbook.createSheet(sheetName, sheetNo);
        //设置纵横打印（默认为纵打）
        jxl.SheetSettings sheetSet = sheet.getSettings();
        sheetSet.setProtected(false);

        //标题样式
        WritableFont sheetFont = new WritableFont(WritableFont.createFont("华文新魏"), 15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        WritableCellFormat sheetStyle = new WritableCellFormat(sheetFont);
        sheetStyle.setBackground(Colour.BLUE_GREY);
        sheetStyle.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
        globalStyle(sheetStyle);

        // excel表头样式： 定义格式 字体 下划线 斜体 粗体 颜色
        WritableFont titleFont = new WritableFont(WritableFont.createFont("华文新魏"), 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        WritableCellFormat titleStyle = new WritableCellFormat(titleFont);
        titleStyle.setBackground(Colour.GREEN);
        titleStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
        globalStyle(titleStyle);

        // excel正文样式
        WritableFont contentFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        WritableCellFormat contentStyle = new WritableCellFormat(contentFont);
        contentStyle.setBackground(Colour.DARK_YELLOW);
        contentStyle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

        globalStyle(contentStyle);

        //设置sheet页文件标题 jxl newLabel是以 ： cell index --> row index
        //sheet.setRowView(3500,true); //设置行高
        sheet.addCell(new Label(0,0,String.format("%s%s",sheetName,"_sheet_content"),sheetStyle));
        sheet.mergeCells(0, 0,excelTitle.size() - 1, 0);


        //循环创建表头
        for (int i = 0; i < excelTitle.size() ; i++) {
            sheet.setColumnView(i, getTitleCellStyle(titleStyleWidth.get(i))); //设置col显示样式
            sheet.addCell(new Label(i, 1, excelTitle.get(i), titleStyle));
        }

        //excel文件正文内容
        Field[] fields = null;
        for (int x = 0 ; x < exportList.size();x ++) {
            Object obj = exportList.get(x);
            fields = obj.getClass().getDeclaredFields();
            int callIndex = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                Object va = field.get(obj);
                if (va == null) {
                    va = "--";
                }
                sheet.addCell(new Label(callIndex, x+2, va.toString(), contentStyle));
                callIndex++;
            }
        }
    }

    /**
     * 导出文件通用样式
     * @param sheetStyle
     * @throws WriteException
     */
    private void globalStyle(WritableCellFormat sheetStyle) throws WriteException {
        sheetStyle.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
        sheetStyle.setAlignment(Alignment.CENTRE); // 文字水平对齐
        sheetStyle.setWrap(false); // 文字是否换行
    }

    /**
     * 获取call样式
     * @param size
     * @return
     */
    public static CellView getTitleCellStyle(int size){
        CellView titleCallViewStyle = new CellView();
        titleCallViewStyle.setSize(size);
        return titleCallViewStyle;
    }
}
