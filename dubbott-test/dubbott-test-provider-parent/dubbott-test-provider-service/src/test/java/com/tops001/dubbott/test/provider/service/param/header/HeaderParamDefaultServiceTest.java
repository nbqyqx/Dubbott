package com.tops001.dubbott.test.provider.service.param.header;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.header.HeaderParamDefaultService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class HeaderParamDefaultServiceTest {

	@Autowired
	private HeaderParamDefaultService headerParamDefaultService;
	
	/**
     * Tests that headers are properly set with <code>@DefaultValue</code>s set.
     */
    @Test
    public void testHeaderDefaultValue() throws Exception {
        /*
         * the default values with no headers set.
         */
        Response response = headerParamDefaultService.getHeaderParam(null);
        assertEquals(200, response.getStatus());
        assertEquals("", response.readEntity(String.class));
        assertEquals("MyCustomPropertyHeader", response.getHeaderString("RespCustomPropertyHeader"));
        assertEquals("MyCustomConstructorHeader", response.getHeaderString("RespCustomConstructorHeader"));
        assertEquals("english", response.getHeaderString("RespAccept-Language"));
        assertEquals("MyCustomMethodHeader", response.getHeaderString("RespCustomMethodHeader"));

        /* dubbott客户端无法设置http头的参数，如果参数不在资源方法里而在其他方法里 */
    }
    
}
