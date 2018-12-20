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
import java.util.HashMap;
import java.util.List;
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
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Rss.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            URL url = new URL( "http://rss.allocine.fr/ac/cine/cettesemaine?format=xml" );
            Rss rss = (Rss) jaxbUnmarshaller.unmarshal( url );
            String output = parseFilms(rss);
            return output;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new GatewayResponse("{}", headers, 500);
        }
    }

    public String parseFilms(Rss rss){
        Channel channel = rss.getChannel();
        List<Film> films;
        List<Item> items = channel.getItems();
        return rss.toString();
    }
}
