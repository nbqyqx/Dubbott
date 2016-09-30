package com.tops001.dubbott.test.json.service.jackson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.json.api.jackson.SimplePOJOService;
import com.tops001.dubbott.test.json.model.jackson.Manager;
import com.tops001.dubbott.test.json.model.jackson.Person;

@Service
public class SimplePOJOServiceImpl implements SimplePOJOService {

    public Map<String, Object> getMap() {
        Map<String, String> person = new HashMap<String, String>();
        person.put("name", "John Doe");
        person.put("age", "40");

        List<String> arr = new ArrayList<String>();
        arr.add("firstArrValue");
        arr.add("secondArrValue");

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("person", person);
        json.put("arr", arr);
        return json;
    }

    public List<String> getList() {
        List<String> arr = new ArrayList<String>();
        arr.add("firstArrValue");
        arr.add("secondArrValue");

        return arr;
    }

    public Person getPOJO() {
        Person p = new Person();
        p.setAge(40);
        p.setName("John Doe");

        Manager m = new Manager();
        m.setManagerName("Jane Smith");
        m.setManagerId(123456789);
        p.setManager(m);
        return p;
    }

}
