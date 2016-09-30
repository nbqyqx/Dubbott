package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.MatrixParamNotSetService;
import com.tops001.dubbott.test.provider.api.params.MatrixParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class MatrixParamTest {

	@Autowired
	private MatrixParamNotSetService matrixParamNotSetService;
	
	@Autowired
	private MatrixParamService matrixParamService;
	
	private static Random r = new Random();
	
	@Test
    public void test() throws Exception {
		
        assertEquals("a", matrixParamNotSetService.getDefault('a'));
        byte b = (byte) r.nextInt(Byte.MAX_VALUE);
        assertEquals("" + b, matrixParamNotSetService.getDefault(b));
        assertEquals("0", matrixParamNotSetService.getDefault(0));
        
        double d = r.nextDouble();
        assertEquals("" + d, matrixParamNotSetService.getDefault(d));
        assertEquals("0.0", matrixParamNotSetService.getDefault(0d));

        float f = r.nextFloat();
        assertEquals("" + f, matrixParamNotSetService.getDefault(f));
        assertEquals("0.0", matrixParamNotSetService.getDefault(0f));
        
        int i = r.nextInt();
        assertEquals("" + i, matrixParamNotSetService.getDefault(i));
        assertEquals("0", matrixParamNotSetService.getDefault(0));
        
        short s = (short) r.nextInt(Short.MAX_VALUE);
        assertEquals("" + s, matrixParamNotSetService.getDefault(s));
        assertEquals("0", matrixParamNotSetService.getDefault(0));
        
        long l = r.nextLong();
        assertEquals("" + l, matrixParamNotSetService.getDefault(l));
        assertEquals("0", matrixParamNotSetService.getDefault(0L));
        
        Set<Integer> set = new HashSet<>();
        assertEquals("0", matrixParamNotSetService.getDefault(set));
        set.add(5);
        assertEquals("1", matrixParamNotSetService.getDefault(set));
        
        List<String> list = new ArrayList<>();
        assertEquals("0", matrixParamNotSetService.getDefault(list));
        list.add("aaa");
        assertEquals("1", matrixParamNotSetService.getDefault(list));
        
    }
	
}
