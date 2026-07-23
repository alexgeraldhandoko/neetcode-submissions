class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // See ipad notes for proof of the following:
        // If the graph is cyclic, course structure is incompleteable
        // If the graph is acyclic, course structure is completeable
        // So, the course structure is completeable if and only if it is acyclic
        // So we just return the boolean isAcyclic
        // Use DFS to determine whether or not it is acyclic

        // Create an arraylist of vertices
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) vertices.add(new Vertex());
        // Add the prereqs to the vertices (outdegrees)
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prereq = prerequisites[i][1];
            Vertex vertex = vertices.get(course);
            vertex.prereqs.add(vertices.get(prereq));
        }
        // Start from the first vertex in the arraylist of vertices
        // Then after the DFS starting from that vertex, go to the next vertex that has not been visited
        // Do this until all vertices have been visited
        int pointer = 0;
        Helper helper = new Helper();
        do {
            helper.complete(vertices.get(pointer));
            while (pointer < vertices.size() - 1 && vertices.get(++pointer).isVisited);
        } while (pointer < vertices.size() && !vertices.get(pointer).isVisited);

        // Return the value of isAcyclic
        return helper.isAcyclic;
    }
}

class Helper {
    // Set isAcyclic to be initially true
    public boolean isAcyclic = true;

    public void complete(Vertex vertex) {
        // Set the vertex to has been visited before
        vertex.isVisited = true;
        // Try to complete its prereqs
        for (Vertex prereq : vertex.prereqs) {
            if (prereq.isVisited && !prereq.isCompleted) isAcyclic = false;
            else if (!prereq.isVisited && !prereq.isCompleted) complete(prereq);
        }
        // After complete all its prereqs, set current vertex to be completed
        vertex.isCompleted = true;
        return;
    }
}

// Create vertex class with the fields:
// ArrayList<Vertex> prereqs
// boolean isVisited -> it has been visited
// boolean isCompleted -> all of its neighbours have been completed
class Vertex {
    ArrayList<Vertex> prereqs;
    boolean isVisited;
    boolean isCompleted;

    public Vertex() {
        this.prereqs = new ArrayList<>();
        this.isVisited = false;
        this.isCompleted = false;
    }
}