package jp.s5r.Panyaroid.model.miu;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Content {
  @JsonKey
  private Room room;

  @JsonKey
  private User user;

  @JsonKey
  private String text;

  public Content() {
  }

  public Content(final Room room, final User user, final String text) {
    setRoom(room);
    setUser(user);
    setText(text);
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(final Room room) {
    this.room = room;
  }

  @Override
  public String toString() {
    return "room: " + getRoom().toString()
        + ", user: "+ getUser().toString()
        + ", text: " + getText();
  }
}
