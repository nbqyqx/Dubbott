package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.EncodingParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class EncodingParamServiceTest {

	@Autowired
	private EncodingParamService encodingParamService;

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 如果是get请求，那么可以使用http客户端采用如下方式进行参数传递：<br/>
	 * http://127.0.0.1:8080/dubbott-test-provider-service/v1.0/encodingparam/
	 * city;appversion=1.1?location=location=Austin%2B%20Texas <br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedQueryParam() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopInCity("Austin%2B%20Texas");
		assertEquals("getShopInCity:location=Austin%2B%20Texas;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedQueryParamMethod() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopInCityMethod("Austin%2B%20Texas");
		assertEquals("getShopInCityMethod:location=Austin%2B%20Texas;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedPathParam() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopInCountry("United%20States%20of%20America");
		assertEquals("getShopInCountry:location=United%20States%20of%20America;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedPathParamMethod() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopInCountryMethod("United%20States%20of%20America");
		assertEquals("getShopInCountryMethod:location=United%20States%20of%20America;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedMatrixParam() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopOnStreet("Burnet%20Road");
		assertEquals("getShopOnStreet:location=Burnet%20Road;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedMatrixParamMethod() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopOnStreetMethod("Burnet%2B%20Road");
		assertEquals("getShopOnStreetMethod:location=Burnet%2B%20Road;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedFormParam() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopInRegion("The%20Southwest");
		assertEquals("getShopInRegion:location=The%20Southwest;appversion=1.1%2B", result);
	}

	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 可以尝试通过原始http的客户端进行参数传递<br/>
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSingleEncodedFormParamMethod() throws Exception {
		encodingParamService.setAppVersion("1.1");
		String result = encodingParamService.getShopInRegionMethod("The%20Southwest");
		assertEquals("getShopInRegionMethod:location=The%20Southwest;appversion=1.1%2B", result);
	}

}
