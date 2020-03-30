package algo.string_match;

public class Trie {
    private TrieNode root = new TrieNode('/');

    // 构建 trie 树
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null)
                p.children[index] = new TrieNode(text[i]);
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null)
                return false;
            p = p.children[index];
        }
        return p.isEndingChar; // 如果 pattern 匹配完了，但是字符不是最后一个，表示 pattern 只是当前字符串的前缀
    }


    public class TrieNode {
        public char data;
        private TrieNode[] children = new TrieNode[26];
        private boolean isEndingChar = false;

        private TrieNode(char data) {
            this.data = data;
        }
    }
}
