package com.tops001.dubbott.test.provider.service.param.header;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

import javax.ws.rs.core.Response;

import org.apache.cxf.common.util.SortedArraySet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.header.HeaderParamExceptionService;
import com.tops001.dubbott.test.provider.api.params.header.HeaderParamExceptionService.HeaderValueOf;
import com.tops001.dubbott.test.provider.api.params.header.HeaderStringConstructor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class HeaderParamExceptionServiceTest {

	@Autowired
	private HeaderParamExceptionService headerParamExceptionService;

	/**
	 * Tests that a custom header with a primitive type (int) can be used.
	 */
	@Test
	public void testHeaderParamPrimitiveException() throws Exception {

		Response response = headerParamExceptionService.getHeaderParam(314);
		assertEquals(200, response.getStatus());
		assertEquals("", response.readEntity(String.class));
		assertEquals("314", response.getHeaderString("RespCustomNumHeader"));

		/* dubbott客户端无法设置http头的参数，如果参数不在资源方法里而在其他方法里 */
	}

	// testHeaderParamStringConstructorException -
	// testHeaderPropertyValueOfException
	// result in FFDC logs being created, because either an NPE or
	// IllegalArgumentException
	// is thrown. The NPE occurs because part of what
	// executeStringConstructorHeaderTest
	// does is send a GET where there is NO header set.
	interface HeaderParamExceptionTestAPI<T> {
		Response test(T t) throws Exception;
	}

	/**
	 * Tests that a custom header with a custom constructor can be used.
	 * <ul>
	 * <li>If the header is not set, then the header parameter is set to null.
	 * </li>
	 * <li>If the header constructor throws an exception, then 400 Bad Request
	 * status is returned.</li>
	 * <li>If a <code>WebApplicationException</code> is thrown during parameter
	 * construction, then use that.</li>
	 * </ul>
	 */
	@Test
	public void testHeaderParamStringConstructorException() throws Exception {
		executeStringConstructorHeaderTest("CustomStringHeader", customHeader -> {
			if (Objects.nonNull(customHeader)) {
				HeaderStringConstructor customStringHeader = new HeaderStringConstructor(customHeader);
				return headerParamExceptionService.getStringConstructorHeaderParam(customStringHeader);
			}
			return headerParamExceptionService.getStringConstructorHeaderParam(null);
		});
	}
	
	/**
     * Tests that a custom header with a custom static valueOf method can be
     * used.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    public void testHeaderParamValueOfException() throws Exception {
    	executeValueOfHeaderTest("CustomValueOfHeader", customHeader -> {
			HeaderValueOf headerValueOf = HeaderValueOf.valueOf(customHeader);
			return headerParamExceptionService.getValueOfHeaderParam(headerValueOf);
		});
    }
    
    /**
     * Tests that a custom header is set correctly in a List of a type with a
     * custom static valueOf method.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("CXF客户端不支持")
    public void testHeaderParamListValueOfException() throws Exception {
    	executeValueOfHeaderTest("CustomValueOfHeader", customHeader -> {
    		List<HeaderValueOf> list = new ArrayList<>();
			HeaderValueOf headerValueOf = HeaderValueOf.valueOf(customHeader);
			list.add(headerValueOf);
			return headerParamExceptionService.getValueOfHeaderParam(list);
		});
    }
    
    /**
     * Tests that a custom header is set correctly in a Set of a type with a
     * custom static valueOf method.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("CXF客户端不支持")
    public void testHeaderParamSetValueOfException() throws Exception {
    	executeValueOfHeaderTest("CustomValueOfHeader", customHeader -> {
    		Set<HeaderValueOf> set = new HashSet<>();
			HeaderValueOf headerValueOf = HeaderValueOf.valueOf(customHeader);
			set.add(headerValueOf);
			return headerParamExceptionService.getValueOfHeaderParam(set);
		});
    }

    /**
     * Tests that a custom header is set correctly in a Set of a type with a
     * custom static valueOf method.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("CXF客户端不支持")
    public void testHeaderParamSortedSetValueOfException() throws Exception {
    	executeValueOfHeaderTest("CustomValueOfHeader", customHeader -> {
    		SortedSet<HeaderValueOf> sortedSet = new SortedArraySet<>();
			HeaderValueOf headerValueOf = HeaderValueOf.valueOf(customHeader);
			sortedSet.add(headerValueOf);
			return headerParamExceptionService.getValueOfHeaderParam(sortedSet);
		});
    }
    
    /**
     * Tests that a custom header is set correctly in a field with a String
     * constructor type.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("dubbott客户端无法传递head头信息")
    public void testHeaderFieldStringConstructorException() throws Exception {
    	executeStringConstructorHeaderTest("CustomStringConstructorFieldHeader", customHeader -> {
    		HeaderStringConstructor customStringHeader = new HeaderStringConstructor(customHeader);
    		headerParamExceptionService.setCustomStringConstructorFieldHeader(customStringHeader);
			return headerParamExceptionService.getFieldStringConstructorHeaderParam();
		});
    }
    
    /**
     * Tests that a custom header is set correctly in a field with a string
     * constructor.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("dubbott客户端无法传递head头信息")
    public void testHeaderFieldValueOfException() throws Exception {
    	executeValueOfHeaderTest("CustomValueOfFieldHeader", customHeader -> {
        	HeaderValueOf headerValueOf = HeaderValueOf.valueOf(customHeader);
    		headerParamExceptionService.setCustomValueOfFieldHeader(headerValueOf);
			return headerParamExceptionService.getFieldValueOfHeaderParam();
		});
    }

    /**
     * Tests that a custom header is set correctly in a field with a type with a
     * static valueOf method.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("dubbott客户端无法传递head头信息")
    public void testHeaderPropertyStringConstructorException() throws Exception {
        executeStringConstructorHeaderTest("CustomStringConstructorPropertyHeader", customHeader -> {
    		HeaderStringConstructor customStringHeader = new HeaderStringConstructor(customHeader);
    		headerParamExceptionService.setCustomConstructorPropertyHeader(customStringHeader);
			return headerParamExceptionService.getPropertyStringConstructorHeaderParam();
		});
    }
    
    /**
     * Tests that a custom header is set correctly in a field with a type with a
     * static valueOf method.
     * <ul>
     * <li>If the header is not set, then the header parameter is set to null.</li>
     * <li>If the header valueOf throws an exception, then 400 Bad Request
     * status is returned.</li>
     * <li>If a <code>WebApplicationException</code> is thrown during parameter
     * valueOf construction, then use that.</li>
     * </ul>
     */
    @Test
    @Ignore("dubbott客户端无法传递head头信息")
    public void testHeaderPropertyValueOfException() throws Exception {
    	executeValueOfHeaderTest("CustomValueOfPropertyHeader", customHeader -> {
    		HeaderValueOf headerValueOf = new HeaderValueOf(customHeader);
    		headerParamExceptionService.setCustomValueOfPropertyHeader(headerValueOf);
			return headerParamExceptionService.getPropertyValueOfHeaderParam();
		});
    }

	private void executeStringConstructorHeaderTest(String header,
			HeaderParamExceptionTestAPI<? super String> headerParamExceptionTestAPI) throws Exception {

		/* normal */
		Response response = headerParamExceptionTestAPI.test("MyCustomHeaderValue");
		assertEquals(200, response.getStatus());
		assertEquals("", response.readEntity(String.class));
		assertEquals("MyCustomHeaderValue", response.getHeaderString("Resp" + header));

		/* web app ex thrown */
		try {
			response = headerParamExceptionTestAPI.test("throwWeb");
			assertEquals(499, response.getStatus());
			assertEquals("HeaderStringConstructorWebAppEx", response.readEntity(String.class));
		} catch (Exception e) {
			// nothing to do
		}
		/* runtime exception thrown */
		try {
			response = headerParamExceptionTestAPI.test("throwNull");
			assertEquals(400, response.getStatus());
		} catch (Exception e) {
			// nothing to do
		}

		/* exception thrown */
		try {
			response = headerParamExceptionTestAPI.test("throwEx");
			assertEquals(400, response.getStatus());
		} catch (Exception e) {
			// nothing to do
		}
	}
	
	private void executeValueOfHeaderTest(String header,
			HeaderParamExceptionTestAPI<? super String> headerParamExceptionTestAPI) throws Exception {

		/* normal */
		Response response = headerParamExceptionTestAPI.test("MyCustomHeaderValue");
		assertEquals(200, response.getStatus());
		assertEquals("", response.readEntity(String.class));
		assertEquals("MyCustomHeaderValue", response.getHeaderString("Resp" + header));

		/* web app ex thrown */
		try {
			response = headerParamExceptionTestAPI.test("throwWeb");
			assertEquals(498, response.getStatus());
			assertEquals("HeaderStringConstructorWebAppEx", response.readEntity(String.class));
		} catch (Exception e) {
			// nothing to do
		}
		/* runtime exception thrown */
		try {
			response = headerParamExceptionTestAPI.test("throwNull");
			assertEquals(400, response.getStatus());
		} catch (Exception e) {
			// nothing to do
		}

		/* exception thrown */
		try {
			response = headerParamExceptionTestAPI.test("throwEx");
			assertEquals(400, response.getStatus());
		} catch (Exception e) {
			// nothing to do
		}
	}

}
