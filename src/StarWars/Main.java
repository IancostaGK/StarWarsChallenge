package StarWars;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> actors = new ArrayList<>();
        ArrayList<Integer> actors_moves_count = new ArrayList<>();
        ArrayList<String> falcon_pilots = new ArrayList<>();
        API_Data.getApi(actors, actors_moves_count, falcon_pilots);
    }
}
