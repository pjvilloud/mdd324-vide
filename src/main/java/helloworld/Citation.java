package helloworld;

import org.json.JSONObject;

public class Citation {

    private String quote="";
    private String author="";

    public String createCitation(JSONObject json){
        this.quote = json.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).get("quote").toString();
        this.author = json.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).get("author").toString();
        String result = "{\n \"author\": \""+ this.author +"\" ,\n \"quote\" : \"" + this.quote + " \" \n}";
        return result;


    }

    public Citation(JSONObject json) {
        this.quote = json.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).get("quote").toString();
        this.author = json.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).get("author").toString();
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"author\" : \"" + author + "\"" +
                ",\n\"quote\" : \"" + quote + "\"" +
                "\n}";
    }
}