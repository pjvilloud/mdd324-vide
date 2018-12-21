package cinema;

import cinema.App;
import cinema.GatewayResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    List<Film> result = (List<Film>) app.handleRequest(null, null);
    Assert.assertTrue(result != null);
  }

  @Test
  public void rssParsedFromUrl() throws MalformedURLException {
    App app = new App();

    Rss rss = app.parseRssFromUrl(new URL("http://rss.allocine.fr/ac/cine/cettesemaine?format=xml"));

    Assert.assertNotNull("No RSS found", rss);
    Assert.assertNotNull("No channel found",rss.getChannel());
    Assert.assertNotNull( "No film List found",rss.getChannel().getItems());
    Assert.assertNotNull("No film in list",rss.getChannel().getItems().get(0));
    Assert.assertEquals(cinema.Item.class, rss.getChannel().getItems().get(0).getClass());
  }

  @Test
  public void filmsParsedFromRss() {
    App app = new App();

    Rss rss = new Rss();
    rss.setChannel(new Channel());
    List<Item> items = new ArrayList<>();
    items.add(new Item());
    items.add(new Item());
    items.get(0).setTitle("Aquaman");
    items.get(0).setDescription("&lt;p&gt;Action (02h24min) - Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'&gt;James Wan&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'&gt;Jason Momoa&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'&gt;Amber Heard&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'&gt;Willem Dafoe&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'&gt;Patrick Wilson&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'&gt;Nicole Kidman&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-208692/\"&gt;Séances des 791 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/&gt;");
    items.get(1).setTitle("Le Retour de Mary Poppins");
    items.get(1).setDescription("&lt;p&gt;Comédie musicale (02h11min) - Michael et Jane sont désormais adultes. Michael vit sur Cherry Tree Lane avec ses trois enfants et leur gouvernante, Ellen. Lorsque celui-ci perd un proche, Mary Poppins, l’énigmatique nounou, réapparaît dans la vie de la famille Banks.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=69756.html' title='Rob Marshall'&gt;Rob Marshall&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=123054.html' title='Emily Blunt'&gt;Emily Blunt&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=265771.html' title='Lin-Manuel Miranda'&gt;Lin-Manuel Miranda&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=100662.html' title='Ben Whishaw'&gt;Ben Whishaw&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=35010.html' title='Emily Mortimer'&gt;Emily Mortimer&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=10044.html' title='Julie Walters'&gt;Julie Walters&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=240987.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-240987/\"&gt;Séances des 986 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19580182&amp;cfilm=240987.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/c6YbbUvcIFs\" height=\"1\" width=\"1\" alt=\"\"/&gt;");
    rss.getChannel().setItems(items);

    List<Film> films = app.parseFilms(rss);

    //Film aquaman = new Film("Aquaman", "<description>&lt;p&gt;Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'&gt;James Wan&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'&gt;Jason Momoa&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'&gt;Amber Heard&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'&gt;Willem Dafoe&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'&gt;Patrick Wilson&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'&gt;Nicole Kidman&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-208692/\"&gt;Séances des 791 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/&gt;</description>","02h24min", "Action");
    /*
    aquaman.setCategorie("Action");
    aquaman.setDuree("02h24min");
    aquaman.setTitre("Aquaman");
    aquaman.setDecription("<description>&lt;p&gt;Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'&gt;James Wan&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'&gt;Jason Momoa&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'&gt;Amber Heard&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'&gt;Willem Dafoe&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'&gt;Patrick Wilson&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'&gt;Nicole Kidman&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-208692/\"&gt;Séances des 791 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/&gt;</description>");
    */
    //films.add(aquaman);
    Assert.assertEquals("Aquaman", films.get(0).getTitre());
    Assert.assertEquals("02h24min", films.get(0).getDuree());
    Assert.assertEquals("Action", films.get(0).getCategorie());
    Assert.assertEquals( "&lt;p&gt;Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'&gt;James Wan&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'&gt;Jason Momoa&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'&gt;Amber Heard&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'&gt;Willem Dafoe&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'&gt;Patrick Wilson&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'&gt;Nicole Kidman&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-208692/\"&gt;Séances des 791 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/&gt;", films.get(0).getDecription());

    Assert.assertEquals("02h11min", films.get(1).getDuree());
    Assert.assertEquals("Comédie musicale", films.get(1).getCategorie());
    Assert.assertEquals("&lt;p&gt;Michael et Jane sont désormais adultes. Michael vit sur Cherry Tree Lane avec ses trois enfants et leur gouvernante, Ellen. Lorsque celui-ci perd un proche, Mary Poppins, l’énigmatique nounou, réapparaît dans la vie de la famille Banks.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=69756.html' title='Rob Marshall'&gt;Rob Marshall&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=123054.html' title='Emily Blunt'&gt;Emily Blunt&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=265771.html' title='Lin-Manuel Miranda'&gt;Lin-Manuel Miranda&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=100662.html' title='Ben Whishaw'&gt;Ben Whishaw&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=35010.html' title='Emily Mortimer'&gt;Emily Mortimer&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=10044.html' title='Julie Walters'&gt;Julie Walters&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=240987.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-240987/\"&gt;Séances des 986 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19580182&amp;cfilm=240987.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-240987/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/c6YbbUvcIFs\" height=\"1\" width=\"1\" alt=\"\"/&gt;", films.get(1).getDecription());
  }

  /*
  @Test
  public void filmsParsedFromUrl() throws MalformedURLException {
    App app = new app();

    Rss rss = app.parseRssFromUrl( new URL("http://rss.allocine.fr/ac/cine/cettesemaine?format=xml") );

    String descriptionTest = rss.getChannel().getItems().get(0).getDescription();

    List<Film> films = app.parseFilms(rss);

    Assert.assertEquals();
  }
  */
}
