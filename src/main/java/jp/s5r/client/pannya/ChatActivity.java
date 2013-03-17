package jp.s5r.client.pannya;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import jp.s5r.client.pannya.fragments.ChatFragment;
import jp.s5r.client.pannya.models.Content;
import jp.s5r.client.pannya.models.Message;
import jp.s5r.client.pannya.models.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * チャット表示部 Activity.
 */
public class ChatActivity
    extends AbstractBaseActivity
    implements TextView.OnEditorActionListener,
               ActionBar.OnNavigationListener {

  private int mSelectedItemPosition = -1;
  private Fragment mFragment;
  private AQuery mAq;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    setContentView(R.layout.chat);

    final ActionBar actionBar = getActionBar();
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        this,
        android.R.layout.simple_list_item_1,
        new String[] {
            "StyleCube", "KING RECORDS", "PonyCanyon",
            "Lants",
        });
    actionBar.setListNavigationCallbacks(adapter, this);

    actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

    mAq = new AQuery(this);
    mAq.id(R.id.chat_send_button).clicked(this, "sendButtonClicked");
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
        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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

  @Override
  public boolean onNavigationItemSelected(final int itemPosition,
                                          final long itemId) {
    if (mSelectedItemPosition == itemPosition) {
      return false;
    }
    mSelectedItemPosition = itemPosition;

    mFragment = new ChatFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.chat_list_container, mFragment);
    transaction.addToBackStack(null);
    transaction.commit();

    return false;
  }
}
