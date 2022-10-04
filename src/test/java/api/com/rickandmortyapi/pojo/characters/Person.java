package api.com.rickandmortyapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.stream.Location;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    public Integer id;
    public String name;
    public Object location;
//    public ArrayList<String> episode;
    public String url;
    public Date created;

    public Person(Integer id, String name, String location, String url, Date created) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.url = url;
        this.created = created;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
