package jp.s5r.client.pannya.models;

public enum Type {
  TEXT;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
