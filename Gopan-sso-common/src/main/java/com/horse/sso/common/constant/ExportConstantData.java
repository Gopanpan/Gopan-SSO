package com.horse.sso.common.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :Gopan  15923508369@163.com .
 * @date :2016/12/20 13:00
 * @desc :excel 文件导出常量
 */
public class ExportConstantData {

    /**
     * 系统用户导出设置excel表头
     * @return
     */
    public static List<String> sysUserheaderList(){
        //设置文件导出必要参数，按设定顺序创建下载文件
        List<String> headerList = Lists.newArrayList();
        headerList.add("员工姓名");
        headerList.add("员工真实名称");
        headerList.add("性别");
        headerList.add("生日");
        headerList.add("电话号码");
        headerList.add("电子邮件");
        headerList.add("上次登录时间");
        headerList.add("上次登录IP地址");
        headerList.add("是否启用");
        headerList.add("创建人");
        headerList.add("创建时间");
        headerList.add("修改人");
        headerList.add("修改时间");
        return headerList;
    }

    /**
     * 系统用户导出 设置excel cell宽度
     * @return
     */
    public static List<Integer> sysUserColumnWidth(){
        List<Integer> columnWidth = Lists.newArrayList();
        columnWidth.add(3500);
        columnWidth.add(3800);
        columnWidth.add(3800);
        columnWidth.add(5400);
        columnWidth.add(5400);
        columnWidth.add(5400);
        columnWidth.add(3000);
        columnWidth.add(3800);
        columnWidth.add(3800);
        columnWidth.add(3800);
        columnWidth.add(3800);
        columnWidth.add(3800);
        columnWidth.add(3800);
        return columnWidth;

    }


}
