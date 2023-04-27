package ea.barshai;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.Eades84Layout;
import org.graphstream.ui.layout.HierarchicalLayout;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.view.Viewer;

public class Tutorial3 {

  public static void main(String args[]) {
    System.setProperty("org.graphstream.ui", "swing");
    //System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    Graph graph = new MultiGraph ("Bazinga!");
    // Populate the graph.
    Viewer viewer = graph.display();
    // Let the layout work ...
    viewer.disableAutoLayout();
    // Do some work ...
    viewer.enableAutoLayout();

    for (int i =1; i<=100; i++) {
      graph.addNode(String.valueOf(i));

    }
    int j=0;
    graph.addEdge(String.valueOf(j++), "1", "97");
    graph.addEdge(String.valueOf(j++), "1", "4");
    graph.addEdge(String.valueOf(j++), "1", "5");
    graph.addEdge(String.valueOf(j++), "1", "55");
    graph.addEdge(String.valueOf(j++), "1", "72");
    graph.addEdge(String.valueOf(j++), "1", "73");
    graph.addEdge(String.valueOf(j++), "1", "42");
    graph.addEdge(String.valueOf(j++), "1", "75");
    graph.addEdge(String.valueOf(j++), "1", "12");
    graph.addEdge(String.valueOf(j++), "1", "78");
    graph.addEdge(String.valueOf(j++), "1", "48");
    graph.addEdge(String.valueOf(j++), "1", "82");
    graph.addEdge(String.valueOf(j++), "1", "51");
    graph.addEdge(String.valueOf(j++), "1", "83");
    graph.addEdge(String.valueOf(j++), "1", "87");
    graph.addEdge(String.valueOf(j++), "1", "89");
    graph.addEdge(String.valueOf(j++), "1", "90");
    graph.addEdge(String.valueOf(j++), "1", "32");
    graph.addEdge(String.valueOf(j++), "1", "94");
    graph.addEdge(String.valueOf(j++), "1", "70");
    graph.addEdge(String.valueOf(j++), "2", "4");
    graph.addEdge(String.valueOf(j++), "2", "9");
    graph.addEdge(String.valueOf(j++), "2", "10");
    graph.addEdge(String.valueOf(j++), "2", "11");
    graph.addEdge(String.valueOf(j++), "2", "13");
    graph.addEdge(String.valueOf(j++), "2", "15");
    graph.addEdge(String.valueOf(j++), "2", "17");
    graph.addEdge(String.valueOf(j++), "2", "19");
    graph.addEdge(String.valueOf(j++), "2", "20");
    graph.addEdge(String.valueOf(j++), "2", "31");
    graph.addEdge(String.valueOf(j++), "2", "41");
    graph.addEdge(String.valueOf(j++), "2", "44");
    graph.addEdge(String.valueOf(j++), "2", "45");
    graph.addEdge(String.valueOf(j++), "2", "53");
    graph.addEdge(String.valueOf(j++), "2", "61");
    graph.addEdge(String.valueOf(j++), "2", "62");
    graph.addEdge(String.valueOf(j++), "2", "64");
    graph.addEdge(String.valueOf(j++), "2", "66");
    graph.addEdge(String.valueOf(j++), "2", "79");
    graph.addEdge(String.valueOf(j++), "2", "88");
    graph.addEdge(String.valueOf(j++), "2", "92");
    graph.addEdge(String.valueOf(j++), "2", "93");
    graph.addEdge(String.valueOf(j++), "2", "100");
    graph.addEdge(String.valueOf(j++), "3", "33");
    graph.addEdge(String.valueOf(j++), "3", "99");
    graph.addEdge(String.valueOf(j++), "3", "68");
    graph.addEdge(String.valueOf(j++), "3", "5");
    graph.addEdge(String.valueOf(j++), "3", "47");
    graph.addEdge(String.valueOf(j++), "3", "80");
    graph.addEdge(String.valueOf(j++), "3", "57");
    graph.addEdge(String.valueOf(j++), "3", "26");
    graph.addEdge(String.valueOf(j++), "3", "59");
    graph.addEdge(String.valueOf(j++), "4", "67");
    graph.addEdge(String.valueOf(j++), "4", "36");
    graph.addEdge(String.valueOf(j++), "4", "34");
    graph.addEdge(String.valueOf(j++), "4", "43");
    graph.addEdge(String.valueOf(j++), "4", "56");
    graph.addEdge(String.valueOf(j++), "4", "14");
    graph.addEdge(String.valueOf(j++), "4", "81");
    graph.addEdge(String.valueOf(j++), "4", "24");
    graph.addEdge(String.valueOf(j++), "4", "58");
    graph.addEdge(String.valueOf(j++), "4", "91");
    graph.addEdge(String.valueOf(j++), "5", "65");
    graph.addEdge(String.valueOf(j++), "5", "69");
    graph.addEdge(String.valueOf(j++), "5", "7");
    graph.addEdge(String.valueOf(j++), "5", "8");
    graph.addEdge(String.valueOf(j++), "5", "35");
    graph.addEdge(String.valueOf(j++), "5", "84");
    graph.addEdge(String.valueOf(j++), "5", "85");
    graph.addEdge(String.valueOf(j++), "5", "54");
    graph.addEdge(String.valueOf(j++), "5", "23");
    graph.addEdge(String.valueOf(j++), "5", "27");
    graph.addEdge(String.valueOf(j++), "6", "33");
    graph.addEdge(String.valueOf(j++), "6", "98");
    graph.addEdge(String.valueOf(j++), "6", "76");
    graph.addEdge(String.valueOf(j++), "6", "16");
    graph.addEdge(String.valueOf(j++), "6", "22");
    graph.addEdge(String.valueOf(j++), "6", "29");
    graph.addEdge(String.valueOf(j++), "6", "95");
    graph.addEdge(String.valueOf(j++), "7", "49");
    graph.addEdge(String.valueOf(j++), "7", "52");
    graph.addEdge(String.valueOf(j++), "7", "28");
    graph.addEdge(String.valueOf(j++), "7", "74");
    graph.addEdge(String.valueOf(j++), "8", "77");
    graph.addEdge(String.valueOf(j++), "8", "30");
    graph.addEdge(String.valueOf(j++), "8", "71");
    graph.addEdge(String.valueOf(j++), "8", "40");
    graph.addEdge(String.valueOf(j++), "9", "60");
    graph.addEdge(String.valueOf(j++), "9", "37");
    graph.addEdge(String.valueOf(j++), "9", "63");
    graph.addEdge(String.valueOf(j++), "9", "39");
    graph.addEdge(String.valueOf(j++), "10", "96");
    graph.addEdge(String.valueOf(j++), "11", "38");
    graph.addEdge(String.valueOf(j++), "12", "50");
    graph.addEdge(String.valueOf(j++), "16", "46");
    graph.addEdge(String.valueOf(j++), "18", "97");
    graph.addEdge(String.valueOf(j++), "19", "25");
    graph.addEdge(String.valueOf(j++), "21", "98");
    graph.addEdge(String.valueOf(j++), "23", "86");
    graph.display();
  }
}