package jp.s5r.Panyaroid.model.miu;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Content {
  @JsonKey(value = "room")
  private Room mRoom;

  @JsonKey(value = "user")
  private User mUser;

  @JsonKey(value = "text")
  private String mText;

  public Content(final Room room, final User user, final String text) {
    setRoom(room);
    setUser(user);
    setText(text);
  }

  public String getText() {
    return mText;
  }

  public void setText(final String text) {
    mText = text;
  }

  public User getUser() {
    return mUser;
  }

  public void setUser(final User user) {
    mUser = user;
  }

  public Room getRoom() {
    return mRoom;
  }

  public void setRoom(final Room room) {
    mRoom = room;
  }
}
