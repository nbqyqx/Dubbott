package com.tops001.dubbott.demo.user.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.client.InvocationCallback;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.tops001.dubbott.demo.common.ApiInvokeResult;
import com.tops001.dubbott.demo.common.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/dubbo-demo-consumer.xml")
public class UserRestServiceTest {

    private static final Logger    LOG = LoggerFactory.getLogger(UserRestServiceTest.class);

    @Resource
    private UserRestService userRestService;

    //@Test
    public void testEchoCXFAPI() {
        UserRestService proxy = JAXRSClientFactory.create("http://localhost:8080/dubbott-demo-provider/v1.0/", UserRestService.class);
        WebClient.getConfig(proxy).getRequestContext().put(InvocationCallback.class.getName(), new EchoServiceCallback());
        assertNull(proxy.echoService("neal niubi"));
    }
    
    @Test
    public void testEcho() throws InterruptedException {
        ClientConfiguration config = WebClient.getConfig(userRestService);
        assertNull(userRestService.echoService("neal niubi"));
        Thread.sleep(1000);
        assertEquals(EchoServiceCallback.res, "hello neal niubi");
    }
    
    @Test
    public void test() {
        User user = buileUser(Long.valueOf(123L));
        ApiInvokeResult<User> registerUser = userRestService.registerUser(user);
        Long id = registerUser.getValue().getId();
        LOG.info("===>>> " + id);
        assertEquals(Long.valueOf(123L), id);
    }

    @Test
    public void testFindUsers() {
        User user = userRestService.getUser(234L);
        LOG.info("===>>> " + user);
        //assertNull(user);
        
        user = buileUser(Long.valueOf(234L));
        userRestService.registerUser(user);
        User userRes = userRestService.getUser(234L);
        LOG.info("===>>> " + userRes);
        assertEquals(user.getId(), userRes.getId());

    }

    //@Test
    public void testGetUser() {
        for (int i = 1; i < 100; i++) {
            User user2 = userRestService.getUser((long) i);
            LOG.info("===>>> " + user2);
        }
    }

    public User buileUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("ZhangSan");
        return user;
    }

    private static List<Object> buildProviders() {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJaxbJsonProvider());
        return providers;
    }

}
