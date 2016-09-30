package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.QueryParamNotSetService;
import com.tops001.dubbott.test.provider.api.params.QueryParamService;
import com.tops001.dubbott.test.provider.api.params.QueryParamService.ParamWithStringConstructor;
import com.tops001.dubbott.test.provider.api.params.QueryParamService.ParamWithValueOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class QueryParamTest {

	@Autowired
	private QueryParamNotSetService queryParamNotSetService;

	@Autowired
	private QueryParamService queryParamService;

	private static Random r = new Random();

	@Test
	public void test() throws Exception {

		assertEquals("a", queryParamNotSetService.getDefault('a'));
		assertEquals("0", queryParamNotSetService.getDefault(0));

		byte b = (byte) r.nextInt(Byte.MAX_VALUE);
		assertEquals("" + b, queryParamNotSetService.getDefault(b));

		assertEquals("0.0", queryParamNotSetService.getDefault(0d));
		double d = r.nextDouble();
		assertEquals("" + d, queryParamNotSetService.getDefault(d));

		assertEquals("0.0", queryParamNotSetService.getDefault(0f));
		float f = r.nextFloat();
		assertEquals("" + f, queryParamNotSetService.getDefault(f));

		assertEquals("0", queryParamNotSetService.getDefault(0));
		int i = r.nextInt();
		assertEquals("" + i, queryParamNotSetService.getDefault(i));

		assertEquals("0", queryParamNotSetService.getDefault((short) 0));
		short s = (short) r.nextInt(Short.MAX_VALUE);
		assertEquals("" + s, queryParamNotSetService.getDefault(s));

		assertEquals("0", queryParamNotSetService.getDefault(0L));
		long l = r.nextLong();
		assertEquals("" + l, queryParamNotSetService.getDefault(l));

		Set<Integer> set = new HashSet<>();
		assertEquals("0", queryParamNotSetService.getDefault(set));
		set.add(5);
		assertEquals("1", queryParamNotSetService.getDefault(set));

		List<String> list = new ArrayList<>();
		assertEquals("0", queryParamNotSetService.getDefault(list));
		list.add("aaa");
		assertEquals("1", queryParamNotSetService.getDefault(list));

	}

	/**
	 * Tests that no query parameters sent still calls proper resource.
	 */
	@Test
	public void testNoQueryParam() throws Exception {
		assertEquals("deleteConstructorQueryID:notset", queryParamService.deleteConstructorQueryID());
		assertEquals("getConstructorQueryID:notset", queryParamService.getConstructorQueryID());
		assertEquals("postConstructorQueryID:notset", queryParamService.postConstructorQueryID());
		assertEquals("putConstructorQueryID:notset", queryParamService.putConstructorQueryID());
	}

	/**
	 * Tests that a no constructor query parameter is set.
	 */
	@Test
	public void testNoConstructorQueryParamAndSimpleQueryParam() throws Exception {
		assertEquals("deleteSimpleQueryParameter:notset;hi", queryParamService.deleteSimpleQueryParameter("hi"));
		assertEquals("getSimpleQueryParameter:notset;hi", queryParamService.getSimpleQueryParameter("hi"));
		assertEquals("postSimpleQueryParameter:notset;hi", queryParamService.postSimpleQueryParameter("hi"));
		assertEquals("putSimpleQueryParameter:notset;hi", queryParamService.putSimpleQueryParameter("hi"));
	}

	/**
	 * Tests the constructor and simple query parameter can be out of order.
	 */
	@Test
	@Ignore("dubbott客户端无法设置非资源方法的query参数：queryid")
	public void testOutOfOrderSimpleQueryParam() throws Exception {

	}

	/**
	 * Tests that query parameters are case sensitive.
	 */
	@Test
	@Ignore("dubbott客户端无法设置非资源方法的query参数：queryid")
	public void testLowercaseQueryParam() throws Exception {

	}

	/**
	 * Tests multiple query parameters sent to same resource.
	 */
	@Test
	public void testMultipleQueryParam() throws Exception {
		assertEquals("getMultiQueryParameter:notset;hi;789;1moreparam2go",
				queryParamService.getMultiQueryParameter("hi", "789", "1moreparam2go"));
		assertEquals("deleteMultiQueryParameter:notset;hi;789;1moreparam2go",
				queryParamService.deleteMultiQueryParameter("hi", "789", "1moreparam2go"));
		assertEquals("putMultiQueryParameter:notset;hi;789;1moreparam2go",
				queryParamService.putMultiQueryParameter("hi", "789", "1moreparam2go"));
		assertEquals("postMultiQueryParameter:notset;hi;789;1moreparam2go",
				queryParamService.postMultiQueryParameter("hi", "789", "1moreparam2go"));
	}

	/**
	 * Tests that primitive types are accepted in query parameters.
	 */
	@Test
	public void testPrimitiveTypedQueryParameter() throws Exception {
		assertEquals("getQueryParameterPrimitiveTypes:false;12;3.14;3;b;1234567890;32456;123.0", queryParamService
				.getQueryParameterPrimitiveTypes(false, 12, 3.14, (byte) 3, 'b', 1234567890L, (short) 32456, 123F));
	}
	
	/**
     * Tests that primitive types are accepted in query parameters.
     */
    @Test
    public void testQueryParameterTypeWithStringConstructor() throws Exception {
    	ParamWithStringConstructor constructor = new ParamWithStringConstructor("1234");
        assertEquals("getQueryParameterStringConstructor:1234", queryParamService.getQueryParameterStringConstructor(constructor));
    }
    
    /**
     * Tests that primitive types are accepted in query parameters.
     */
    @Test
    public void testQueryParameterTypeWithValueOfMethod() throws Exception {
    	ParamWithValueOf param = ParamWithValueOf.valueOf("456");
        assertEquals("getQueryParameterValueOf:456789", queryParamService.getQueryParameterValueOf(param));
    }

}
