package com.tops001.dubbott.demo.common;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tops001.dubbott.demo.common.ApiInvokeResult;
import com.tops001.dubbott.demo.common.ResultCode;

public class RestServerExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestServerExceptionHandler.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error("Error cause: ", e);
        ApiInvokeResult<Object> result = ApiInvokeResult.buildFailResult(ResultCode.SYS_ERR, e.getMessage());
        
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e;
            StringBuffer buf = new StringBuffer();
            for (@SuppressWarnings("rawtypes")
            ConstraintViolation cv : cve.getConstraintViolations()) {
                buf.append(cv.getMessage()).append(", InvalidValue is ").append(cv.getInvalidValue()).append(";");
                buf.append(System.lineSeparator());
            }
            result = ApiInvokeResult.buildFailResult(ResultCode.PARAM_INVALID, buf.toString());
        } /**
        else if (e instanceof ConstraintViolationException) {
            ...
        }*/
        

        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON_TYPE).encoding("utf-8").build();
    }
}
