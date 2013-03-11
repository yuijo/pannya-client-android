package jp.s5r.client.pannya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import jp.s5r.client.pannya.models.Content;
import jp.s5r.client.pannya.models.Message;

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
    listMessage.setAdapter(new MessageAdapter(messages));
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
      Content content = message.getContent();

      TextView name = (TextView) view.findViewById(R.id.chat_message_name);
      name.setText(content.getUser().getName());
      TextView body = (TextView) view.findViewById(R.id.chat_message_body);
      body.setText(content.getText());

      return view;
    }
  }
}
