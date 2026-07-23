class Solution {
    ArrayList<Integer> lengths = new ArrayList<>();

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            lengths.add(str.length());
            sb.append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        int cnt = 0;
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < lengths.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = cnt; j < cnt + lengths.get(i); j++) sb.append(str.charAt(j));
            out.add(sb.toString());
            cnt += lengths.get(i);
        }
        return out;
    }
}