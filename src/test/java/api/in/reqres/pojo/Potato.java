package api.in.reqres.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import utils.DateDeserializer;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Potato {
    private String name;
    private String id;
    private String job;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createdAt;

    public Potato(String name, String id, String job, LocalDateTime createdAt) {
        this.name = name;
        this.id = id;
        this.job = job;
        this.createdAt = createdAt;
    }

    public Potato() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Potato)) return false;
        Potato potato = (Potato) o;
        return name.equals(potato.name) && id.equals(potato.id) && Objects.equals(job, potato.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, job);
    }
}
