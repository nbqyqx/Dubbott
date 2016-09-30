package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.AutoDecodeParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class AutoDecodeParamServiceTest {
	
	@Autowired
	private AutoDecodeParamService autoDecodeParamService;
	
	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 如果是get请求，那么可以使用http客户端采用如下方式进行参数传递：<br/>
	 *   http://127.0.0.1:8080/dubbott-test-provider-service/v1.0/decodedparams/city;appversion=1.1?location=%21%20%2A%20%27%20%28%20%29%20%3B%20%3A%20%40%20%26%20%3D%20%2B%20%24%20%2C%20%2F%20%3F%20%25%20%23%20%5B%20%5D	<br/>
	 * @throws Exception
	 */
	@Test
	@Ignore
    public void testSingleDecodedQueryParam() throws Exception {
		autoDecodeParamService.setAppVersion("1.1");
        String result = autoDecodeParamService.getShopInCityDecoded("%21%20%2A%20%27%20%28%20%29%20%3B%20%3A%20%40%20%26%20%3D%20%2B%20%24%20%2C%20%2F%20%3F%20%25%20%23%20%5B%20%5D");
        assertEquals("getShopInCityDecoded:location=! * ' ( ) ; : @ & = + $ , / ? % # [ ];appversion=1.1",
                     result);
    }
	
	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 如果是get请求，那么可以使用http客户端进行参数传递。<br/>
	 * @throws Exception
	 */
	@Test
	@Ignore
    public void testSingleDecodedPathParm() throws Exception {
		autoDecodeParamService.setAppVersion("1.1");
        String result = autoDecodeParamService.getShopInCountryDecoded("United%20States%20of%20America");
        assertEquals("getShopInCountryDecoded:location=United States of America;appversion=1.1,2",
                     result);
    }
	
	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 如果是get请求，那么可以使用http客户端进行参数传递。<br/>
	 * @throws Exception
	 */
	@Test
	@Ignore
    public void testSingleDecodedMatrixParam() throws Exception {
		autoDecodeParamService.setAppVersion("1.1");
        String result = autoDecodeParamService.getShopOnStreetDecoded("Burnet%20Road");
        assertEquals("getShopOnStreetDecoded:location=Burnet Road;appversion=1.1+",
                result);
    }
	
	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 如果是get请求，那么可以使用http客户端进行参数传递。<br/>
	 * @throws Exception
	 */
	@Test
	@Ignore
    public void testSingleDecodedFormParam() throws Exception {
        autoDecodeParamService.setAppVersion("1.1");
        String result = autoDecodeParamService.getShopInRegionDecoded("%21%20%2A%20%27%20%28%20%29%20%3B%20%3A%20%40%20%26%20%3D%20%2B%20%24%20%2C%20%2F%20%3F%20%25%20%23%20%5B%20%5D");
        assertEquals("getShopInRegionDecoded:location=! * ' ( ) ; : @ & = + $ , / ? % # [ ];appversion=",
                result);
        
    }

}
