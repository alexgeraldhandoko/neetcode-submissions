class Solution {
    public int[][] merge(int[][] intervals) {
        // Initialise the data structures
        ArrayList<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new Interval(interval[0],
                interval[1]));
        }
        Collections.sort(intervalList);

        // Execute the merging algorithm
        int loopVar = 0;
        while (loopVar < intervalList.size() - 1) {
            Interval currInterval = intervalList.get(loopVar);
            Interval nextInterval = intervalList.get(loopVar + 1);

            if (currInterval.end >= nextInterval.start) {
                currInterval.end = Math.max(
                    currInterval.end, nextInterval.end);
                intervalList.remove(loopVar + 1);
                continue;
            }
            loopVar++;
        }

        // Return the output
        int[][] out = new int[intervalList.size()][2];
        for (int i = 0; i < out.length; i++) {
            out[i][0] = intervalList.get(i).start;
            out[i][1] = intervalList.get(i).end;
        }
        return out;
    }
}

class Interval implements Comparable<Interval> {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Interval other) {
        return this.start - other.start;
    }
}