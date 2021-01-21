package wsstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.net.URISyntaxException;

public class WSSTest {

  private static final Logger logger = LoggerFactory.getLogger(WSSTest.class);

  final static String url = "<REPLACE_ME>";

  final static String  msg = "[\n" +
      "      2,\n" +
      "      \"QQnxlRKL1xiTHrySKd7nputFK6dJBetMEk0C\",\n" +
      "      \"BootNotification\",\n" +
      "  {\n" +
      "    \"chargePointVendor\": \"Vendor\",\n" +
      "      \"chargePointModel\": \"model\",\n" +
      "      \"chargePointSerialNumber\": \"serial-number\",\n" +
      "      \"chargeBoxSerialNumber\": \"box-serial-number\",\n" +
      "      \"firmwareVersion\": \"0.0.1\",\n" +
      "      \"iccid\": \"iccid\",\n" +
      "      \"imsi\": \"imsi\",\n" +
      "      \"meterType\": \"meter-type\",\n" +
      "      \"meterSerialNumber\": \"001.13.1.01\"\n" +
      "  }\n" +
      "]";

  public static void main(String[] args) {
    System.getProperties().put("javax.net.debug", "all");
    try {
      logger.info("Example log from {}", WSSTest.class.getSimpleName());
      // open websocket
      final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(url));
      //  add listener
      clientEndPoint.addMessageHandler(message -> {
        logger.info(message);
      });
      clientEndPoint.sendMessage(msg);
      Thread.sleep(5000);
    } catch (InterruptedException | URISyntaxException ex) {
      System.err.println("Exception exception: " + ex.getMessage());
    }
  }
}
