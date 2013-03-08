package jp.s5r.Panyaroid;

import android.os.Bundle;
import android.view.Window;

public class ChatActivity extends AbstractBaseActivity {
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    setContentView(R.layout.chat);
  }
}
