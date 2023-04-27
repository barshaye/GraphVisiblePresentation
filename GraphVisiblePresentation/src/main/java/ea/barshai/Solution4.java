package ea.barshai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Solution4 {

  public static MyTree solve() {
    //read the tree from STDIN and return its root as a return value of this function
    try {
      System.setIn(new FileInputStream(new File("C:\\JavaProjects\\GraphVisiblePresentation\\GraphVisiblePresentation\\src\\main\\resources\\HakerRank_testCase10.txt")));
      //System.setIn(new FileInputStream(new File("C:\\JavaProjects\\GraphVisiblePresentation\\GraphVisiblePresentation\\src\\main\\resources\\HakerRank_testCase8.txt")));
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
    MyTree.Color[] colors = new MyTree.Color[colorsStr.length];
    for (int i = 0; i < colorsStr.length; i++) {
      colors[i] = (colorsStr[i].equals("0")) ? MyTree.Color.RED : MyTree.Color.GREEN;
    }

    List<Pair> links = new ArrayList<>(nodesCount);
    while (in.hasNextLine()) {
      String[] s = in.nextLine().split("\\s");
      Integer left = Integer.valueOf(s[0]);
      Integer right = Integer.valueOf(s[1]);
      links.add(new Pair(left, right));
    }

    Map<Integer, Set<Integer>> adges = new HashMap<>();
    processAdges(nodesCount, links, adges);

    MyTree root = new MyTree(values[0], colors[0], 0, 1);
    processChildren(nodesCount, values, colors, adges, root, 1);
    afterProcessAdges(root, links, adges);
    return root;
  }

  private static void processAdges(int nodesCount, List<Pair> links, Map<Integer, Set<Integer>> adges) {
    for (int i = 1; i<=nodesCount; i++) {
      adges.put(i, new HashSet<Integer>());
      for (Pair p:links) {
        if (p.left== i) {
          adges.get(i).add(p.right);
        }
        if (p.right==i) {
          adges.get(i).add(p.left);
        }
      }
    }
  }

  private static void afterProcessAdges(MyTree root, List<Pair> links, Map<Integer, Set<Integer>> adges) {
    // looking for lost nodes
    Set<Integer> lostList = new TreeSet<>();
    for (Pair p : links) {
      Integer left = p.left;
      Integer right = p.right;
      boolean isLeftInTree = treeHasNode(root, left);
      boolean isRightInTree = treeHasNode(root, right);
      if (!isLeftInTree) {
        lostList.add(left);
      }
      if (!isRightInTree) {
        lostList.add(right);
      }
    }
    for (Integer lost :lostList) {
      System.out.println(lost);
    }
  }

  private static boolean treeHasNode(MyTree root, int nodeIndex) {
    if (nodeIndex == root.getIndex()) return true;
    return root.getChildren().stream().anyMatch(ch -> treeHasNode(ch, nodeIndex));
  }
  static List<Integer> processed = new ArrayList<>();
  private static void processChildren(int nodesCount, Integer[] values, MyTree.Color[] colors, Map<Integer, Set<Integer>> adges, MyTree parentNode,
      int parentIndex) {
    if (parentIndex > nodesCount) {
      return;
    }
    if(!processed.contains(parentIndex)) {
      processed.add(parentIndex);
      Set<Integer> children = adges.get(parentIndex);
      for (Integer ch : children) {
        adges.get(ch).remove(parentIndex);
        boolean hasChildren = adges.get(ch).size() > 0;
        MyTree node;
        if (hasChildren) {
          node = new MyTree(values[ch - 1], colors[ch - 1], parentNode.getDepth() + 1, ch);
          processChildren(nodesCount, values, colors, adges, node, ch);
        } else {
          node = new MyTreeLeaf(values[ch - 1], colors[ch - 1], parentNode.getDepth() + 1, ch);
        }
        parentNode.addChild(node);
      }
    }
  }

  public static void main(String[] args) {
    MyTree root = solve();
    printAsGraph(root);
  }

  public static void printAsGraph(MyTree root) {
    String styleSheet =
        "node {" +
            "	text-alignment: right;" +
            "	text-offset: 10px, 0px;" +
            "   size: 5px, 5px;" +
            "}" +
            "node.marked {" +
            "	fill-color: red;" +
            "}";

    System.setProperty("org.graphstream.ui", "swing");
    Graph graph = new SingleGraph("Binary Search Tree");
    graph.setAttribute("ui.stylesheet", styleSheet);

    printAsGraph(root, graph);

    Viewer viewer = graph.display();
    viewer.disableAutoLayout();
  }

  private static void printAsGraph(MyTree x, Graph graph) {
    if (x == null) {
      return;
    }

    addNodesToGraph(x, graph);
    //addAdgesToGraph(x, graph);
  }

  private static void addNodesToGraph(MyTree x, Graph graph) {
    org.graphstream.graph.Node n = graph.addNode(String.valueOf(x.getIndex()));
    // print keys as labels if the graph is reasonably small
    n.setAttribute("ui.label", n.getId());

    // plot the depth and rank as x,y coordinates
    n.setAttribute("x", x.getIndex() - 1);  // rank(x.key)
    n.setAttribute("y", x.getDepth() * -1);
    for (MyTree child : x.getChildren()) {
      addNodesToGraph(child, graph);
    }
  }

  private static void addAdgesToGraph(MyTree x, Graph graph) {
    for (MyTree child : x.getChildren()) {
      int from = x.getIndex();
      int to = child.getIndex();
      String adgeKey = String.valueOf(from).concat(" ").concat(String.valueOf(to));
      try {
        graph.addEdge(adgeKey, from - 1, to - 1, true);
      } catch (Exception e) {
        e.printStackTrace();
      }
      addAdgesToGraph(child, graph);
    }
  }
}
