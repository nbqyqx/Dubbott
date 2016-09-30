package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("defaultvalue")
public interface DefaultValueService {

	/**
	 * 当前dubbott框架只支持目前这种写法在接口层面进行数据初始化，下面这种写法不能初始化参数数据
	 * 	public void setVersion(@HeaderParam("requestVersion") @DefaultValue("1.0") String version);
	 * 
	 * @param version
	 */
	@HeaderParam("requestVersion")
	@DefaultValue("1.0")
    public void setVersion(String version);
    
	@DefaultValue("100")
	@QueryParam("limit")
    public void setLimit(String limit);

    public static class Page {

        private final String offset;

        public Page(String offset, int dummy) {
            this.offset = offset;
            System.out.println("Executed constructor");
        }

        public String getOffset() {
            return offset;
        }

        public int getPage() {
            return Integer.valueOf(offset) * 1; // Integer.valueOf(limit);
        }

        public static Page valueOf(String offset) {
            return new Page(offset, 123);
        }
    }

    @GET
    public String getRow(@QueryParam("offset") @DefaultValue("0") Page page);

    @DefaultValue("normal")
    @QueryParam("sort")
    public void setSort(String sort);

    public String getSort();
    
}
