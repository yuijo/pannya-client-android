package jp.s5r.client.pannya.fragments;

import com.androidquery.AQuery;
import jp.s5r.client.pannya.R;
import jp.s5r.client.pannya.models.Content;
import jp.s5r.client.pannya.models.Message;
import jp.s5r.client.pannya.models.Type;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * チャット表示部分.
 */
public class ChatFragment extends Fragment {
  private AQuery mAq;

  @Override
  public View onCreateView(final LayoutInflater inflater,
                           final ViewGroup container,
                           final Bundle savedInstanceState) {
    final View view =
        inflater.inflate(R.layout.chat_fragment, container, false);

    mAq = new AQuery(getActivity(), view);

    ArrayList<Message> messages = new ArrayList<Message>();
    messages.add(new Message.Builder()
                     .network("StyleCube")
                     .type(Type.TEXT)
                     .content(new Content.Builder()
                                  .room("#ShinyBlue")
                                  .user("yuiogura")
                                  .text("今こそ Step in! Shiny Blue!")
                                  .build())
                     .build());
    messages.add(new Message.Builder()
                     .network("StyleCube")
                     .type(Type.TEXT)
                     .content(new Content.Builder()
                                  .room("#ShinyBlue")
                                  .user("yuiogura")
                                  .text("旅立つ鐘鳴らせ!")
                                  .build())
                     .build());

    mAq.id(R.id.chat_list_message).adapter(new MessageAdapter(messages));

    return view;
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
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.chat_message, null);
      }

      Message message = getItem(i);
      Content content = message.getContent();

      AQuery aq = new AQuery(view);
      aq.id(R.id.chat_message_name).text(content.getUser().getName());
      aq.id(R.id.chat_message_body).text(content.getText());
      aq.id(R.id.chat_message_icon).image("https://si0.twimg.com/profile_images/3281723874/24d11457e1fbcebd96b9391d199ba207.png");

      return view;
    }
  }
}
