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
public class ChatActivity extends AbstractBaseActivity
    implements ActionBar.TabListener, TextView.OnEditorActionListener {

  private int mCurrentSelectedTabPosition = -1;
  private Fragment mFragment;
  private AQuery mAq;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    setContentView(R.layout.chat);

    final ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    actionBar.addTab(actionBar.newTab()
                              .setText("StyleCube")
                              .setTabListener(this));
    actionBar.addTab(actionBar.newTab()
                              .setText("KING RECORDS")
                              .setTabListener(this));
    actionBar.addTab(actionBar.newTab()
                              .setText("PonyCanyon")
                              .setTabListener(this));
    actionBar.addTab(actionBar.newTab()
                              .setText("Lantis")
                              .setTabListener(this));

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

  // ----------------------------
  //  タブの管理
  // ----------------------------

  @Override
  public void onTabSelected(final ActionBar.Tab tab,
                            final FragmentTransaction fragmentTransaction) {
    int tabPosition = tab.getPosition();
    if (mCurrentSelectedTabPosition == tabPosition) {
      return;
    }
    mCurrentSelectedTabPosition = tabPosition;
    mFragment = Fragment.instantiate(this, ChatFragment.class.getName());
    fragmentTransaction.add(R.id.chat_list_container, mFragment);
  }

  @Override
  public void onTabUnselected(final ActionBar.Tab tab,
                              final FragmentTransaction fragmentTransaction) {
    fragmentTransaction.remove(mFragment);
  }

  @Override
  public void onTabReselected(final ActionBar.Tab tab,
                              final FragmentTransaction fragmentTransaction) {
  }
}
