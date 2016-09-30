package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/decodedparams")
public interface AutoDecodeParamService {

	@MatrixParam("appversion")
    public void setAppVersion(String appVersion);

    @GET
    @Path("country/{location}")
    public String getShopInCountryDecoded(@PathParam("location") String location);

    @GET
    @Path("city")
    public String getShopInCityDecoded(@QueryParam("location") String location);

    @GET
    @Path("street")
    public String getShopOnStreetDecoded(@MatrixParam("location") String location);

    @POST
    @Path("region")
    public String getShopInRegionDecoded(@FormParam("location") String location);
    
}
