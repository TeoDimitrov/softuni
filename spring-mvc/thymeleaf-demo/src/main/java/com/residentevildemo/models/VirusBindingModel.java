package com.residentevildemo.models;
import com.residentevildemo.annotations.IsInTheFuture;
import com.residentevildemo.entities.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class VirusBindingModel {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, max = 30, message = "Invalid Name size")
    private String name;

    @NotBlank(message = "Cannot be vlank")
    @Size(min = 5, max = 100, message = "Invalid Desc size")
    private String description;

    @Pattern(regexp = "^.*[Cc]orp.*$", message = "Doesnt contain Corp")
    private String creator;

    @Size(max = 50)
    private String sideEffects;

    private boolean isDeadly;

    private boolean isCurable;

    @NotNull(message = "Should have a mutation")
    private Mutation mutation;

    @Range(min = 0, max =100)
    private double turnoverRate;

    @Range(min = 0, max = 12)
    private int hoursToTurn;

    @NotNull(message = "Cannot be null")
    private Magnitude magnitude;

    @IsInTheFuture(message = "Is in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releasedOn;

    @NotEmpty(message = "Should pick capitals")
    private String[] capitals;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
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

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public int getHoursToTurn() {
        return hoursToTurn;
    }

    public void setHoursToTurn(int hoursToTurn) {
        this.hoursToTurn = hoursToTurn;
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

    public String[] getCapitals() {
        return capitals;
    }

    public void setCapitals(String[] capitals) {
        this.capitals = capitals;
    }
}
