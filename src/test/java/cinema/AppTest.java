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
    items.get(0).setDescription("<p>Action (02h24min) - Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.</p><p>Un film de <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'>James Wan</a><br>Avec <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'>Jason Momoa</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'>Amber Heard</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'>Willem Dafoe</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'>Patrick Wilson</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'>Nicole Kidman</a></p><p><a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\">Presse</a> : <a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"><img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /></a> - <a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\">Spectateurs</a> : <a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"><img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /></a></p><p>>> <a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\">Fiche complète du film</a> | <a href=\"http://www.allocine.fr/seance/film-208692/\">Séances des 791 cinémas</a> | <a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\">Bandes-annonces</a> | <a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\">Photos</a> | sur <i><a href=\"http://www.allocine.fr\">AlloCiné</a></i></p><img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/>");
    items.get(1).setTitle("Le Retour de Mary Poppins");
    items.get(1).setDescription("<p>Comédie musicale (02h11min) - Michael et Jane sont désormais adultes. Michael vit sur Cherry Tree Lane avec ses trois enfants et leur gouvernante, Ellen. Lorsque celui-ci perd un proche, Mary Poppins, l’énigmatique nounou, réapparaît dans la vie de la famille Banks.</p><p>Un film de <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=69756.html' title='Rob Marshall'>Rob Marshall</a><br>Avec <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=123054.html' title='Emily Blunt'>Emily Blunt</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=265771.html' title='Lin-Manuel Miranda'>Lin-Manuel Miranda</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=100662.html' title='Ben Whishaw'>Ben Whishaw</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=35010.html' title='Emily Mortimer'>Emily Mortimer</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=10044.html' title='Julie Walters'>Julie Walters</a></p><p><a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\">Presse</a> : <a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\"><img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /></a> - <a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\">Spectateurs</a> : <a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\"><img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /></a></p><p>>> <a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=240987.html\">Fiche complète du film</a> | <a href=\"http://www.allocine.fr/seance/film-240987/\">Séances des 986 cinémas</a> | <a href=\"http://www.allocine.fr/video/player_gen_cmedia=19580182&amp;cfilm=240987.html\">Bandes-annonces</a> | <a href=\"http://www.allocine.fr/film/fichefilm-240987/photos/\">Photos</a> | sur <i><a href=\"http://www.allocine.fr\">AlloCiné</a></i></p><img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/c6YbbUvcIFs\" height=\"1\" width=\"1\" alt=\"\"/>");
    rss.getChannel().setItems(items);

    List<Film> films = app.parseFilms(rss);

    Assert.assertEquals("Aquaman", films.get(0).getTitre());
    Assert.assertEquals("02h24min", films.get(0).getDuree());
    Assert.assertEquals("Action", films.get(0).getCategorie());
    Assert.assertEquals( "<p>Les origines d’un héros malgré lui, dont le destin est d’unir deux mondes opposés, la terre et la mer. Cette histoire épique est celle d’un homme ordinaire destiné à devenir le roi des Sept Mers.</p><p>Un film de <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=97569.html' title='James Wan'>James Wan</a><br>Avec <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=105127.html' title='Jason Momoa'>Jason Momoa</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=144088.html' title='Amber Heard'>Amber Heard</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=4788.html' title='Willem Dafoe'>Willem Dafoe</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=86535.html' title='Patrick Wilson'>Patrick Wilson</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=15760.html' title='Nicole Kidman'>Nicole Kidman</a></p><p><a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\">Presse</a> : <a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/presse/\"><img border=\"0\" src=\"http://fr.web.img3.acsta.net/commons/rss/note_small_25.png\" alt=\"2.5\" /></a> - <a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\">Spectateurs</a> : <a href=\"http://www.allocine.fr/film/fichefilm-208692/critiques/spectateurs/\"><img border=\"0\" src=\"http://fr.web.img6.acsta.net/commons/rss/note_small_40.png\" alt=\"4\" /></a></p><p>>> <a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=208692.html\">Fiche complète du film</a> | <a href=\"http://www.allocine.fr/seance/film-208692/\">Séances des 791 cinémas</a> | <a href=\"http://www.allocine.fr/video/player_gen_cmedia=19581227&amp;cfilm=208692.html\">Bandes-annonces</a> | <a href=\"http://www.allocine.fr/film/fichefilm-208692/photos/\">Photos</a> | sur <i><a href=\"http://www.allocine.fr\">AlloCiné</a></i></p><img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/g8UUHUKbhGg\" height=\"1\" width=\"1\" alt=\"\"/>", films.get(0).getDescription());

    Assert.assertEquals("02h11min", films.get(1).getDuree());
    Assert.assertEquals("Comédie musicale", films.get(1).getCategorie());
    Assert.assertEquals("<p>Michael et Jane sont désormais adultes. Michael vit sur Cherry Tree Lane avec ses trois enfants et leur gouvernante, Ellen. Lorsque celui-ci perd un proche, Mary Poppins, l’énigmatique nounou, réapparaît dans la vie de la famille Banks.</p><p>Un film de <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=69756.html' title='Rob Marshall'>Rob Marshall</a><br>Avec <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=123054.html' title='Emily Blunt'>Emily Blunt</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=265771.html' title='Lin-Manuel Miranda'>Lin-Manuel Miranda</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=100662.html' title='Ben Whishaw'>Ben Whishaw</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=35010.html' title='Emily Mortimer'>Emily Mortimer</a>, <a href='http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=10044.html' title='Julie Walters'>Julie Walters</a></p><p><a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\">Presse</a> : <a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/presse/\"><img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /></a> - <a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\">Spectateurs</a> : <a href=\"http://www.allocine.fr/film/fichefilm-240987/critiques/spectateurs/\"><img border=\"0\" src=\"http://fr.web.img4.acsta.net/commons/rss/note_small_35.png\" alt=\"3.5\" /></a></p><p>>> <a href=\"http://www.allocine.fr/film/fichefilm_gen_cfilm=240987.html\">Fiche complète du film</a> | <a href=\"http://www.allocine.fr/seance/film-240987/\">Séances des 986 cinémas</a> | <a href=\"http://www.allocine.fr/video/player_gen_cmedia=19580182&amp;cfilm=240987.html\">Bandes-annonces</a> | <a href=\"http://www.allocine.fr/film/fichefilm-240987/photos/\">Photos</a> | sur <i><a href=\"http://www.allocine.fr\">AlloCiné</a></i></p><img src=\"http://feeds.feedburner.com/~r/ac/cine/cettesemaine/~4/c6YbbUvcIFs\" height=\"1\" width=\"1\" alt=\"\"/>", films.get(1).getDescription());
  }
}
