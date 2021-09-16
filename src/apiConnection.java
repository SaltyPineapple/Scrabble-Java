import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class apiConnection {

    public boolean api(String word){
        System.out.println(word);
        HttpClient client = HttpClient.newHttpClient();

        //build request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format("https://wordsapiv1.p.rapidapi.com/words/%s",word)))
                .header("x-rapidapi-key", "de4ddcec3fmsh8fdbc2eee30df00p13e9a3jsn6ac2c2d3a074")
                .header("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        // send async request using client
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(apiConnection::parse)
                .join();
    }

    public static boolean parse(String responseBody){
        JSONObject wordResponse = new JSONObject(responseBody);

        try{
            String word = (String) wordResponse.get("word");
            JSONArray results = (JSONArray) wordResponse.get("results");
            for(int x=0; x<results.length(); x++){
                JSONObject jo = results.getJSONObject(x);
                String def = jo.getString("definition");
                //System.out.println(def);
            }

            return true;

        }
        catch(JSONException o){
            System.out.println("Not a valid word");
            return false;
        }




    }

}
