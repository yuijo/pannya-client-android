package jp.s5r.client.pannya.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import jp.s5r.client.pannya.models.Message;
import jp.s5r.client.pannya.models.MessageGenerated;
import net.vvakame.util.jsonpullparser.JsonFormatException;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MessageReceiveService
    extends Service
    implements WebSocket.OnTextMessage {

  private static final URI DEFAULT_SERVER_URI =
      URI.create("ws://localhost:4000");

  private final IBinder mBinder = new LocalBinder();

  private Connection mConnection = null;

  private HashMap<String, OnMessageListener> mOnMessageListenerHashMap =
      new HashMap<String, OnMessageListener>();

  public interface OnMessageListener {
    void onMessage(final Message message);
  }

  public class LocalBinder extends Binder {
    public MessageReceiveService getService() {
      return MessageReceiveService.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    startService(new Intent(getApplicationContext(), getClass()));
  }

  public void registerOnMessageListener(final String identify,
                                        final OnMessageListener listener) {
    mOnMessageListenerHashMap.put(identify, listener);
  }

  public boolean unregisterOnMessageListener(final String identify) {
    if (mOnMessageListenerHashMap.containsKey(identify)) {
      mOnMessageListenerHashMap.remove(identify);
      return true;
    }
    return false;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  public void openConnection() {
    openConnection(DEFAULT_SERVER_URI);
  }

  public void openConnection(URI uri) {
    closeConnection();

    WebSocketClientFactory factory = new WebSocketClientFactory();
    try {
      factory.start();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    WebSocketClient client = factory.newWebSocketClient();
    try {
      client.open(uri, this);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
  }

  public void closeConnection() {
    if (mConnection != null && mConnection.isOpen()) {
      mConnection.close();
    }
  }

  @Override
  public void onMessage(final String message) {
    Message parsedMessage = null;
    try {
      parsedMessage = MessageGenerated.get(message);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JsonFormatException e) {
      e.printStackTrace();
    }

    if (parsedMessage != null) {
      for (Map.Entry<String, OnMessageListener> entry
          : mOnMessageListenerHashMap.entrySet()) {
        OnMessageListener listener =  entry.getValue();
        listener.onMessage(parsedMessage);
      }
    }
  }

  @Override
  public void onOpen(final Connection connection) {
    mConnection = connection;
  }

  @Override
  public void onClose(final int closeCode, final String message) {
    mConnection = null;
  }
}
