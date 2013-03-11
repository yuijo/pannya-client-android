package jp.s5r.Panyaroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * チャット表示部 Activity.
 */
public class ChatActivity extends AbstractBaseActivity {
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    setContentView(R.layout.chat);

    ListView listMessage = (ListView) findViewById(R.id.chat_list_message);
    ArrayList<Message> messages = new ArrayList<Message>();
    messages.add(new Message("chiwa", "おかえり"));
    messages.add(new Message("masuzu", "さあ出掛けよう"));
    messages.add(new Message("chiwa", "絆が大事"));
    messages.add(new Message("masuzu", "恋人が大事"));
    messages.add(new Message("chiwa", "この痛み、君のせい?"));
    messages.add(new Message("masuzu", "胸のときめき、嘘のつもり"));
    messages.add(new Message("chiwa", "ねえもっと、いっしょしよ?"));
    messages.add(new Message("masuzu", "ダメ、私だけ。"));
    messages.add(new Message("chiwa", "油断もスキもない!"));
    listMessage.setAdapter(new MessageAdapter(messages));
  }

  private static class Message {
    private String mName;
    private String mMessage;

    public Message(final String name, final String message) {
      mName = name;
      mMessage = message;
    }

    public String getName() {
      return mName;
    }

    public String getMessage() {
      return mMessage;
    }
  }

  private class MessageAdapter extends BaseAdapter {
    private List<Message> mMessages;

    public MessageAdapter(final List<Message> messages) {
      mMessages = messages;
    }

    @Override
    public int getCount() {
      return mMessages.size();
    }

    @Override
    public Message getItem(final int i) {
      return mMessages.get(i);
    }

    @Override
    public long getItemId(final int i) {
      return i;
    }

    @Override
    public View getView(final int i,
                        final View convertView,
                        final ViewGroup viewGroup) {
      View view = convertView;
      if (view == null) {
        LayoutInflater inflater = ChatActivity.this.getLayoutInflater();
        view = inflater.inflate(R.layout.chat_message, null);
      }

      Message message = getItem(i);

      TextView name = (TextView) view.findViewById(R.id.chat_message_name);
      name.setText(message.getName());
      TextView body = (TextView) view.findViewById(R.id.chat_message_body);
      body.setText(message.getMessage());

      return view;
    }
  }
}
