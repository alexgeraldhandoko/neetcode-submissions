class Solution {
    ArrayList<List<Integer>> listCollection = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Looks like dynamic programming to me
        // It's like unbounded knapsack
        // So we can choose to take the first number either 1 or 2 or 3 and so on 
        knapsack(candidates, target, 0, 0, new ArrayList<Integer>());
        return listCollection;
    }

    public void knapsack(int[] candidates, int target, int idx, int sum, ArrayList<Integer> list) {
        if (idx >= candidates.length) return;
        if (sum == target) {
            List<Integer> newList = new ArrayList<>(list);
            listCollection.add(newList);
            return;
        }
        if (sum + candidates[idx] <= target) {
            // Take the current number
            list.add(candidates[idx]);
            sum += candidates[idx];
            knapsack(candidates, target, idx, sum, list);
            // Don't take current number
            list.remove(list.size() - 1);
            sum -= candidates[idx];
            idx++;
            knapsack(candidates, target, idx, sum, list);
        } else {
            // Don't take current number
            idx++;
            knapsack(candidates, target, idx, sum, list);
        }
    }
}