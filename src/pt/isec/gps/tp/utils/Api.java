package pt.isec.gps.tp.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    final static String API_KEY = "AIzaSyCKfh3at5u-PjPnPTBDh3veMxOdpZpOn8I";


    public static float[][] distances;
    public static float[][] times;
    //public static String[] cities = {"", "Bengaluru", "Chennai", "Goa", "Mumbai", "Hyderabad", "Kolkata", "Patna", "Delhi", "Jaipur,+Rajasthan", "Lukhnow"};
    //public static String[] cities = {"", "Lousã", "Poiares", "Serpins", "Coimbra"};
    public static String[] cities = {"Coimbra", "Lousã"};
    public static final int n= cities.length;

    //downloading the data
    public static String getData(String source, String destination) throws Exception {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
//        System.out.println(response);
        return response;

    }

    public static void parse(String response,int i,int j){
        //long distance = -1L;
        //long time = -1L;
        //parsing json data and updating data
        {
            try {


                JsonParser jp = new JsonParser();
                JsonObject jo = (JsonObject) jp.parse(response);
                JsonArray ja = (JsonArray) jo.get("rows");
                jo = (JsonObject) ja.get(0);
                ja = (JsonArray) jo.get("elements");
                jo = (JsonObject) ja.get(0);
                JsonObject je = (JsonObject) jo.get("distance");
                JsonObject jf = (JsonObject) jo.get("duration");
                var distance = je.get("value");
                var time = jf.get("value");

                //distance =  (long )je.get("value");
                //time = (long) jf.get("value");

                distances[i][j] = distance.getAsFloat();
                times[i][j] = time.getAsFloat();

            } catch (Exception e) {
                System.out.println(e + " for " + cities[j]);
            }
        }
    }

    public static void make_text_file() throws FileNotFoundException {
        PrintWriter out =new PrintWriter("distanceMatrix.txt");
        for (int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                out.print(distances[i][j] + " ");
            }
            out.println();
        }
        out.close();
        PrintWriter out2 =new PrintWriter("timeMatrix.txt");
        for (int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                out2.print(times[i][j] + " ");
            }
            out2.println();
        }
        out2.close();
    }
    public float execute(String local1, String local2) throws Exception {
        distances = new float[n][n];
        times = new float[n][n];
        int count=0;
        String response = getData(local1, local2);
        parse(response,0,0);
        System.out.println(response);
        System.out.println("Tempo:"+times[0][0]);
        return times[0][0];
    }


}