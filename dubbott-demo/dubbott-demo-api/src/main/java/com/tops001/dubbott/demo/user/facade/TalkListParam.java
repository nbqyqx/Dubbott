package com.tops001.dubbott.demo.user.facade;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import io.swagger.annotations.ApiParam;

public class TalkListParam  {

    @ApiParam(value = "搜索关键字")
    @QueryParam("keyword")
    private String keyword;
    
    @ApiParam(value = "圈圈id")
    @QueryParam("groupId")
    private Long groupId;

//    @Min(1)
//   // @DefaultValue("1")
//    @QueryParam(value = "pageIndex")
//    @ApiParam(value = "页码", required = false)
//    private Integer currentPage;

//    @Min(1)
//   // @DefaultValue("10")
//    @QueryParam(value = "pageSize")
//    @ApiParam(value = "分页参数：当前分页大小", required = false)
//    private Integer pageSize;

    @Min(1)
    @DefaultValue("1")
    @FormParam(value = "pageIndex")
    @ApiParam(value = "页码-ios", required = false, defaultValue = "1")
    private Integer currentPageIOS=1;

    @Min(1)
    @DefaultValue("10")
    @FormParam(value = "pageSize")
    @ApiParam(value = "分页参数：当前分页大小-ios", required = false, defaultValue = "10")
    private Integer pageSizeIOS=10;

    @ApiParam(value = "搜索关键字")
    @FormParam("keyword")
    private String keywordIOS;

    @ApiParam(value = "圈圈id")
    @FormParam("groupId")
    private Long groupIdIOS;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

//    public Integer getCurrentPage() {
//        return currentPage;
//    }
//
//    public void setCurrentPage(Integer currentPage) {
//        this.currentPage = currentPage;
//    }
    //
    // public Integer getPageSize() {
    // return pageSize;
    // }
    //
    // public void setPageSize(Integer pageSize) {
    // this.pageSize = pageSize;
    // }

    public Integer getCurrentPageIOS() {
        return currentPageIOS;
    }

    public void setCurrentPageIOS(Integer currentPageIOS) {
        this.currentPageIOS = currentPageIOS;
    }

    public Integer getPageSizeIOS() {
        return pageSizeIOS;
    }

    public void setPageSizeIOS(Integer pageSizeIOS) {
        this.pageSizeIOS = pageSizeIOS;
    }

    public String getKeywordIOS() {
        return keywordIOS;
    }

    public void setKeywordIOS(String keywordIOS) {
        this.keywordIOS = keywordIOS;
    }

    public Long getGroupIdIOS() {
        return groupIdIOS;
    }

    public void setGroupIdIOS(Long groupIdIOS) {
        this.groupIdIOS = groupIdIOS;
    }
}
