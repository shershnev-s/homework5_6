package by.tut.shershnev_s.repository.model;

public class User {

    private int id;
    private String username;
    private String password;
    private boolean isActive;
    private int userGroupID;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean getisActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public int getUserGroupID() {
        return userGroupID;
    }

    public void setUserGroupID(int userGroupID) {
        this.userGroupID = userGroupID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
