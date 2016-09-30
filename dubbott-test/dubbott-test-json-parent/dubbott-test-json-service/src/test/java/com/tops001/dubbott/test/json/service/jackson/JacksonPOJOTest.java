package com.tops001.dubbott.test.json.service.jackson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.tops001.dubbott.test.json.api.jackson.JacksonPOJOService;
import com.tops001.dubbott.test.json.api.jackson.JacksonPOJOService.Person;
import com.tops001.dubbott.test.json.api.jackson.SimplePOJOService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-json-client.xml")
public class JacksonPOJOTest {

	@Autowired
	private JacksonPOJOService jacksonPOJOService;
	
	@Autowired
	private SimplePOJOService simplePOJOService;
	
    @Test
    public void testGETPerson() throws IOException {
    	Person person = jacksonPOJOService.getPerson();
    	assertEquals("first", person.getFirst());
    	assertEquals("last", person.getLast());
    }

    @Test
    public void testGETPersonWithNullValue() throws IOException {
    	Person person = jacksonPOJOService.getPersonWithNullValue();
    	assertEquals("first", person.getFirst());
    	assertNull(person.getLast());
    }

    @Test
    public void testPOSTPerson() throws IOException {
    	Person p = new Person();
    	p.setFirst("first");
    	p.setLast("last");
    	p = jacksonPOJOService.postPerson(p);
    	assertEquals("first", p.getFirst());
    	assertEquals("last", p.getLast());
    }

    @Test
    public void testGETCollection() throws IOException {
    	List<String> list = jacksonPOJOService.getCollection();
    	assertEquals(3, list.size());
    	assertEquals("string1", list.get(0));
    	assertEquals("", list.get(1));
    	assertEquals("string3", list.get(2));
    }

    @Test
    public void testPOSTCollection() throws IOException {
    	List<String> paramList = new ArrayList<>();
    	paramList.add("s1");
    	paramList.add("s2");
    	paramList.add("s3");
    	paramList = jacksonPOJOService.postCollection(paramList);
    	assertEquals(3, paramList.size());
    	assertEquals("s1", paramList.get(0));
    	assertEquals("s2", paramList.get(1));
    	assertEquals("s3", paramList.get(2));
    }

    @Test
    public void testGETCollectionWithObject() throws IOException {
    	List<Person> persons = jacksonPOJOService.getPersonCollection();
    	assertEquals(3, persons.size());
    	assertEquals("first1", persons.get(0).getFirst());
    	assertEquals("last1", persons.get(0).getLast());
    	assertEquals("first2", persons.get(1).getFirst());
    	assertEquals("last2", persons.get(1).getLast());
    	assertEquals("first3", persons.get(2).getFirst());
    	assertEquals("last3", persons.get(2).getLast());
    }

    @Test
    public void testPOSTCollectionWithObject() throws IOException {
    	List<Person> persons = new ArrayList<>();
    	persons.add(new Person("first1", "last1"));
    	persons.add(new Person("first2", "last2"));
    	persons.add(new Person("first3", "last3"));
    	persons = jacksonPOJOService.postPeopleCollection(persons);
    	assertEquals(3, persons.size());
    	assertEquals("first1", persons.get(0).getFirst());
    	assertEquals("last1", persons.get(0).getLast());
    	assertEquals("first2", persons.get(1).getFirst());
    	assertEquals("last2", persons.get(1).getLast());
    	assertEquals("first3", persons.get(2).getFirst());
    	assertEquals("last3", persons.get(2).getLast());
    }

    @Test
    public void testGETCollectionWithCollection() throws IOException {
    	List<List<Person>> persons = jacksonPOJOService.getCollectionofCollection();
    	String json = new GsonBuilder().create().toJson(persons);
        assertEquals("[[{\"first\":\"first1\",\"last\":\"last1\"}," + "{\"first\":\"first2\",\"last\":\"last2\"},"
                     + "{\"first\":\"first3\",\"last\":\"last3\"}],"
                     + "[{\"first\":\"first4\",\"last\":\"last4\"},"
                     + "null,"
                     + "{\"first\":\"first6\",\"last\":\"last6\"}]]", json);
    }

    @Test
    public void testPOSTCollectionWithCollection() throws IOException {
      String json = "[[{\"first\":\"first1\",\"last\":\"last1\"}," + "{\"first\":\"first2\",\"last\":\"last2\"},"
      + "{\"first\":\"first3\",\"last\":\"last3\"}],"
      + "[{\"first\":\"first4\",\"last\":\"last4\"},"
      + "null,"
      + "{\"first\":\"first6\",\"last\":\"last6\"}]]";
      @SuppressWarnings("serial")
      List<List<Person>> persons = new GsonBuilder().create().fromJson(json, new TypeToken<List<List<Person>>>(){}.getType());
      String result = new GsonBuilder().create().toJson(persons);
      persons = jacksonPOJOService.postCollectionofCollection(persons);
        assertEquals("[[{\"first\":\"first1\",\"last\":\"last1\"}," + "{\"first\":\"first2\",\"last\":\"last2\"},"
                     + "{\"first\":\"first3\",\"last\":\"last3\"}],"
                     + "[{\"first\":\"first4\",\"last\":\"last4\"},"
                     + "null,"
                     + "{\"first\":\"first6\",\"last\":\"last6\"}]]", result);
    }

    @Test
    public void testGETCollectionWithArray() throws IOException {
    	List<Person[]> persons = jacksonPOJOService.getCollectionofArray();
    	String result = new GsonBuilder().create().toJson(persons);
        assertEquals("[[{\"first\":\"first1\",\"last\":\"last1\"}," + "{\"first\":\"first2\",\"last\":\"last2\"},"
                     + "{\"first\":\"first3\",\"last\":\"last3\"}],"
                     + "[{\"first\":\"first4\",\"last\":\"last4\"},"
                     + "null,"
                     + "{\"first\":\"first6\",\"last\":\"last6\"}]]", result);
    }

    @Test
    public void testPOSTCollectionWithArray() throws IOException {
        String json = "[[{\"first\":\"first1\",\"last\":\"last1\"}," + "{\"first\":\"first2\",\"last\":\"last2\"},"
                                               + "{\"first\":\"first3\",\"last\":\"last3\"}],"
                                               + "[{\"first\":\"first4\",\"last\":\"last4\"},"
                                               + "null,"
                                               + "{\"first\":\"first6\",\"last\":\"last6\"}]]";
        @SuppressWarnings("serial")
		List<Person[]> persons = new GsonBuilder().create().fromJson(json, new TypeToken<List<Person[]>>() {}.getType());
		String result = new GsonBuilder().create().toJson(persons);
        assertEquals("[[{\"first\":\"first1\",\"last\":\"last1\"}," + "{\"first\":\"first2\",\"last\":\"last2\"},"
                     + "{\"first\":\"first3\",\"last\":\"last3\"}],"
                     + "[{\"first\":\"first4\",\"last\":\"last4\"},"
                     + "null,"
                     + "{\"first\":\"first6\",\"last\":\"last6\"}]]", result);
    }

    /**
     * Tests that a HashMap can be serialized and deserialized via Jackson.
     * 
     * @throws Exception
     */
    @Test
    public void testHashMap() throws Exception {
    	Map<String, Object> result = simplePOJOService.getMap();
        Map<String, String> person = new HashMap<String, String>();
        person.put("name", "John Doe");
        person.put("age", "40");
        assertEquals(person, result.get("person"));

        List<String> arr = new ArrayList<String>();
        arr.add("firstArrValue");
        arr.add("secondArrValue");
        assertEquals(arr, result.get("arr"));
        assertEquals(2, result.keySet().size());
    }

    /**
     * Tests that a List can be serialized and deserialized via Jackson.
     * 
     * @throws Exception
     */
    @Test
    public void testList() throws Exception {
    	List<String> listFromService = simplePOJOService.getList();
        List<String> arr = new ArrayList<String>();
        arr.add("firstArrValue");
        arr.add("secondArrValue");
        assertEquals(arr, listFromService);
    }

    @Test
    public void testPOJO() throws Exception {
    	com.tops001.dubbott.test.json.model.jackson.Person p = simplePOJOService.getPOJO();
        System.out.println(p);
        assertEquals("John Doe", p.getName());
        assertEquals(40, p.getAge());
        assertEquals("randomValue", p.randomProp());
        assertEquals("Jane Smith", p.getManager().getManagerName());
        assertEquals(123456789, p.getManager().getManagerId());
    }
}