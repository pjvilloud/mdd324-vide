package helloworld;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class Quote {

    JSONObject json = new JSONObject(IOUtils.toString(new URL("http://quotes.rest/qod.json"), Charset.forName("UTF-8")));
    Gson gson = new Gson();
    Citation userObject = gson.fromJson(json.toString(), Citation.class);



    public Quote() throws IOException {
    }
}
