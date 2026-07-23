class PrefixTree {
    ArrayList<SpecialCharacter> root = new ArrayList<>();

    public PrefixTree() {
        
    }
    
    public void insert(String word) {
        ArrayList<SpecialCharacter> arr = root;
        for (int i = 0; i < word.length(); i++) {
            // Obtain the character and the ascii idx
            char c = word.charAt(i);
            int ascii = findAscii(c);

            // If current array doesn't have enough indexes to place the character in its correct idx, then
            // build the array up to that index
            while (arr.size() <= ascii) arr.add(null);

            // Then insert the character in that index
            // Mark it as the end of a string if it is
            SpecialCharacter specialCharacter = i == word.length() - 1 
                                                ? new SpecialCharacter(c, true)
                                                : new SpecialCharacter(c);
            if (arr.get(ascii) == null) {
                arr.set(ascii, specialCharacter);
                arr = specialCharacter.charList;
                continue;
            } else if (i == word.length() - 1) {
                arr.get(ascii).isEnd = true;
            }
            arr = arr.get(ascii).charList;
        }
    }
    
    public boolean search(String word) {
        ArrayList<SpecialCharacter> arr = root;
        int ascii = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            ascii = findAscii(c);
            
            // If the character is larger than the arr or currently null in that idx, then false
            if (ascii > arr.size() - 1 || arr.get(ascii) == null) {
                return false;
            }
            
            if (i < word.length() - 1) arr = arr.get(ascii).charList;
        }
        // If the last character is not marked as end of string, return false
        if (!arr.get(ascii).isEnd) return false;
        return true;
    }
    
    public boolean startsWith(String prefix) {
        ArrayList<SpecialCharacter> arr = root;
        for (char c : prefix.toCharArray()) {
            int ascii = findAscii(c); 
            if (ascii > arr.size() - 1 || arr.get(ascii) == null) return false;
            arr = arr.get(ascii).charList;
        }
        return true;
    }

    public int findAscii(char c) {
        return Character.toLowerCase(c) - 97;
    }
}

class SpecialCharacter {
    public char actualChar;
    public ArrayList<SpecialCharacter> charList = new ArrayList<>();
    public boolean isEnd = false;

    public SpecialCharacter(char actualChar, boolean isEnd) {
        this.actualChar = actualChar;
        this.isEnd = isEnd;
    }

    public SpecialCharacter(char actualChar) {
        this.actualChar = actualChar;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */