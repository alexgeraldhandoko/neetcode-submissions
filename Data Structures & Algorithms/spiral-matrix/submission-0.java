class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        boolean debug = true;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int x = 0;
        int y = 0;
        int oldX = 0;
        int oldY = 0;
        int oldMatrix = 0;
        int countCheck = 0;
        // true && false = right
        // true && true = down
        // false && true = left
        // false && false = up
        boolean xDir = true;
        boolean yDir = false;
        List<Integer> outputList = new ArrayList<>();
        List<Integer> checklist = new ArrayList<>();

        for (int i = 0; i < rows * cols; i++) {
            if (debug) {
                System.out.println("matrix[" + x + "][" + y + "]");
            }
            outputList.add(matrix[x][y]);
            oldMatrix = matrix[x][y];
            matrix[x][y] = -101;
            // Check if the oldX and oldY are the same as the current x and y
            if (x == oldX && y == oldY) {
                countCheck++;
            }
            else {
                countCheck = 0;
            }
            if (countCheck == 3) {
                break;
            }
            oldX = x;
            oldY = y;
            if (xDir && !yDir) {
                y++;
            }
            else if (xDir && yDir) {
                x++;
            }
            else if (!xDir && yDir) {
                y--;
            }
            else {
                x--;
            }

            if (x >= rows || x < 0 || y < 0 || y >= cols || matrix[x][y] == -101) {
                // Change direction
                boolean temp = xDir;
                xDir = !yDir;
                yDir = temp;

                // Remove the changes
                x = oldX;
                y = oldY;
                matrix[x][y] = oldMatrix;
                outputList.remove(i);
                i--;
            }
        }
        return outputList;
    }
}