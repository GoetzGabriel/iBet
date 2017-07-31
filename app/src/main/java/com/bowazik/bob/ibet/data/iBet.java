package com.bowazik.bob.ibet.data;

import java.io.Serializable;
import java.util.Date;

/**
 * The iBet data format in which the iBets are saved.
 * It is used to retrieve and the bets from the web server and to send it.
 */

public class iBet implements Serializable{

    private int id, creator, contender, value;
    private String title, description, status, contenderName;
    private Double created;

    public iBet(int id, int creator, int contender, String title, String description, int value, /*Double created,*/ String status, String contenderName){

        this.id = id;
        this.creator = creator;
        this.contender = contender;
        this.title = title;
        this.description = description;
        this.contender = contender;
        this.value = value;
        this.created = created;
        this.status = status;
        this.contenderName = contenderName;
    }

    public iBet(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getContender() {
        return contender;
    }

    public void setContender(int contender) {
        this.contender = contender;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCreated() {
        return created;
    }

    public void setCreated(Double created) {
        this.created = created;
    }

    public String getContenderName() {
        return contenderName;
    }

    public void setContenderName(String contenderName) {
        this.contenderName = contenderName;
    }
}
