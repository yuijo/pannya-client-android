package jp.s5r.client.pannya.models;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Network {
  @JsonKey
  private String name;

  public Network() {
  }

  public Network(final String name) {
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
