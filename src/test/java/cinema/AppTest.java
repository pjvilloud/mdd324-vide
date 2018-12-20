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
    items.get(0).setTitle("Aquaman");
    items.get(0).setDescription("<description>&lt;p&gt;Action (02h24min) - Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.&lt;/p&gt;&lt;p&gt;Un film de &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'&gt;James Wan&lt;/a&gt;&lt;br&gt;Avec &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'&gt;Jason Momoa&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'&gt;Amber Heard&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'&gt;Willem Dafoe&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'&gt;Patrick Wilson&lt;/a&gt;, &lt;a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'&gt;Nicole Kidman&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;Presse&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /&gt;&lt;/a&gt; - &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;Spectateurs&lt;/a&gt; : &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"&gt;&lt;img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /&gt;&lt;/a&gt;&lt;/p&gt;&lt;p&gt;&gt;&gt; &lt;a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\"&gt;Fiche complète du film&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/seance/film-208692/\"&gt;Séances des 791 cinémas&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\"&gt;Bandes-annonces&lt;/a&gt; | &lt;a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\"&gt;Photos&lt;/a&gt; | sur &lt;i&gt;&lt;a href=\"http://www.allocine.fr\"&gt;AlloCiné&lt;/a&gt;&lt;/i&gt;&lt;/p&gt;&lt;img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/&gt;</description>");
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


  }
}
