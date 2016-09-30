package com.tops001.dubbott.test.validation.api.beanvalidation10;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.tops001.dubbott.test.validation.model.beanvalidation10.BookWithValidation;

public interface BookStoreValidatable {
    @Valid
    Collection<BookWithValidation> list(@Min(1) int page);
}
