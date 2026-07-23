class MedianFinder {
    // Implement two PQs
    // One is the smaller PQ
    // One is the larger PQ
    // If even number of elements, then both PQs have same number of elements
    // If odd number of elements, then the larger PQ has the larger number of elements
    // If even, then the median is the (largest elem in smaller PQ + smallest elem in larger PQ) / 2
    // If odd, then the median is the smallest elem in the larger PQ

    // Right PQ will always be equal to or the larger PQ in terms of amt of numbers

    PriorityQueue<Integer> leftMaxPq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    PriorityQueue<Integer> rightMinPq = new PriorityQueue<>();

    public MedianFinder() {
        
    }

    public void addNum(int num) {
        // Edge case 1: If both PQs are empty
        if (leftMaxPq.isEmpty() && rightMinPq.isEmpty()) {
            rightMinPq.offer(num);
            return;
        }

        // Edge case 2: If left PQ is empty
        if (leftMaxPq.isEmpty()) {
            if (num > rightMinPq.peek()) {
                leftMaxPq.offer(rightMinPq.poll());
                rightMinPq.offer(num);
            } else {
                leftMaxPq.offer(num);
            }
            return;
        }

        // Case 1: Both PQs are same size
        if (leftMaxPq.size() == rightMinPq.size()) {
            // Case 1A: The number is larger than the largest in the left PQ
            // --> Then put the number in the right PQ
            if (num >= leftMaxPq.peek()) {
                rightMinPq.offer(num); 
                return;
            }
            // Case 1B: The number is smaller than the smallest in the right PQ
            // --> Then put the number in the left PQ
            // --> Then put the largest number in the left PQ into the right PQ
            if (num < rightMinPq.peek()) {
                leftMaxPq.offer(num);
                rightMinPq.offer(leftMaxPq.poll());
            }
        } 
        // Case 2: The right PQ is larger
        else {
            // Case 2A: The number is smaller than the smallest in the right PQ
            // --> Then put the number in the left PQ
            if (num <= rightMinPq.peek()) {
                leftMaxPq.offer(num); 
                return;
            }
            // Case 2B: The number is larger than the largest in the left PQ
            // --> Then put the number in the right PQ
            // --> Then put the smallest number in the right PQ into the left PQ
            if (num > leftMaxPq.peek()) {
                rightMinPq.offer(num);
                leftMaxPq.offer(rightMinPq.poll());
            }
        }
    }
    
    public double findMedian() {
        int amt = leftMaxPq.size() + rightMinPq.size();
        if ((amt & 1) == 0) {
            return (leftMaxPq.peek() + rightMinPq.peek()) / 2.0;
        }
        return rightMinPq.peek();
    }
}