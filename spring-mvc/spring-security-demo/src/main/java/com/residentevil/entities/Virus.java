package com.residentevil.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "viruses")
public class Virus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "side_effects")
    private String sideEffects;

    @Basic
    private String creator;

    @Column(name = "is_deadly")
    private boolean isDeadly;

    @Column(name = "is_curable")
    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    private Mutation mutation;

    @Column(name = "turnover_rate")
    private float turnoverRate;

    @Column(name = "hours_until_turn")
    private int hoursUntilTurn;

    @Enumerated(EnumType.STRING)
    private Magnitude magnitude;

    @Column(name = "released_on", updatable = false)
    private Date releasedOn;

    @ManyToMany
    @JoinTable(name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id"))
    private Set<Capital> capitals;

    public Virus() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public float getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(float turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public int getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(int hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
