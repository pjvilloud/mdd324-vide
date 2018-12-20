package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.owlike.genson.Genson;
import module.Ephemeride;
import module.ListeGlobale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Handler for requests to Lambda function.
 */

public class App implements RequestHandler<Object, Object> {

    private Genson genson = new Genson();
    private Ephemeride ephemeride = new Ephemeride();
    private Date date = new Date(); // your date


    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        try {
            final String pageContents = this.getPageContents("https://raw.githubusercontent.com/theofidry/ephemeris/master/src/ephemeris.json");
            ListeGlobale listeGlobale = genson.deserialize(pageContents, ListeGlobale.class);
            System.out.println(listeGlobale.getApril().get(0).get(0));
            //switch
            Integer moisNum = month();
            List<List<String>> liste;
            switch (moisNum){
                case 0 :
                    liste = listeGlobale.getJanuary();
                    break;
                case 1 :
                    liste = listeGlobale.getFebruary();
                    break;
                case 2 :
                    liste = listeGlobale.getMarch();
                    break;
                case 3 :
                    liste = listeGlobale.getApril();
                    break;
                case 4 :
                    liste = listeGlobale.getMay();
                    break;
                case 5 :
                    liste = listeGlobale.getJune();
                    break;
                case 6 :
                    liste = listeGlobale.getJuly();
                    break;
                case 7 :
                    liste = listeGlobale.getAugust();
                    break;
                case 8 :
                    liste = listeGlobale.getSeptember();
                    break;
                case 9 :
                    liste = listeGlobale.getOctober();
                    break;
                case 10 :
                    liste = listeGlobale.getNovember();
                    break;
                case 11 :
                    liste = listeGlobale.getDecember();
                    break;
            }
            return ephemeride;
        } catch (IOException e) {
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

    public int year() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    public Integer month() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }
    public Integer week() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
}
