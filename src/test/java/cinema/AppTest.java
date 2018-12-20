package cinema;

import cinema.App;
import cinema.GatewayResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    String result = (String) app.handleRequest(null, null);
    Assert.assertEquals("gg", result);
  }
}
