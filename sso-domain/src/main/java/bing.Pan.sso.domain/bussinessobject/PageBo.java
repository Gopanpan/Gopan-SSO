package bing.Pan.sso.domain.bussinessobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/4/18 16:46
 * @desc : 页码Bo
 */
@ApiModel
public class PageBo implements Serializable {

    @ApiModelProperty("当前页码")
    @NotNull(message="当前页码不能为空!")
    private int pageIndex;

    @ApiModelProperty("每页数据量")
    @NotNull(message = "每页数量不能为空!")
    private int pageSize;


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
