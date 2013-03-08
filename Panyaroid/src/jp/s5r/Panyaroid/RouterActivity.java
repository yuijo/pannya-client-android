package jp.s5r.Panyaroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * 最初に呼び出される Activity だが特に表示したりはしない.
 */
public class RouterActivity extends Activity {
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }
}
