package jp.s5r.client.pannya;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RouterActivityTest {
  @Test
  public void shouldHaveHappySmiles() throws Exception {
    String appName = new RouterActivity().getResources().getString(R.string.app_name);
    assertThat(appName, equalTo("Panyaroid"));
  }
}
