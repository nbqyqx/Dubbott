package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.DefaultValueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class DefaultValueServiceTest {

	@Autowired
	private DefaultValueService defaultValueService;
	
	@Test
	public void testDefaultvalue() {
		String result = defaultValueService.getRow(null);
		assertEquals("getRow:" + "offset="
                + "0"
                + ";version="
                + "1.0"
                + ";limit="
                + "100"
                + ";sort="
                + "normal", result);
	}
	
	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的参数。<br/>
	 * 如果是get请求，那么可以使用http客户端进行测试的时候则可以在url的后面以？参数名＝参数值的方式进行数据传递来进行service基本服务的初始化<br/>
	 * 如：http://127.0.0.1:8080/dubbott-test-provider-service/v1.0/defaultvalue?sort=backward&limit=314
	 */
	@Test
	@Ignore
	public void testUseSomeDefaultValue() {
		defaultValueService.setSort("backward");
		defaultValueService.setLimit("314");
		String result = defaultValueService.getRow(null);
		assertEquals("getRow:" + "offset="
                + "314"
                + ";version="
                + "1.0"
                + ";limit="
                + "100"
                + ";sort="
                + "backward", result);
	}
	
}
