package jp.s5r.client.pannya.models;

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

  public static class Builder {
    private Room mRoom;
    private User mUser;
    private String mText;

    public Builder() {
    }

    public Builder room(String name) {
      mRoom = new Room(name);
      return this;
    }

    public Builder user(String name) {
      mUser = new User(name);
      return this;
    }

    public Builder text(String text) {
      mText = text;
      return this;
    }

    public Content build() {
      return new Content(mRoom, mUser, mText);
    }
  }
}
