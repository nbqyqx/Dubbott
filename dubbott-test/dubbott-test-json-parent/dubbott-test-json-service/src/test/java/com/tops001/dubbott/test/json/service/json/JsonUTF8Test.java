package com.tops001.dubbott.test.json.service.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.json.api.json.JsonUTF8Service;
import com.tops001.dubbott.test.json.model.json.Country;

/**
 * The purpose of this test is to work item 88030_PM76009_:
 * INVALID UTF-8 middle byte error WITH DANISH CHARACTER SET
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-json-client.xml")
public class JsonUTF8Test {

	@Autowired
    private JsonUTF8Service jsonUTF8Service;

    @Test
    public void testCountriesUpperCase() {
    	Country[] countries = jsonUTF8Service.getCountriesUpperCase();
        assertEquals("DANMARK", countries[0].getName());
        assertEquals("ÆGYPTEN", countries[1].getName());
    }

    @Test
    public void testCountriesLowerCase() {
        Country[] countries = jsonUTF8Service.getCountriesLowerCase();
        assertEquals("danmark", countries[0].getName());
        assertEquals("ægypten", countries[1].getName());
    }

}
