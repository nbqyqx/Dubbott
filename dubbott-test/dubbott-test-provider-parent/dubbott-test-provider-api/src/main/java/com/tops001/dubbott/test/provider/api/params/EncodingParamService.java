package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/encodingparam")
public interface EncodingParamService {

	@Encoded @MatrixParam("appversion")
    public void setAppVersion(String appVersion);

    @GET
    @Path("country/{location}")
    public String getShopInCountry(@Encoded @PathParam("location") String location);

    @GET
    @Path("method/country/{location}")
    @Encoded
    public String getShopInCountryMethod(@PathParam("location") String location);

    @GET
    @Encoded
    @Path("method/city")
    public String getShopInCityMethod(@QueryParam("location") String location);

    @GET
    @Path("city")
    public String getShopInCity(@Encoded @QueryParam("location") String location);

    @GET
    @Encoded
    @Path("method/street")
    public String getShopOnStreetMethod(@MatrixParam("location") String location);

    @GET
    @Path("street")
    public String getShopOnStreet(@Encoded @MatrixParam("location") String location);

    @POST
    @Path("region")
    public String getShopInRegion(@Encoded @FormParam("location") String location);

    @POST
    @Encoded
    @Path("method/region")
    public String getShopInRegionMethod(@FormParam("location") String location);
    
}
