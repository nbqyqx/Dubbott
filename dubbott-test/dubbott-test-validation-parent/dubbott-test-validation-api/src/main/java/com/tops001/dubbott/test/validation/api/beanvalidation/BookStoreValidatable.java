package com.tops001.dubbott.test.validation.api.beanvalidation;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.tops001.dubbott.test.validation.model.beanvalidation.BookWithValidation;

public interface BookStoreValidatable {
	
    @Valid
    Collection<BookWithValidation> list(@Min(1) int page);
    
    @Valid
    public abstract BookWithValidation getBook(@Pattern(regexp = "\\d+") String id);
    
}
