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
     * 通话记录导出设置excel表头
     * @return
     */
    public static List<String> sysUserRecordTitle(){
        //设置文件导出必要参数，按设定顺序创建下载文件
        List<String> excelTitle = Lists.newArrayList();
        excelTitle.add("接听状态");
        excelTitle.add("员工姓名");
        excelTitle.add("员工账号");
        excelTitle.add("所属组");
        excelTitle.add("所属角色");
        excelTitle.add("员工外呼时间");
        excelTitle.add("客户接听时间");
        excelTitle.add("客户挂断时间");
        excelTitle.add("通话时长");
        excelTitle.add("客户类型");
        excelTitle.add("用户Id");
        excelTitle.add("客户邀请码");
        return excelTitle;
    }

    /**
     * 通话记录导出 设置excel cell宽度
     * @return
     */
    public static List<Integer> sysUserCallStyle(){

        List<Integer> excelTitleCallWidth = Lists.newArrayList();
        excelTitleCallWidth.add(3800);
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
        return excelTitleCallWidth;

    }


}
