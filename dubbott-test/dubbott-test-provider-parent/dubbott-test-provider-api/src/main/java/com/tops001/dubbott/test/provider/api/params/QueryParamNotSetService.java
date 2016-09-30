package com.tops001.dubbott.test.provider.api.params;

import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/queryparamnotset")
public interface QueryParamNotSetService {

    @Path("int")
    @GET
    public String getDefault(@QueryParam("count") int count);

    @Path("short")
    @GET
    public String getDefault(@QueryParam("smallCount") short smallCount);

    @Path("long")
    @GET
    public String getDefault(@QueryParam("longCount") long longCount);

    @Path("float")
    @GET
    public String getDefault(@QueryParam("floatCount") float floatCount);

    @Path("double")
    @GET
    public String getDefault(@QueryParam("d") double count);

    @Path("byte")
    @GET
    @Produces("text/plain")
    public String getDefault(@QueryParam("b") byte count);

    @Path("char")
    @GET
    public String getDefault(@QueryParam("letter") char count);

    @Path("set")
    @GET
    public String getDefault(@QueryParam("bag") Set<Integer> stuff);

    @Path("list")
    @GET
    public String getDefault(@QueryParam("letter") List<String> stuff);
    
}
