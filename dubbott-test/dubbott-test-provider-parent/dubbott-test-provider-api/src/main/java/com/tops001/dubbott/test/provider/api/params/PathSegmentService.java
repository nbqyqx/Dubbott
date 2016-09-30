package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.PathSegment;

@Path("/pathsegment")
public interface PathSegmentService {

    @Path("{loc}")
    @GET
    public String helloPath(@PathParam("loc") PathSegment pathSegment);

    @Path("matrix/{loc}")
    @GET
    public String helloPath(@PathParam("loc") String path,
                            @PathParam("loc") PathSegment pathSegment,
                            @MatrixParam("mp") String matrix);
    
}
