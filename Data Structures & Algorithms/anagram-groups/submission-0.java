class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            String original = str;
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(original);
        }
        ArrayList<List<String>> out = new ArrayList<>();
        for (List<String> list : map.values()) {
            out.add(list);
        }
        return out;
    }
}
