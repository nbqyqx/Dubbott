package com.tops001.dubbott.test.provider.api.params;

import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;

@Path("/matrixparamnotset")
public interface MatrixParamNotSetService {

    @Path("int")
    @GET
    public String getDefault(@MatrixParam("count") int count);

    @Path("short")
    @GET
    public String getDefault(@MatrixParam("smallCount") short smallCount);

    @Path("long")
    @GET
    public String getDefault(@MatrixParam("longCount") long longCount);

    @Path("float")
    @GET
    public String getDefault(@MatrixParam("floatCount") float floatCount);

    @Path("double")
    @GET
    public String getDefault(@MatrixParam("count") double count);

    @Path("byte")
    @GET
    public String getDefault(@MatrixParam("b") byte count);

    @Path("char")
    @GET
    public String getDefault(@MatrixParam("letter") char letter);

    @Path("set")
    @GET
    public String getDefault(@MatrixParam("bag") Set<Integer> stuff);

    @Path("list")
    @GET
    public String getDefault(@MatrixParam("letter") List<String> stuff);
    
}
