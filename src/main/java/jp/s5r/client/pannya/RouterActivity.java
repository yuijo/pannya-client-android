package jp.s5r.client.pannya;

import android.content.Intent;
import android.os.Bundle;

/**
 * 最初に呼び出される Activity だが特に表示したりはしない.
 */
public class RouterActivity extends AbstractBaseActivity {
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    startChatActivity();
    finish();
  }

  private void startChatActivity() {
    Intent intent = new Intent(RouterActivity.this, ChatActivity.class);
    startActivity(intent);
  }
}
