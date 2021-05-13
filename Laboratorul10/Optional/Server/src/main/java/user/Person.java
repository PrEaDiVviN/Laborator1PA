package user;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String username;
    private String password;
    private List<String> friendList;
    boolean online;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        friendList = new ArrayList<>();
        this.online = false;
    }

    public Person(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person) {
            Person personObj = (Person) obj;
            if(personObj.username.compareTo(this.username) == 0)
                return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
