package jp.s5r.Panyaroid.model.miu;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class User {
  @JsonKey
  private String name;

  public User() {
  }

  public User(final String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
