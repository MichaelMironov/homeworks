package api.com.rickandmortyapi.pojo.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private Integer id;
    private String name;
    private String species;
    private Location location;
    private String url;
    private Date created;
    private ArrayList<String> episode;

    public Person(Integer id, String name, String species, Location location, String url, Date created, ArrayList<String> episode) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.location = location;
        this.url = url;
        this.created = created;
        this.episode = episode;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getLocation() {
        return location.name;
    }

    public void setLocation(Location location) {
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

    public ArrayList<String> getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList<String> episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "Информация о персонаже: \n" +
                "id: "+ id +"\n" +
                "Имя: "+ name +"\n" +
                "Раса: "+ species +"\n"+
                "Местонахождение "+ location.name +"\n"+
                "Появление в эпизодах: "+ episode;
    }
}
