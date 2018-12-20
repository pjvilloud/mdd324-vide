package cinema;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import helloworld.GatewayResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Object, Object> {

    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Rss.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            URL url = new URL( "http://rss.allocine.fr/ac/cine/cettesemaine?format=xml" );
            Rss rss = (Rss) jaxbUnmarshaller.unmarshal( url );
            List<Film> films = parseFilms(rss);
            return films;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new GatewayResponse("{}", headers, 500);
        }
    }

    public List<Film> parseFilms(Rss rss){
        Channel channel = rss.getChannel();
        List<Film> films = new ArrayList<>();
        List<Item> items = channel.getItems();

        for(Item i : items) {
            String description;
            String duree;
            String categorie;
            String tempDescription = i.getDescription();
            String[] temp = tempDescription.split(" - ");
            description = temp[1] + temp[2];
            temp = temp[0].split(" ");
            categorie = temp[0].split(";")[2];
            duree = temp[1].substring(1, 9);
            films.add(new Film(i.getTitle(), description, categorie, duree));
        }

        return films;
    }
}
