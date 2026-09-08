class Solution {
    int m, n;
    String word;
    HashSet<Pair> set = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.word = word;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (track(i, j, 0, board)) return true;
            }
        }
        return false;
    }

    public boolean track(int i, int j, int idx, char[][] board) {
        // if (idx < word.length()) System.out.println("char that we are looking for: " + word.charAt(idx));
        // System.out.println("char that we are currently at: " + board[i][j]);

        if (idx == word.length() - 1 && word.charAt(idx) == board[i][j]) {
            set.remove(new Pair(i, j));
            return true;
        }

        if (idx >= word.length()) {
            set.remove(new Pair(i, j));
            return true;
        }

        if (board[i][j] != word.charAt(idx)) {
            set.remove(new Pair(i, j));
            return false;
        }

        set.add(new Pair(i, j));

        int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (set.contains(new Pair(row, col))) continue;
            if (row < 0 || row >= m || col < 0 || col >= n) continue;
            // System.out.println("test"); // Does not get executed
            if (track(row, col, idx + 1, board)) {
                set.remove(new Pair(i, j));
                return true;
            }
        }
        set.remove(new Pair(i, j));
        return false;
    }
}

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;    
    }

    @Override
    public boolean equals(Object obj) {
        // Pre checks
        if (this == obj) return true;
        if (!(obj instanceof Pair)) return false;

        // Actual check
        Pair pair = (Pair) obj;
        if (this.x == pair.x && this.y == pair.y) return true;
        return false;
    }   

    @Override
    public int hashCode() {
        return 6 * x + y;
    }
}