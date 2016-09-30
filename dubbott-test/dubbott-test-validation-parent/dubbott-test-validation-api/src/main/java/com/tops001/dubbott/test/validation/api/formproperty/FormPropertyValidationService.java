package com.tops001.dubbott.test.validation.api.formproperty;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("params/form/validate/propertynotmultivaluedmaparam")
public interface FormPropertyValidationService {

    @POST
    public String doSomething(@FormParam(value = "P1") String p1, String something);
    
}
