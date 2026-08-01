/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // Idea: 
        // A graph would be the clone of another graph if every node's neighbours
        // of the clone graph is equal to every node's neighbours of the original
        // graph

        // Since the graph is connected, we can do BFS from the origin node

        // Let there be a cloneNode variable, representing the current node whose
        // neighbours we are adding in the clone graph

        // Then whenever we enqueue the neighbours of a node into the BFS queue,
        // we also add it as the neighbours of cloneNode

        Queue<Node> originalQ = new LinkedList<>();
        Queue<Node> copyQ = new LinkedList<>();

        originalQ.add(node);
        Node firstCopyNode = new Node(node.val);
        copyQ.add(firstCopyNode);
        bfs(originalQ, copyQ);
        // System.out.println("firstCopyNode: " + firstCopyNode);
        // System.out.println("firstCopyNode neighbors: " + firstCopyNode.neighbors.stream().map(neighbor -> neighbor.val).toList());
        return firstCopyNode;
    }

    public void bfs(Queue<Node> originalQ, Queue<Node> copyQ) {
        HashMap<Integer, Node> enqueued = new HashMap<>();

        enqueued.put(copyQ.peek().val, copyQ.peek());

        while (!originalQ.isEmpty()) {
            Node originalNode = originalQ.poll();
            Node copyNode = copyQ.poll();
            
            // System.out.println("");
            // System.out.println("originalNode: " + originalNode.val);
            // System.out.println("copyNode: " + copyNode.val);

            for (Node neighbor : originalNode.neighbors) {
                // System.out.println("");
                // System.out.println("neighbor: " + neighbor.val);

                if (enqueued.containsKey(neighbor.val)) {
                    copyNode.neighbors.add(enqueued.get(neighbor.val));
                    // System.out.println("adding new neighbor: " + neighbor.val);
                } else {
                    // System.out.println("adding new neighbor: " + neighbor.val);
                    Node newCopyNode = new Node(neighbor.val);
                    copyNode.neighbors.add(newCopyNode);
                    copyQ.offer(newCopyNode);
                    originalQ.offer(neighbor);
                    enqueued.put(neighbor.val, newCopyNode);
                }
            }
        }
    }
}