package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Item implements Serializable {
    private String id;
    private String name;
    private String location;    //file name or Web page
    private Map<String, Object> tags = new HashMap<>();

    public void addTag(String key, Object obj) {
        tags.put(key,obj);
    }

    public void removeTag(String key) {
        tags.remove(key);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }
    public Item(String id, String name, String location) {
        this.id = new String(id);
        this.location = new String(location);
        this.name = new String(name);
    }

    public Item() {}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item) {
            Item other = (Item) obj;
            if(this.name.compareTo(other.name) == 0 && this.id.equals(other.id) && this.location.equals(other.location))
                return true;
        }
        return false;
    }
}
