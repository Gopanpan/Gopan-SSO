package bing.Pan.sso.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/31 17:48
 * @desc :
 */
public class ExcelExportPOITools {

    /**
     * 工作薄对象
     */
    private SXSSFWorkbook workbook;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styleMap;

    /**
     * 当前行号
     */
    private int rowNum;



    public ExcelExportPOITools(String title, List<String> headerList){

        this.workbook = new SXSSFWorkbook(500);
        this.sheet = workbook.createSheet("Export");
        this.styleMap = createStyles(workbook);

        //title
        if (StringUtils.isNotBlank(title)){
            Row titleRow = sheet.createRow(rowNum++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styleMap.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
        }

        //header
        Row headerRow = sheet.createRow(rowNum++);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styleMap.get("header"));
            String[] ss = StringUtils.split(headerList.get(i), "**", 2);
            if (ss.length==2){
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
                        new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            }else{
                cell.setCellValue(headerList.get(i));
            }
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = sheet.getColumnWidth(i)*2;
            sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
        }


    }


    /**
     * 创建表格样式
     * @param workbook 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook workbook) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style = workbook.createCellStyle();
        // 设置标题样式
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setFontName("华文新魏");
        font.setFontHeightInPoints((short) 18);//设置字体大小
        style.setFont(font);
        styles.put("title", style);

        style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 11);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);


        style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.ROSE.index);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setRightBorderColor(HSSFColor.DARK_YELLOW.index);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.DARK_YELLOW.index);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.DARK_YELLOW.index);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.DARK_YELLOW.index);
        Font dataFont = workbook.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 11);
        style.setFont(dataFont);
        styles.put("data", style);

        return styles;
    }


    /**
     * 添加数据（通过annotation.ExportField添加数据）
     * @return list 数据列表
     */
    public <E> ExcelExportPOITools setDataList(List<E> list) throws IllegalAccessException {
        Field[] fields=null;
        for(Object obj:list){
            int colunm = 0;
            Row row = this.addRow();

            fields=obj.getClass().getDeclaredFields();

            for(Field v:fields){
                v.setAccessible(true);
                Object va=v.get(obj);
                if(va==null){
                    va="--";
                }
                this.addCall(row,colunm++, va);

            }
        }
        return this;
    }







    /**
     * 输出数据流
     * @param os 输出数据流
     */
    public ExcelExportPOITools write(OutputStream os) throws IOException {
        workbook.write(os);
        return this;
    }

    /**
     * 输出到客户端
     * @param fileName 输出文件名
     */
    public ExcelExportPOITools write(HttpServletResponse response, String fileName) throws IOException{
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        write(response.getOutputStream());
        return this;
    }



    /**
     * 清理临时文件
     */
    public ExcelExportPOITools dispose(){
        workbook.dispose();
        return this;
    }


    private void addCall(Row row, int i, Object va) {
        Cell cell = row.createCell(i);
        cell.setCellValue(va.toString());
        cell.setCellStyle(styleMap.get("data"));
    }


    /**
     * 添加一行
     * @return 行对象
     */
    public Row addRow(){
        return sheet.createRow(rowNum++);
    }




}
