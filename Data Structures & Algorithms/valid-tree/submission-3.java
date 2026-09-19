class Solution {
    boolean debug = false;

    public boolean validTree(int n, int[][] edges) {
        // Create the graph
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodeMap.put(i, new Node(i));
        }
        for (int i = 0; i < edges.length; i++) {
            // Initialise the node
            int src = edges[i][0];
            int target = edges[i][1];
            Node nodeSrc = new Node(src);
            Node nodeTarget = new Node(target);
            if (nodeMap.containsKey(src)) {
                nodeSrc = nodeMap.get(src);
            } else {
                nodeMap.put(src, nodeSrc);
            }
            if (nodeMap.containsKey(target)) {
                nodeTarget = nodeMap.get(target);
            } else {
                nodeMap.put(target, nodeTarget);
            }

            // Assign the neighbour to that node
            nodeSrc.neighbours.add(nodeTarget);
            nodeTarget.neighbours.add(nodeSrc);
        }

        if (debug) {
            System.out.println("The map contains: ");
            for (Node node : nodeMap.values()) {
                System.out.println(node.id);
            }
        }

        // Perform bfs cyclic detection on every connected component 
        for (int i = 0; i < nodeMap.size(); i++) {
            Node node = nodeMap.get(i);
            if (!node.visited) {
                Queue<Node> q = new LinkedList<>();
                q.offer(node);
                if (!bfs(q)) return false;
            }
        }

        // Perform connected check
        for (Node node : nodeMap.values()) {
            node.visited = false;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(nodeMap.get(0));
        bfs(q);
        for (Node node : nodeMap.values()) {
            if (!node.visited) return false;
        }
        return true;
    }

    public boolean bfs(Queue<Node> q) {
        HashSet<Node> set = new HashSet<>();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node src = q.poll();
                set.add(src);
                src.visited = true;
                if (debug) {
                    System.out.println("src: " + src.id);
                }
                for (Node neighbour : src.neighbours) {
                    if (neighbour == src.parent) {
                        continue;
                    } else if (set.contains(neighbour)) {
                        return false;
                    }
                    neighbour.parent = src;
                    q.offer(neighbour);
                }
            }
        }
        return true;
    }
}

class Node {
    int id;
    Node parent;
    ArrayList<Node> neighbours = new ArrayList<>();
    boolean visited = false;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}

// Transform the edge list into a graph
    // Then do a BFS
    // During BFS, do not enqueue parents
    // Each node should only have one parent, assigned when
    // it is enqueued
    // If during the BFS, nodes at a deeper depth tries to
    // enqueue a node from a shallower depth which is not its parent,
    // then there is a cycle.
    // Else, there is no cycle.