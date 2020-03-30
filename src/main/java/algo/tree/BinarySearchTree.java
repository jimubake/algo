package algo.tree;

/**
 * @author jimubake
 *
 */

public class BinarySearchTree {

    private Node tree;

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(8);
        tree.insert(9);
        tree.insert(15);
        tree.insert(5);
        tree.insert(14);
        tree.insert(7);
        tree.insert(17);

        tree.inOrder(tree.tree);
    }

    public int depth() {
        return nodeDep(tree);
    }

    public int nodeDep(Node node) {
        if (node == null) return 0;
        int leftDep = nodeDep(node.left);
        int rightDep = nodeDep(node.right);
        int max = leftDep;
        if (rightDep > leftDep)
            max = rightDep;
        return max;
    }

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (p.data > data) p = p.left;
            else if (p.data < data) p = p.right;
            else return p;
        }
        return null;
    }

    public Node findMin() {
        if (tree == null) return null;
        Node p = tree;
        while (p.left != null) p = p.left;
        return p;
    }

    public Node findMax() {
        if (tree == null) return null;
        Node p = tree;
        while (p.right != null)
            p = p.right;
        return p;
    }

    public void delete(int data) {
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (p.data > data)
                p = p.left;
            else
                p = p.right;
        }
        if (p == null) return;

        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minP = minP.left;
                minPP = minP;
            }

            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        Node child;
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child;
        else if (pp.left == p) pp.left = child;
        else p.right = child;

    }

    public void insert(int data) {
        Node newNode = new Node(data);
        if (tree == null) {
            tree = newNode;
            return;
        }

        Node p = tree;
        while (true) {
            if (p.data > data)
                if (p.left == null) {
                    p.left = newNode;
                    return;
                } else
                    p = p.left;
            else if (p.data < data)
                if (p.right == null) {
                    p.right = newNode;
                    return;
                } else
                    p = p.right;
        }
    }

    // 中序遍历
    public void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
