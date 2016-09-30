package com.tops001.dubbott.test.json.service.jackson;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.json.api.jackson.JacksonPOJOService;

/**
 * <code>JacksonPOJOResource</code> is a simple POJO which is annotated with
 * JAX-RS annotations to turn it into a JAX-RS resource.
 * <p/>
 * This class has a {@link Path} annotation with the value "/person" which
 * means the resource will be available at:
 * <code>http://&lt;hostname&gt;:&lt;port&gt/&lt;context root&gt;/&lt;servlet path&gt;/person</code>
 * <p/>
 * Remember to add this resource class to the {@link JacksonPOJOApplication#getClasses()} method.
 */
@Service
public class JacksonPOJOServiceImpl implements JacksonPOJOService {

    public Person getPerson() {
        Person p = new Person();
        p.setFirst("first");
        p.setLast("last");
        return p;
    }

    public Person getPersonWithNullValue() {
        Person p = new Person();
        p.setFirst("first");
        p.setLast(null);
        return p;
    }

    public Person postPerson(Person p) {
        return p;
    }

    public List<String> getCollection() {
        List<String> list = new ArrayList<String>();
        list.add("string1");
        list.add("");
        list.add("string3");
        return list;
    }

    public List<String> postCollection(List<String> list) {
        return list;
    }

    public List<Person> getPersonCollection() {
        List<Person> people = new ArrayList<Person>();
        Person p = new Person();
        p.setFirst("first1");
        p.setLast("last1");
        people.add(p);
        p = new Person();
        p.setFirst("first2");
        p.setLast("last2");
        people.add(p);
        p = new Person();
        p.setFirst("first3");
        p.setLast("last3");
        people.add(p);
        return people;
    }

    public List<Person> postPeopleCollection(List<Person> people) {
        return people;
    }

    public String[] getArray() {
        String[] list = new String[4];
        list[0] = "string1";
        list[1] = "";
        list[2] = null;
        list[3] = "string4";
        return list;
    }

    public String[] postArray(String[] list) {
        return list;
    }

    public Person[] getPeopleArray() {
        Person[] people = new Person[3];
        Person p = new Person();
        p.setFirst("first1");
        p.setLast("last1");
        people[0] = p;
        p = new Person();
        p.setFirst("first2");
        p.setLast("last2");
        people[1] = p;
        p = new Person();
        p.setFirst("first3");
        p.setLast("last3");
        people[2] = p;
        return people;
    }

    public Person[] postPeopleArray(Person[] people) {
        return people;
    }

    public List<List<Person>> getCollectionofCollection() {
        List<List<Person>> peopleCollection = new ArrayList<List<Person>>();

        List<Person> people = new ArrayList<Person>();
        Person p = new Person();
        p.setFirst("first1");
        p.setLast("last1");
        people.add(p);
        p = new Person();
        p.setFirst("first2");
        p.setLast("last2");
        people.add(p);
        p = new Person();
        p.setFirst("first3");
        p.setLast("last3");
        people.add(p);
        peopleCollection.add(people);

        people = new ArrayList<Person>();
        p = new Person();
        p.setFirst("first4");
        p.setLast("last4");
        people.add(p);
        people.add(null);
        p = new Person();
        p.setFirst("first6");
        p.setLast("last6");
        people.add(p);
        peopleCollection.add(people);

        return peopleCollection;
    }

    public List<List<Person>> postCollectionofCollection(List<List<Person>> peopleCollection) {
        return peopleCollection;
    }

    public List<Person[]> getCollectionofArray() {
        List<Person[]> peopleCollection = new ArrayList<Person[]>();

        List<Person> people = new ArrayList<Person>();
        Person p = new Person();
        p.setFirst("first1");
        p.setLast("last1");
        people.add(p);
        p = new Person();
        p.setFirst("first2");
        p.setLast("last2");
        people.add(p);
        p = new Person();
        p.setFirst("first3");
        p.setLast("last3");
        people.add(p);
        peopleCollection.add(people.toArray(new Person[] {}));

        people = new ArrayList<Person>();
        p = new Person();
        p.setFirst("first4");
        p.setLast("last4");
        people.add(p);
        people.add(null);
        p = new Person();
        p.setFirst("first6");
        p.setLast("last6");
        people.add(p);
        peopleCollection.add(people.toArray(new Person[] {}));

        return peopleCollection;
    }

    public List<Person[]> postCollectionofArray(List<Person[]> peopleCollection) {
        return peopleCollection;
    }

}
