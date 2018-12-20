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
import java.util.*;

/**
 * Handler for requests to Lambda function.
 */

public class App implements RequestHandler<Object, Object> {

    private Genson genson = new Genson();
    private Date date = new Date(); // your date


    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        try {
            final String pageContents = this.getPageContents("https://raw.githubusercontent.com/theofidry/ephemeris/master/src/ephemeris.json");
            ListeGlobale listeGlobale = genson.deserialize(pageContents, ListeGlobale.class);
            Integer moisNum = month();
            List<List<String>> liste = listSwitch(moisNum, listeGlobale);
            List<String> fete = liste.get(dayMonth()-1);
            String nomFete = fete.get(1)+" "+fete.get(0);
            Ephemeride ephemeride = new Ephemeride(date.toString(), nomFete, dayYear(), 365-dayYear(), week());
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

    public Integer dayMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public Integer dayYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public List<List<String>> listSwitch(int moisNum, ListeGlobale listeGlobale){
        List<List<String>> liste;
        switch (moisNum){
            case 0 :
                liste = listeGlobale.getJanuary();
                return liste;
            case 1 :
                liste = listeGlobale.getFebruary();
                return liste;
            case 2 :
                liste = listeGlobale.getMarch();
                return liste;
            case 3 :
                liste = listeGlobale.getApril();
                return liste;
            case 4 :
                liste = listeGlobale.getMay();
                return liste;
            case 5 :
                liste = listeGlobale.getJune();
                return liste;
            case 6 :
                liste = listeGlobale.getJuly();
                return liste;
            case 7 :
                liste = listeGlobale.getAugust();
                return liste;
            case 8 :
                liste = listeGlobale.getSeptember();
                return liste;
            case 9 :
                liste = listeGlobale.getOctober();
                return liste;
            case 10 :
                liste = listeGlobale.getNovember();
                return liste;
            case 11 :
                liste = listeGlobale.getDecember();
                return liste;
        }
        return null;
    }
}
