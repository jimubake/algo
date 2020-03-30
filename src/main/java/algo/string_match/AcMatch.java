package algo.string_match;

import java.util.LinkedList;
import java.util.Queue;


public class AcMatch {

    private AcNode root = new AcNode('/');

    public void match(char[] text) {
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int index = text[i] - 'a';
            while (p.children[index] == null && p != root)
                p = p.fail;
            p = p.children[index];
            if (p == null) p = root; // 没有匹配到，重新从 root 开始匹配

            AcNode temp = p;
            while (temp != root) {
                if (temp.isEndingChar) {
                    int pos = i - temp.length + 1;
                    System.out.println("匹配的起始下标" + pos + "; 长度" + temp.length);
                }
                temp = temp.fail;
            }
        }                                                                    
    }

    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; ++i) { // 逐层创建每个节点的fail 指针
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (pc == root)
                    pc.fail = root;
                else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            qc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null)
                        pc.fail = root;
                }
                queue.add(pc);
            }
        }
    }


    public void insert(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null)
                p.children[index] = new AcNode(text[i]);
            p = p.children[index];
            p.length++;
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        AcNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null)
                return false;
            p = p.children[index];
        }
        return p.isEndingChar; // 如果 pattern 匹配完了，但是字符不是最后一个，表示 pattern 只是当前字符串的前缀
    }

    public class AcNode {
        public char data;
        public AcNode[] children = new AcNode[26];
        public boolean isEndingChar = false;
        public int length = -1; // 当 isEndingChar = true时，记录模式串长度
        public AcNode fail; // 失败指针

        public AcNode(char data) {
            this.data = data;
        }
    }
}
