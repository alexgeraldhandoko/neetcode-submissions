class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Check for columns and rows
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> rowSet = new HashSet<>();
            HashSet<Integer> colSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                // Check column
                if (board[j][i] != '.') {
                    int colNum = board[j][i] - '0';
                    // System.out.println("colNum: " + colNum);
                    if (colNum < 0 || colNum > 9) return false;
                    // System.out.println("i: " + i + " j: " + j);
                    if (colSet.contains(colNum)) return false;
                    colSet.add(colNum);
                }
                // Check row
                if (board[i][j] != '.') {
                    int rowNum = board[i][j] - '0';
                    if (rowNum < 0 || rowNum > 9) return false;
                    if (rowSet.contains(rowNum)) return false;
                    rowSet.add(rowNum);
                }
            }   
        }

        // Check for each 3x3 box
        for (int bigRow = 0; bigRow < 3; bigRow++) {
            for (int bigCol = 0; bigCol < 3; bigCol++) {
                HashSet<Integer> set = new HashSet<>();
                for (int i = bigRow * 3; i < bigRow * 3 + 3; i++) {
                    for (int j = bigCol * 3; j < bigCol * 3 + 3; j++) {
                        if (board[i][j] == '.') continue;
                        int num = board[i][j] - '0';
                        if (set.contains(num)) return false;
                        set.add(num);
                    }
                }
            }
        }

        return true;
    }
}