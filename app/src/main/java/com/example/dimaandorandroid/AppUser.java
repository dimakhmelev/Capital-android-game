package com.example.dimaandorandroid;

public class AppUser implements Comparable<AppUser> {

    private String email;
    private String password;
    private String score;
    private String level;

    public String getEmail() {
        return email;
    }

    public AppUser() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public AppUser(String email, String password, String score, String level) {
        this.email = email;
        this.password = password;
        this.score = score;
        this.level = level;
    }

    @Override
    public int compareTo(AppUser appUser) {
        return Integer.compare(Integer.parseInt(this.getScore()), Integer.parseInt(appUser.getScore()));
    }
}