package jp.s5r.Panyaroid.model.miu;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class User {
  @JsonKey(value = "name")
  private String mName;

  public User(final String name) {
    setName(name);
  }

  public String getName() {
    return mName;
  }

  public void setName(final String name) {
    mName = name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
