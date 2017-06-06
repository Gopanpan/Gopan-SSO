package bing.Pan.sso.common.utils;

import bing.Pan.sso.common.enums.DateEnums;
import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/31 17:48
 * @desc : POI excel文件导出，支持超大数据量
 * @sample
 *      初始化excel文件 --> 添加数据 --> 渲染到客户端 -->清理临时文件
 *      new ExcelExportTools("系统用户导出数据","导出数据",ExportConstantData.sysUserHead(),ExportConstantData.sysUserColumnWidth(),list.size())
 *      .setDataList(list,filterField)
 *      .write(response,"系统用户导出数据.xlsx")
 *      .dispose();
 */
public class ExcelExportTools {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private SXSSFWorkbook workbook;
    private List<Sheet> sheetList = Lists.newArrayList();
    private Map<String, CellStyle> styleMap;

    private int sheetCount = 1;                                                         //sheet个数
    private int rowNum;                                                                 //当前行号
    private static final int sheetCapacity  = 500000;                                   //excel单个sheet最大行数
    private static final int memoryCacheRow = 50000;                                    //内存中缓存工作薄行数数

    private static final String formatData[] = new String[]{"java.time.LocalDateTime",
            "java.lang.Boolean"};                                                       //时间格式化字符串形式


    /**
     * 初始化excel表格
     * @param title             excel 文件标题，多个sheet页则每个sheet页的标题相同
     * @param sheetName         sheet名称前缀,系统会自动追加当前sheet页的页号 sheetName_xxx
     * @param headerList        表头,最终的表头集合,按照导出数据实体类型字段的创建顺序添加,
     *                          如遇过滤字段舍弃，后面的字段补上
     * @param columnWidth       每列宽度
     * @param exportDataSize    导出数据集合长度
     * @throws ServiceException
     */
    public ExcelExportTools(String title, String sheetName, List<String> headerList, List<Integer> columnWidth,int exportDataSize) throws ServiceException {
        if(StringUtils.isEmpty(title) || StringUtils.isEmpty(sheetName) || StringUtils.isEmpty(headerList) || exportDataSize == 0)
            throw new ServiceException(ResponseCode.SERVE_LOGIC_PARAM_MISS);

        workbook = new SXSSFWorkbook(memoryCacheRow);

        if(exportDataSize > sheetCapacity)
            sheetCount = exportDataSize % sheetCapacity == 0? exportDataSize / sheetCapacity: exportDataSize / sheetCapacity + 1;

        styleMap = createStyles(workbook);

        //循环创建sheet页
        for (int x =0; x < sheetCount; x ++){

            Sheet sheet = workbook.createSheet(String.format("%s%s%s", sheetName, "_", x));

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

                sheet.setColumnWidth(i, columnWidth.get(i));
            }
            rowNum = 0;
            sheetList.add(sheet);
        }
        logger.info("导出excel文件-->初始化excel文件完成");
        logger.info(MessageFormat.format("导出的数据长度为：{0}", exportDataSize));
        logger.info(MessageFormat.format("导出的excel sheet总页数为：{0},单个sheet页最大行数{1}",sheetCount,sheetCapacity));
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
     * @param booleanFormat boolean类型格式化:数组类型,下标为0时代表 为true是格式化的文字，下标为1时代表 为false是格式化的文字，
     * @param intFormat     需要将int类型转换成对应枚举值的字段
     * @param <E>
     * @return
     * @throws IllegalAccessException
     */
    public <E> ExcelExportTools setDataList(List<E> list, String[] filterField,String[] booleanFormat,
                                            Map<String,Map<Integer,String>> intFormat)throws IllegalAccessException {

        for (int x =0; x < sheetCount; x ++){
            List<E> sheetExportList;
            if(x == sheetCount -1){
                sheetExportList = list.subList(x * sheetCapacity, list.size());
            }else{
                sheetExportList = list.subList(x * sheetCapacity, (x+1) * sheetCapacity);
            }
            dealSheetData(sheetExportList,sheetList.get(x),filterField,booleanFormat,intFormat);

        }

        return this;

    }

    private <E> void dealSheetData(List<E> sheetExportList, Sheet sheet, String[] filterField, String[] booleanFormat,
                                   Map<String,Map<Integer,String>> intFormat) throws IllegalAccessException {
        rowNum = 2;         //循环创建title，表头时rowNum复位为0了,数据真实的起始行应为2
        for(Object obj:sheetExportList){
            int cellNum = 0;
            Row row =  sheet.createRow(rowNum++);
            for(Field field:obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                String fieldName = field.getName();
                if(exclusive(fieldName,filterField)){

                    //处理枚举值
                    if(dealEnums(intFormat,fieldName,field.get(obj),row,cellNum)){
                        cellNum++;
                        continue;
                    }

                    if(field.getType().getName().equalsIgnoreCase(formatData[0])){
                        Object va = field.get(obj);
                        if(StringUtils.isEmpty(va)) va = "--";
                        else  va = DateUtils.date2String((LocalDateTime) va, DateEnums.HYPHEN_YYYYMMddHHmmss.getPatterns());

                        addCall(row,cellNum++,va);

                    }else if(field.getType().getName().equalsIgnoreCase(formatData[1])){
                        Object va=field.get(obj);
                        if(StringUtils.isEmpty(va)) va="--";
                        else{
                            if(!StringUtils.isEmpty(booleanFormat)){
                                if((boolean) va) va = booleanFormat[0];
                                else va = booleanFormat[1];
                            }
                        }
                        addCall(row,cellNum++,va);

                    }else{
                        Object va=field.get(obj);
                        if(StringUtils.isEmpty(va))
                            va="--";
                        addCall(row,cellNum++, va);
                    }

                }
            }
        }

    }

    private boolean dealEnums(Map<String,Map<Integer,String>> intFormat, String fieldName, Object val, Row row, int cellNum) {
        if(StringUtils.isEmpty(intFormat) || intFormat.size() == 0) return true;

        boolean whetherContinue = false;
        for(String key: intFormat.keySet()){
            if(fieldName.equals(key)){
                Map<Integer, String> formatMap = intFormat.get(key);
                for(Integer enumKey: formatMap.keySet()){
                    if(enumKey.equals(val))
                        addCall(row,cellNum,formatMap.get(val));
                }
                whetherContinue = true;
            }
        }

        return whetherContinue;

    }

    private boolean exclusive(String fieldName, String[] filterField) {
        if(StringUtils.isEmpty(filterField)) return  true;

        for (String exclusive:filterField) {
            if(fieldName.equals(exclusive))return false;
        }
        return true;
    }

    private void addCall(Row row, int i, Object va) {
        Cell cell = row.createCell(i);
        cell.setCellValue(va.toString());
        cell.setCellStyle(styleMap.get("data"));
    }

    /**
     * 输出到客户端
     * @param response     相应流
     * @param fileName     保存excel文件名
     * @return
     * @throws IOException
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
        logger.info("导出excel文件-->excel文件导出完成");
        return this;
    }


}
