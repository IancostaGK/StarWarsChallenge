package StarWars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class API_Data {

    static void getApi(ArrayList<String> actors, ArrayList<Integer> actors_moves_count, ArrayList<String> falcon_pilots){
        int error_control = 0, i = 1;
        String output;
        System.out.println("loading...");
        do{
            try {
                HttpURLConnection conn = getHttpURLConnection(i);
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                while ((output = br.readLine()) != null) {
                    ListActors(actors, actors_moves_count, falcon_pilots, output);
                }
                error_control = 0;
                i++;
                conn.disconnect();
            } catch (Exception e) {
                error_control++;
                i++;
            }
        } while(error_control< 5);
        ListPilots(falcon_pilots);
    }

    private static void ListActors(ArrayList<String> actors, ArrayList<Integer> actors_moves_count, ArrayList<String> falcon_pilots, String output) {
        int appears = 0;
        String[] output_list = output.split(",");
        appears = getNumero_de_aparicoes(appears, output_list);
        String actorName = getNomePersongem(output_list[0]);
        actors.add(actorName);
        actors_moves_count.add(appears);
        if (output.contains("https://swapi.dev/api/starships/10/"))
            falcon_pilots.add(actorName);
        System.out.println(actorName + " || appears: " + appears);
    }

    private static void ListPilots(ArrayList<String> falcon_pilots){
        System.out.println("-------------------------");
        System.out.println("Millenium Falcon pilots: ");
        for(String pilot: falcon_pilots){
            System.out.println(pilot);
        }
    }

    private static HttpURLConnection getHttpURLConnection(int i) throws IOException {
        URL url = new URL("https://swapi.dev/api/people/" + i);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }
        return conn;
    }

    private static int getNumero_de_aparicoes(int appears , String[] teste) {
        for (String j : teste) {
            if (j.contains("https://swapi.dev/api/films/"))
                appears ++;
        }
        return appears ;
    }

    private static String getNomePersongem(String palavra) {
        return palavra.split(":")[1].replaceAll("\"", "");
    }
}
