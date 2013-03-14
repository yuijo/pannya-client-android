package jp.s5r.client.pannya;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import jp.s5r.client.pannya.models.Content;
import jp.s5r.client.pannya.models.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * チャット表示部 Activity.
 */
public class ChatActivity
    extends AbstractBaseActivity
    implements TextView.OnEditorActionListener {
  private AQuery mAq;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    setContentView(R.layout.chat);

    mAq = new AQuery(this);

    ArrayList<Message> messages = new ArrayList<Message>();
    mAq.id(R.id.chat_send_button).clicked(this, "sendButtonClicked");
    mAq.id(R.id.chat_list_message).adapter(new MessageAdapter(messages));
    mAq.id(R.id.chat_input_message)
       .getEditText()
       .setOnEditorActionListener(this);
  }

  @SuppressWarnings("unused")
  public void sendButtonClicked(final View button) {
    sendMessage();
    hideSoftInputFromWindow(mAq.id(R.id.chat_input_message).getEditText());
  }

  private void hideSoftInputFromWindow(final TextView textView) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

    inputMethodManager.hideSoftInputFromWindow(
        textView.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    inputMethodManager.hideSoftInputFromWindow(
        textView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
  }

  @Override
  public boolean onEditorAction(final TextView textView,
                                final int actionId,
                                final KeyEvent keyEvent) {
    if (keyEvent == null) {
      return false;
    }

    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
      sendMessage();
      hideSoftInputFromWindow(textView);
      return true;
    }

    return false;
  }

  private void sendMessage() {
    String inputMessage = mAq.id(R.id.chat_input_message)
                             .getEditable()
                             .toString();
    if (inputMessage == null || inputMessage.equals("")) {
      inputMessage = "input message";
    }
    Toast.makeText(this, inputMessage, Toast.LENGTH_SHORT).show();
    mAq.getEditText().setText("");
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
