package algo.stack08;


// 括号match
public class KuoHaoCheck {

    private int size;
    private Node top;

    public KuoHaoCheck() {
        this.size = 0;
        this.top = null;
    }

    public static void main(String[] args) {
        KuoHaoCheck check = new KuoHaoCheck();
        System.out.println(check.isMatch("[]{[{())}]}"));
    }

    public boolean isMatch(String data) {
        char[] chars = data.toCharArray();
        if (chars.length == 0) return false;
        for (char i : chars) {
            if (i == '[' || i == '(' || i == '{')
                push(i);
            else {
                char c = pop();
                if ((int) c + 1 != (int) i && (int) c + 2 != (int) i) return false;
            }
        }
        return top == null;
    }

    public void push(char c) {
        this.top = new Node(c, this.top);
        size++;
    }

    public char pop() {
        if (this.top == null)
            return ' ';
        char c = this.top.data;
        size--;
        this.top = this.top.next;
        return c;
    }

    public static class Node {
        private char data;
        private Node next;

        public Node(char data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
