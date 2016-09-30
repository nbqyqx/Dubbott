package com.tops001.dubbott.test.json.api.jackson;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
@Path("/person")
public interface JacksonPOJOService {

    @GET
    @Produces("application/json")
    @Path("person")
    public Person getPerson();

    @GET
    @Produces("application/json")
    @Path("person/last/null")
    public Person getPersonWithNullValue();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("person")
    public Person postPerson(Person p);

    @GET
    @Produces("application/json")
    @Path("string")
    public List<String> getCollection();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("string")
    public List<String> postCollection(List<String> list);

    @GET
    @Produces("application/json")
    @Path("personcollect")
    public List<Person> getPersonCollection();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("personcollect")
    public List<Person> postPeopleCollection(List<Person> people);

    @GET
    @Produces("application/json")
    @Path("stringarray")
    public String[] getArray();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("stringarray")
    public String[] postArray(String[] list);

    @GET
    @Produces("application/json")
    @Path("personarray")
    public Person[] getPeopleArray();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("personarray")
    public Person[] postPeopleArray(Person[] people);

    @GET
    @Produces("application/json")
    @Path("collectionofcollection")
    public List<List<Person>> getCollectionofCollection();
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("collectionofcollection")
    public List<List<Person>> postCollectionofCollection(List<List<Person>> peopleCollection);

    @GET
    @Produces("application/json")
    @Path("collectionofarray")
    public List<Person[]> getCollectionofArray();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("collectionofarray")
    public List<Person[]> postCollectionofArray(List<Person[]> peopleCollection);

    public static class Person {
        String first;
        String last;

        public Person(String first, String last) {
        	this.first = first;
        	this.last = last;
        }
        
        public Person() {
        	
        }
        
        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Person))
                return false;
            Person other = (Person) o;
            return this.first.equals(other.first) && this.last.equals(other.last);
        }
    }
}
