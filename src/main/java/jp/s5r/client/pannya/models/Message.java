package jp.s5r.client.pannya.models;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Message {
  @JsonKey
  private Network network;

  @JsonKey
  private String type;

  @JsonKey
  private Content content;

  public Message() {
  }

  public Message(final Network network,
                 final Type type,
                 final Content content) {
    setNetwork(network);
    setType(type);
    setContent(content);
  }

  public Network getNetwork() {
    return network;
  }

  public void setNetwork(final Network network) {
    this.network = network;
  }

  public Type getType() {
    return Type.valueOf(type.toUpperCase());
  }

  public void setType(final Type type) {
    setType(type.toString());
  }

  public void setType(final String type) {
    this.type = type;
  }

  public Content getContent() {
    return content;
  }

  public void setContent(final Content content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "network: " + getNetwork().toString()
        + ", type: " + getType().toString()
        + ", content" + getContent().toString();
  }

  public static class Builder {
    private Network mNetwork;
    private Type mType;
    private Content mContent;

    public Builder() {
    }

    public Builder network(String name) {
      mNetwork = new Network(name);
      return this;
    }

    public Builder type(Type type) {
      mType = type;
      return this;
    }

    public Builder content(Content content) {
      mContent = content;
      return this;
    }

    public Message build() {
      return new Message(mNetwork, mType, mContent);
    }
  }
}
