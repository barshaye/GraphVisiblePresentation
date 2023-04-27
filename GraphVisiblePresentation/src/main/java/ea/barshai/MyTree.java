package ea.barshai;

import java.util.ArrayList;
import java.util.List;

public class MyTree {

  enum Color {
    RED, GREEN
  }

  private final int value;
  private final Color color;
  private final int depth;
  private final int index;
  private final List<MyTree> children = new ArrayList<>();

  public MyTree(int value, Color color, int depth, int index) {
    this.value = value;
    this.color = color;
    this.depth = depth;
    this.index = index;
  }

  public List<MyTree> getChildren() {
    return children;
  }

  public void addChild(MyTree child) {
    children.add(child);
  }

  public int getIndex() {
    return index;
  }

  public int getValue() {
    return value;
  }

  public Color getColor() {
    return color;
  }

  public int getDepth() {
    return depth;
  }
}
