class Solution {
    public int numIslands(char[][] grid) {
        boolean debug = false;
        int m = grid.length;
        int n = grid[0].length;
        // Try using a matrix?
        Point[][] points = new Point[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                points[i][j] = new Point(grid[i][j]);
            }
        }
        // Then enumerate the neighbours of each point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Get the point
                Point point = points[i][j];
                if (i - 1 >= 0 && grid[i - 1][j] == '1') point.neighbours.add(points[i - 1][j]);
                if (i + 1 < m && grid[i + 1][j] == '1') point.neighbours.add(points[i + 1][j]);
                if (j - 1 >= 0 && grid[i][j - 1] == '1') point.neighbours.add(points[i][j - 1]);
                if (j + 1 < n && grid[i][j + 1] == '1') point.neighbours.add(points[i][j + 1]);
            }
        }
        // Then do BFS
        int count = 0;
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (points[i][j].isVisited || points[i][j].val == 0) {
                    if (debug) System.out.println("Skipping this one");
                    continue;
                }
                if (points[i][j].val == '1' && !points[i][j].isVisited) {
                    q.add(points[i][j]);
                    while (!q.isEmpty()) {
                        Point point = q.poll();
                        if (debug) System.out.println(point.toString());
                        point.isVisited = true;
                        for (Point neighbour : point.neighbours) {
                            if (!neighbour.isVisited && !neighbour.isAdded) {
                                q.add(neighbour);
                                neighbour.isAdded = true;
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }
}

class Point {
    char val;
    boolean isVisited;
    boolean isAdded;
    ArrayList<Point> neighbours;

    public Point(char val) {
        this.val = val;
        this.isVisited = false;
        this.isAdded = false;
        this.neighbours = new ArrayList<Point>();
    }
}