package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import org.apache.cxf.jaxrs.impl.PathSegmentImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.PathSegmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class PathSegmentServiceTest {
	
	@Autowired
	private PathSegmentService pathSegmentService;
	
	@Test
    public void testPathSegmentNoMatrixParameters() throws Exception {
        assertEquals("somepath", pathSegmentService.helloPath(new PathSegmentImpl("somepath")));
        assertEquals("123456", pathSegmentService.helloPath(new PathSegmentImpl("123456")));
    }
	
	@Test
	@Ignore("/pathsegment/matrix/{loc}业务接口过于复杂不知道怎么传参数")
    public void testPathSegmentMatrixParameters() throws Exception {
       // TODO: /pathsegment/matrix/{loc}业务接口过于复杂不知道怎么传参数
    }

}
