package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Object, Object> {

    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        JSONObject json = null;
        try {
            json= TestContent.readJsonFromUrl("https://raw.githubusercontent.com/pjvilloud/mdd324-vide/master/qod.json");
            //json = TestContent.readJsonFromUrl("http://quotes.rest/qod.json");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Citation dayQuote = new Citation(json);
        //System.out.println("quote : " + json.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).get("quote"));
        //System.out.println("author : "+ json.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).get("author"));
        //System.out.println(dayQuote.toString());

        try {
            return dayQuote;
        } catch (Exception e) {
            return new GatewayResponse("{}", headers, 500);
        }
    }

    private String getPageContents(String address) throws IOException{
        BufferedReader br = null;
        StringJoiner lines = new StringJoiner(System.lineSeparator());
        try {
            URL url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toString();
    }
}


