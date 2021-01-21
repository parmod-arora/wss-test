package wsstest;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebsocketClientEndpoint {

  Session session = null;
  private MessageHandler messageHandler;

  public WebsocketClientEndpoint(URI endpointURI) {
    try {
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      container.connectToServer(this, endpointURI);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
  }

  @OnClose
  public void onClose(Session session, CloseReason reason) {
    this.session = null;
  }

  @OnMessage
  public void onMessage(String message) {
    if (this.messageHandler != null) {
      this.messageHandler.handleMessage(message);
    }
  }

  public void addMessageHandler(MessageHandler msgHandler) {
    this.messageHandler = msgHandler;
  }

  public void sendMessage(String message) {
    this.session.getAsyncRemote().sendText(message);
  }

  public static interface MessageHandler {

    public void handleMessage(String message);
  }
}