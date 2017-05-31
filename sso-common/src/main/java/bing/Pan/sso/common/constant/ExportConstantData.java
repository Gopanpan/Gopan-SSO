package bing.Pan.sso.common.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan  15923508369@163.com .
 * @date :2016/12/20 13:00
 * @desc :excel 文件导出常量
 */
public class ExportConstantData {

    /**
     * 系统用户导出设置excel表头
     * @return
     */
    public static List<String> sysUserHead(){
        //设置文件导出必要参数，按设定顺序创建下载文件
        List<String> excelTitle = Lists.newArrayList();
        excelTitle.add("员工姓名");
        excelTitle.add("员工真实名称");
        excelTitle.add("密码");
        excelTitle.add("性别");
        excelTitle.add("生日");
        excelTitle.add("电话号码");
        excelTitle.add("电子邮件");
        excelTitle.add("上次登录时间");
        excelTitle.add("上次登录IP地址");
        excelTitle.add("是否可用");
        excelTitle.add("创建人");
        excelTitle.add("创建时间");
        excelTitle.add("修改人");
        excelTitle.add("修改时间");
        return excelTitle;
    }

    /**
     * 通话记录导出 设置excel cell宽度
     * @return
     */
    public static List<Integer> sysUserCallWidth(){
        List<Integer> excelTitleCallWidth = Lists.newArrayList();
        excelTitleCallWidth.add(3500);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(5400);
        excelTitleCallWidth.add(5400);
        excelTitleCallWidth.add(5400);
        excelTitleCallWidth.add(3000);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        excelTitleCallWidth.add(3800);
        return excelTitleCallWidth;

    }


}
