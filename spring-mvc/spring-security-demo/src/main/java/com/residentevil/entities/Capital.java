package com.residentevil.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "capitals")
public class Capital {

    @Id
    private long id;

    private String name;

    private float latitude;

    private float longitude;

    @ManyToMany(mappedBy = "capitals")
    private Set<Virus> viruses;

    public Capital() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Set<Virus> getViruses() {
        return viruses;
    }

    public void setViruses(Set<Virus> viruses) {
        this.viruses = viruses;
    }
}
