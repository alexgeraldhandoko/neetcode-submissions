class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Graph traversal
        // Reachability test
        // Reverse directed graph?
        // If can flow from A to B, then draw edge from B to A
        // If can flow from A to Ocean, draw edge from Ocean to A
        // This way, collect all nodes reachable from Atlantic
        // Collect all nodes reachable from Pacific
        // Find the intersection of those two collections       
        // BFS will be the traversal algorithm 

        // Prepare data structures
        Queue<Node> q = new LinkedList<>();
        HashSet<Node> pacificSet = new HashSet<>();
        HashSet<Node> atlanticSet = new HashSet<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int m = heights.length;
        int n = heights[0].length;
        Node[][] nodeHeights = new Node[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nodeHeights[i][j] = new Node(heights[i][j], i, j);
            }
        }

        // Populate with top edge and left edge
        for (int i = 0; i < n; i++) q.offer(nodeHeights[0][i]);
        for (int i = 0; i < m; i++) q.offer(nodeHeights[i][0]);

        // Do BFS from the pacific ocean
        while (!q.isEmpty()) {
            Node node = q.poll();
            pacificSet.add(node);
            for (int[] dir : dirs) {
                int newR = node.r + dir[0];
                int newC = node.c + dir[1];
                if (newR >= 0 && newR < m && newC >= 0 && newC < n) {
                    Node neighbour = nodeHeights[newR][newC];
                    if (node.height <= neighbour.height && !pacificSet.contains(neighbour)) {
                        q.offer(neighbour);
                        pacificSet.add(neighbour);
                    }
                }
            }
        }

        // Populate queue with bottom and right edges
        for (int i = 0; i < n; i++) q.offer(nodeHeights[m - 1][i]);
        for (int i = 0; i < m; i++) q.offer(nodeHeights[i][n - 1]);

        // Do BFS from atlantic
        while (!q.isEmpty()) {
            Node node = q.poll();
            atlanticSet.add(node);
            for (int[] dir : dirs) {
                int newR = node.r + dir[0];
                int newC = node.c + dir[1];
                if (newR >= 0 && newR < m && newC >= 0 && newC < n) {
                    Node neighbour = nodeHeights[newR][newC];
                    if (node.height <= neighbour.height && !atlanticSet.contains(neighbour)) {
                        q.offer(neighbour);
                        atlanticSet.add(neighbour);
                    }
                }
            }
        }

        List<List<Integer>> list = new ArrayList<>();
        for (Node node : pacificSet) {
            if (atlanticSet.contains(node)) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(node.r); tmp.add(node.c);
                list.add(tmp);
            }
        }
        return list;
    }
}

class Node {
    public int height;
    public int r;
    public int c;
    public ArrayList<Node> neighbours;

    public Node(int height, int r, int c) {
        this.height = height;
        this.r = r;
        this.c = c;
    }
}