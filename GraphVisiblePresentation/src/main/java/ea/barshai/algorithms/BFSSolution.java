package ea.barshai.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

// Ищем путь от заданной точки старта до выхода
public class BFSSolution {

  int edgeSize;
  int[][] arr;
  Point startPoint;

  List<Point> visitedPoints = new ArrayList<>();
  Deque<Point> queue = new ArrayDeque<>();
  List<Point> reachedExits = new ArrayList<>();

  public static void main(String[] args) throws FileNotFoundException {
    BFSSolution bfsSolution = new BFSSolution();
    bfsSolution.read();
    bfsSolution.printTask();
    bfsSolution.findPathToExit();
    bfsSolution.printPath();
  }

  private void findPathToExit() {
    queue.add(startPoint);
    processQueue();
  }

  private void processQueue() {
    while (queue.size() > 0) {
      Point p = queue.pollFirst();
      System.out.printf("Достали из очереди точку %s.%s%n", p.x, p.y);
      if (pointIsExit(p)) {
        reachedExits.add(p);
        System.out.printf("Exit is here: %s%n", p);
        //return;
      }
      if (!visitedPoints.contains(p)) {
        visitedPoints.add(p);
        putNeighborsInQueue(p);
      }
    }
  }

  private void putNeighborsInQueue(Point p) {
    List<Point> neighbors = new ArrayList<>(4);
    if (p.y - 1>=0) {
      Point upperNeighbor = new Point(p.x, p.y - 1, p.distanceFromStart + 1, p);
      neighbors.add(upperNeighbor);
    }
    if (p.y + 1< edgeSize) {
      Point bottomNeighbor = new Point(p.x, p.y + 1, p.distanceFromStart + 1, p);
      neighbors.add(bottomNeighbor);
    }
    if (p.x - 1>=0) {
      Point leftNeighbor = new Point(p.x - 1, p.y, p.distanceFromStart + 1, p);
      neighbors.add(leftNeighbor);
    }
    if (p.x + 1<edgeSize) {
      Point rightNeighbor = new Point(p.x + 1, p.y, p.distanceFromStart + 1, p);
      neighbors.add(rightNeighbor);
    }

    neighbors.forEach(this::putPointInQueue);
  }

  private void putPointInQueue(Point p) {
    if (arr[p.y][p.x] == 0 && !visitedPoints.contains(p)) {
      queue.add(p);
    }
  }

  private boolean pointIsExit(Point p) {
    if (isOnBorder(p)) {
      return arr[p.y][p.x] == 0;
    }

    return false;
  }

  private boolean isOnBorder(Point p) {
    return p.x == 0 || p.y == 0 || p.x == edgeSize - 1 || p.y == edgeSize - 1;
  }

  private boolean isInPathToExit(Point p) {
    for (Point exit : reachedExits) {
      if (exit.containsPointInPath(p)) {
        return true;
      }
    }
    return false;
  }

  private void read(){
    System.setIn(this.getClass().getClassLoader().getResourceAsStream("HakerRank_testCase19.txt"));
    Scanner in = new Scanner(System.in);
    edgeSize = in.nextInt();
    arr = new int[edgeSize][edgeSize];

    in.nextLine();
    String s = in.nextLine().trim();
    StringTokenizer st = new StringTokenizer(s);
    int startPointX = Integer.parseInt(st.nextToken());
    int startPointY = Integer.parseInt(st.nextToken());
    startPoint = new Point(startPointX, startPointY, 0, null);

    for (int i = 0; i < edgeSize; i++) {
      if (!in.hasNextLine()) {
        break;
      }
      String line = in.nextLine().trim();
      StringTokenizer stLine = new StringTokenizer(line);
      for (int j = 0; j < edgeSize; j++) {
        int point = Integer.parseInt(stLine.nextToken());
        arr[i][j] = point;
      }
    }
  }

  private void printTask() {
    System.out.print("  ");
    for (int i = 0; i < edgeSize; i++) {
      System.out.printf("%s ", i);
    }
    System.out.println();
    System.out.print("  ");
    for (int i = 0; i < edgeSize; i++) {
      System.out.print("--");
    }
    System.out.println();
    for (int i = 0; i < edgeSize; i++) {
      System.out.printf("%s|", i);
      for (int j = 0; j < edgeSize; j++) {
        boolean isStartPoint = i == startPoint.y && j == startPoint.x;
        System.out.printf("%s ", isStartPoint ? "S" : (arr[i][j] == 0 ? "." : "Х"));
      }
      System.out.println();
    }
  }

  private void printPath() {
    System.out.print("  ");
    for (int i = 0; i < edgeSize; i++) {
      System.out.printf("%s ", i);
    }
    System.out.println();
    System.out.print("  ");
    for (int i = 0; i < edgeSize; i++) {
      System.out.print("--");
    }
    System.out.println();
    for (int i = 0; i < edgeSize; i++) {
      System.out.printf("%s|", i);
      for (int j = 0; j < edgeSize; j++) {
        boolean isStartPoint = i == startPoint.y && j == startPoint.x;
        Point p = new Point(j, i);
        System.out.printf("%s ", isStartPoint ? "S" : (arr[i][j] == 0 ? (isInPathToExit(p) ? "o" : ".") : "Х"));
      }
      System.out.println();
    }
  }
}

class Point {

  int x;
  int y;
  int distanceFromStart;
  Point stepFrom;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point(int x, int y, int distanceFromStart, Point stepFrom) {
    this.x = x;
    this.y = y;
    this.distanceFromStart = distanceFromStart;
    this.stepFrom = stepFrom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Point point)) {
      return false;
    }
    return x == point.x && y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "Point{" +
        "x=" + x +
        ", y=" + y +
        ", distanceFromStart=" + distanceFromStart +
        '}';
  }

  public boolean containsPointInPath(Point pointToFind) {
    if (this.equals(pointToFind)) {
      return true;
    }
    Point p = this.stepFrom;
    while (p !=null) {
      if (p.equals(pointToFind)) {
        return true;
      }
      p = p.stepFrom;
    }

    return false;
  }
}

