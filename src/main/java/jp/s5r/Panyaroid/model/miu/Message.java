package jp.s5r.Panyaroid.model.miu;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Message {
  @JsonKey(value = "network")
  private Network mNetwork;

  @JsonKey(value = "type")
  private String mType;

  @JsonKey(value = "content")
  private Content mContent;

  public Message(final Network network,
                 final Type type,
                 final Content content) {
    setNetwork(network);
    setType(type);
    setContent(content);
  }

  public Network getNetwork() {
    return mNetwork;
  }

  public void setNetwork(final Network network) {
    mNetwork = network;
  }

  public Type getType() {
    return Type.valueOf(mType.toUpperCase());
  }

  public void setType(final Type type) {
    mType = type.toString();
  }

  public Content getContent() {
    return mContent;
  }

  public void setContent(final Content content) {
    mContent = content;
  }
}
