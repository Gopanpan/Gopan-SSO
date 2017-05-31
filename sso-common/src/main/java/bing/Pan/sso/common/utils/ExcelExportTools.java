package bing.Pan.sso.common.utils;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class ExcelExportTools {

    private SXSSFWorkbook workbook;

    private Sheet sheet;

    private Map<String, CellStyle> styleMap;

    //当前行号
    private int rowNum;

    /**
     * 初始化excel表格
     * @param title         excel title
     * @param sheetName     sheet名称
     * @param headerList    表头
     * @param callWidth     单元格宽度
     * @throws ServiceException
     */
    public ExcelExportTools(String title, String sheetName, List<String> headerList, List<Integer> callWidth) throws ServiceException {
        if(StringUtils.isEmpty(title) || StringUtils.isEmpty(sheetName) || StringUtils.isEmpty(headerList))
            throw new ServiceException(ResponseCode.SERVE_LOGIC_PARAM_MISS);

        this.workbook = new SXSSFWorkbook(500);
        this.sheet = workbook.createSheet(sheetName);
        this.styleMap = createStyles(workbook);

        //title
        Row titleRow = sheet.createRow(rowNum++);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(styleMap.get("title"));
        titleCell.setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));

        //header
        Row headerRow = sheet.createRow(rowNum++);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styleMap.get("header"));
            cell.setCellValue(headerList.get(i));

            sheet.setColumnWidth(i, callWidth.get(i));
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
     * 添加数据
     * @param list          导出的list数据列
     * @param filterField   需要过滤的列
     * @param <E>
     * @return
     * @throws IllegalAccessException
     */
    public <E> ExcelExportTools setDataList(List<E> list, String[] filterField) throws IllegalAccessException {

        if(StringUtils.isEmpty(filterField) )
            dealNoFilerField(list);
        else
            dealFilerField(list,filterField);
        return this;
    }

    /**
     * 处理有需要过滤的列表
     * @param list
     * @param filterField
     * @param <E>
     * @throws IllegalAccessException
     */
    private <E> void dealFilerField(List<E> list, String[] filterField) throws IllegalAccessException {
        Field[] fields=null;
        for(Object obj:list){
            int cellNum = 0;
            Row row =  sheet.createRow(rowNum++);
            fields=obj.getClass().getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                String name = field.getName();
                if(exclusive(name,filterField)){
                    Object va=field.get(obj);
                    if(StringUtils.isEmpty(va))
                        va="--";
                    addCall(row,cellNum++, va);
                }
            }
        }
    }

    private boolean exclusive(String name, String[] filterField) {
        for (String exclusive:filterField) {
            if(name.equals(exclusive))return false;
        }
        return true;
    }

    /**
     * 处理没有过滤字段的列表
     * @param list
     * @param <E>
     * @throws IllegalAccessException
     */
    private <E> void dealNoFilerField(List<E> list) throws IllegalAccessException {
        Field[] fields;
        for(Object obj:list){
            int cellNum = 0;
            Row row =  sheet.createRow(rowNum++);
            fields=obj.getClass().getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                Object va=field.get(obj);
                if(StringUtils.isEmpty(va))
                    va="--";
                addCall(row,cellNum++, va);

            }
        }

    }

    /**
     * 输出到客户端
     * @param fileName 输出文件名
     */
    public ExcelExportTools write(HttpServletResponse response, String fileName) throws IOException{
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
        workbook.write(response.getOutputStream());
        return this;
    }



    /**
     * 清理临时文件
     */
    public ExcelExportTools dispose(){
        workbook.dispose();
        return this;
    }


    private void addCall(Row row, int i, Object va) {
        Cell cell = row.createCell(i);
        cell.setCellValue(va.toString());
        cell.setCellStyle(styleMap.get("data"));
    }



}
