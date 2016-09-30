package com.tops001.dubbott.test.provider.service.params;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.DiffCaptureVariablesParamsService;

/**
 * To test that JAX-RS runtime can match request to
 * subresource methods with the same regular expression,
 * but different capture variable names. None of the parameters
 * should have null values.
 */
@Service
public class DiffCaptureVariablesParamsServiceImpl implements DiffCaptureVariablesParamsService {

    // See https://issues.apache.org/jira/browse/WINK-344 for history
    public String getMethod(String id) {
        return "id1_" + id;
    }

    public String doSomething(String id) {
        return "id2_" + id;
    }

    public String deleteMethod(String id) {
        return "id3_" + id;
    }
    
}
