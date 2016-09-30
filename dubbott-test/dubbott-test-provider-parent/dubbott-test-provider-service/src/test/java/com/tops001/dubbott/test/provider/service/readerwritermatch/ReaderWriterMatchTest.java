package com.tops001.dubbott.test.provider.service.readerwritermatch;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAcceptableException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctc.wstx.compat.QNameCreator;
import com.tops001.dubbott.test.provider.api.readerwritermatch.ReaderWriterMatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class ReaderWriterMatchTest {

	@Autowired
	private ReaderWriterMatchService readerWriterMatchService;
	
	/**
     * RTC defect 161757
     * 
     * @test_Strategy: An implementation MUST support application-provided
     *                 entity providers and MUST use those in preference
     *                 to its own pre-packaged providers when either could
     *                 handle the same request. More precisely, step 4 in
     *                 Section 4.2.1 and step 5 in Section 4.2.2 MUST prefer
     *                 application-provided over pre-packaged entity providers.
     *                 i.e. When have the same mediaType
     */
    @Test
    public void testPostBoolean() throws Exception {

        boolean result = readerWriterMatchService.bool(false);
        
        Assert.assertFalse(result);
        
    }

    /*
     * RTC defect 162143
     * 
     * @test_Strategy: Character, text/plain media type
     * 
     * The pre-packaged JAXB and the prepackaged primitive type
     * MessageBodyReaders MUST throw a BadRequestException
     * (400 status) for zero-length request entities.
     */
    @Test(expected=BadRequestException.class)
    public void testPostEmptyEntiry() throws Exception {

        readerWriterMatchService.character(null);

    }

    /*
     * RTC defect 161035 cxf-6256
     * 
     * @test_Strategy: Generate a WebApplicationException with a not acceptable
     * response (HTTP 406 status) and no entity. The exception
     * MUST be processed as described in section 3.3.4. Finish.
     */
    //org.apache.cxf.jaxrs.interceptor.JAXRSOutInterceptor.checkFinalContentType has issue on spec 3.8|2 
    @Test(expected=NotAcceptableException.class)
    public void testGetAcceptTextStar() throws Exception {
        
    	readerWriterMatchService.geText();

    }

    /*
     * RTC defect 174015
     * Sort the selected MessageBodyWriter providers with a
     * primary key of generic type where providers whose
     * generic type is the nearest superclass of the object
     * class are sorted first and a secondary key of media
     * type (see Section 4.2.3).
     */
    @Test
    public void testPostCharacter() throws Exception {

    	Character result = readerWriterMatchService.character('a');
    	
    	System.out.println(result);
    	
    	assertEquals('N', result.charValue());

    }

    /*
     * RTC defect 174461 nested generic type
     * 
     * @test_Strategy: The implementation-supplied entity provider(s) for
     * javax.xml.bind.JAXBElement and application supplied
     * JAXB classes MUST use JAXBContext instances provided
     * by application-supplied context resolvers, see
     * Section 4.3.
     * 
     * com.ibm.ws.jaxrs.fat.provider.readerwritermatch.ApplicationJaxbProvider should not be chosen , it's reader will return null
     */
    @Test
    public void testPostJAXBElement() throws Exception {

        QName name = QNameCreator.create("", "", "");
        JAXBElement<String> param = new JAXBElement<>(name, String.class, "anything");
        JAXBElement<String> result = readerWriterMatchService.jaxb(param);
        System.out.println(result.getValue());

    }
	
}
