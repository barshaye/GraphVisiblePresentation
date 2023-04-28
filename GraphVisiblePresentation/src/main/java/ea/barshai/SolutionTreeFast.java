package ea.barshai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

enum Color {
  RED, GREEN
}

abstract class Tree {

  private int value;
  private Color color;
  private int depth;

  public Tree(int value, Color color, int depth) {
    this.value = value;
    this.color = color;
    this.depth = depth;
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

  public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

  private ArrayList<Tree> children = new ArrayList<>();

  public TreeNode(int value, Color color, int depth) {
    super(value, color, depth);
  }

  public void accept(TreeVis visitor) {
    visitor.visitNode(this);

    for (Tree child : children) {
      child.accept(visitor);
    }
  }

  public void addChild(Tree child) {
    children.add(child);
  }
}

class TreeLeaf extends Tree {

  public TreeLeaf(int value, Color color, int depth) {
    super(value, color, depth);
  }

  public void accept(TreeVis visitor) {
    visitor.visitLeaf(this);
  }
}

abstract class TreeVis {

  public abstract int getResult();

  public abstract void visitNode(TreeNode node);

  public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
  int leavesSum = 0;
  public int getResult() {
    //implement this
    return leavesSum;
  }

  public void visitNode(TreeNode node) {
    if ( (((Nodule)node).isLeaf())) {
      leavesSum += node.getValue();
    }
  }

  public void visitLeaf(TreeLeaf leaf) {
  }
}

class ProductOfRedNodesVisitor extends TreeVis {
  final int	M		= 1000000007;
  long redProduct = 1;

  public int getResult() {
    //implement this
    //return redNodes.stream().reduce(1,(x,y)->x*y);

    return  (int)(redProduct ==0 ? 1 : redProduct);
  }
  private void redSum(Tree node) {
    if (Color.RED == node.getColor()) {
      int val = node.getValue();
      redProduct = ( ( redProduct * val ) % M );
    }
  }

  public void visitNode(TreeNode node) {
    redSum(node);
  }

  public void visitLeaf(TreeLeaf leaf) {
    redSum(leaf);
  }
}

class FancyVisitor extends TreeVis {
 int sum = 0;
  public int getResult() {
    //implement this
    return Math.abs(sum);
  }
  boolean isEven(int num) {
    if (num == 0) return true;

    return num%2 == 0;
  }
  public void visitNode(TreeNode node) {
    if (!((Nodule)node).isLeaf() && isEven(node.getDepth()))
      sum += node.getValue();
    else if (((Nodule)node).isLeaf() && node.getColor() == Color.GREEN)
      sum -= node.getValue();
  }

  public void visitLeaf(TreeLeaf leaf) {
  }
}

class Nodule extends TreeNode {

  private boolean _hasChild = false;
  public Nodule(int value, Color color, int depth) {
    super(value, color, depth);
  }
  public boolean isLeaf() {
    return !_hasChild;
  }
  @Override
  public void addChild(Tree child) {
    _hasChild = true;
    super.addChild(child);
  }
}

public class SolutionTreeFast {
  static List<Integer> processed = new ArrayList<>();
  public static Tree solve() {
    //read the tree from STDIN and return its root as a return value of this function
    try {
      //System.setIn(new FileInputStream(new File("C:\\Parma\\Sber\\Test\\resources\\HakerRank_testCase8.txt")));
      //System.setIn(new FileInputStream(new File("C:\\Parma\\Sber\\Test\\resources\\HakerRank_testCase7.txt")));
      System.setIn(new FileInputStream(new File("C:\\Parma\\Sber\\Test\\resources\\HakerRank_testCase11.txt")));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Scanner in = new Scanner(System.in);
    int nodesCount = in.nextInt();
    in.nextLine();
    String[] valuesStr = in.nextLine().split("\\s");
    Integer[] values = new Integer[valuesStr.length];
    for (int i = 0; i < valuesStr.length; i++) {
      values[i] = Integer.parseInt(valuesStr[i]);
    }

    String[] colorsStr = in.nextLine().split("\\s");
    Color[] colors = new Color[colorsStr.length];
    for (int i = 0; i < colorsStr.length; i++) {
      colors[i] = (colorsStr[i].equals("0")) ? Color.RED : Color.GREEN;
    }

    Nodule[] treeNodes = new Nodule[nodesCount];
    Nodule root = new Nodule(values[0], colors[0], 0);
    treeNodes[0] = root;
    LinkedList<int[]> stack = new LinkedList<>();
    while ( in.hasNext() ) {
      int left = in.nextInt();
      int right = in.nextInt();
      stack.add(new int[]{left, right});
    }
    in.close();

    while (stack.size() > 0) {
      int[] elem = stack.poll();
      int u = elem[0] -1;
      int v = elem[1] -1;
      if (treeNodes[u] != null && treeNodes[v] == null) { // v not attached
        treeNodes[v] = new Nodule(values[v], colors[v], treeNodes[u].getDepth() + 1);
        treeNodes[u].addChild(treeNodes[v]);
      }
      else if (treeNodes[u] == null && treeNodes[v] != null) { // u not attached
        treeNodes[u] = new Nodule(values[u], colors[u], treeNodes[v].getDepth() + 1);
        treeNodes[v].addChild(treeNodes[u]);
      }
      else // both nodes not attached to tree; add back to stack
        stack.add(elem);
    }

    return root;
  }

  public static void main(String[] args) {
    Tree root = solve();
    SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
    ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
    FancyVisitor vis3 = new FancyVisitor();

    root.accept(vis1);
    root.accept(vis2);
    root.accept(vis3);

    int res1 = vis1.getResult();
    int res2 = vis2.getResult();
    int res3 = vis3.getResult();

    System.out.println(res1);
    System.out.println(res2);
    System.out.println(res3);
  }
}

/*
На 8-м файле
45136
56618427
14333

На 10-м файле должно быть
407446
195105511
163593

На 11-м
39755013
433579985
14530307
    */


