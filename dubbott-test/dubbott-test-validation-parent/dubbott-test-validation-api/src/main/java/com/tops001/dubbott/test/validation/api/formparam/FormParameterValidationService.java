package com.tops001.dubbott.test.validation.api.formparam;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("params/form/validate/paramnotmultivaluedmaparam")
public interface FormParameterValidationService {

    @POST
    public String getSomething(@FormParam("firstkey") String p1, String p2);
    
}
