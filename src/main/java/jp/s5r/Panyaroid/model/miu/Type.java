package jp.s5r.Panyaroid.model.miu;

public enum Type {
  TEXT;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
