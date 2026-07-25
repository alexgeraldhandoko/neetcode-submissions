public class Solution {
    public int maxArea(int[] height) {
        // Create a list of bar objects
        ArrayList<Bar> arr = new ArrayList<>();
        for (int i = 0; i < height.length; i++) {
            arr.add(new Bar(i, height[i]));
        }

        // Sort the bars based on increasing height
        ArrayList<Bar> increasingHeight = new ArrayList<>(arr);
        increasingHeight.sort((a, b) -> Integer.compare(a.height, b.height));

        // Sort the bars based on decreasing x-coordinates
        TreeSet<Bar> decreasingX = new TreeSet<>((a, b) -> Integer.compare(b.x, a.x));
        decreasingX.addAll(arr);

        // Sort the bars based on increasing x-coordinates
        TreeSet<Bar> increasingX = new TreeSet<>((a, b) -> Integer.compare(a.x, b.x));
        increasingX.addAll(arr);

        int max = Integer.MIN_VALUE;
        
        // Do matching to the right
        for (int i = 0; i < increasingHeight.size(); i++) {
            // System.out.println("right matching");
            Bar leftBar = increasingHeight.get(i);
            Bar rightBar = decreasingX.first();
            
            decreasingX.remove(leftBar);

            int containerSize = leftBar.height * (rightBar.x - leftBar.x);
            if (containerSize > max) max = containerSize;
        }

        // Do matching to the left
        for (int i = 0; i < increasingHeight.size(); i++) {
            Bar leftBar = increasingX.first();
            Bar rightBar = increasingHeight.get(i);

            increasingX.remove(rightBar);

            int containerSize = rightBar.height * (rightBar.x - leftBar.x);
            if (containerSize > max) max = containerSize;
        }

        return max;
    }
}

class Bar {
    int x;
    int height;

    public Bar(int x, int height) {
        this.x = x;
        this.height = height;
    }
}