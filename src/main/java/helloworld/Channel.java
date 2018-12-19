package helloworld;

public class Channel {

    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Channel() {
    }

    public Channel(Article article) {
        this.article = article;
    }

}