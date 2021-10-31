package com.example.dimaandorandroid;

public class CountryList {

    private String[] capitals;

    CountryList(){
        capitals = new String[] {
                "Israel","Spain", "Turkey",
                "France",
                "Afghanistan", "Austria", "Azerbaijan"
                ,"Bulgaria",
                "Canada","China",
                "Croatia","Cuba","Cyprus",
                "Czech Republic", "Denmark","Egypt",
                "Germany", "Greece", "Iran (Islamic Republic of)",
                "Iraq",	"Ireland","Italy",
                "Japan","Jordan","Lebanon",
                "Argentina","Belgium"



        };
    }

    public String getCapitalByLevel(int level) {
        return capitals[level];
    }

}
