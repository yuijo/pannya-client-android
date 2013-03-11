package jp.s5r.client.pannya.models;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Room {
  @JsonKey
  private String name;

  public Room() {
  }

  public Room(final String name) {
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
