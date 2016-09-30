package com.tops001.dubbott.test.validation.api.formfield;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("params/form/validate/fieldnotmultivaluedmapparam")
public interface FormFieldValidationService {

    @POST
    public String getSomething(String something);
    
}
