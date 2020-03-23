package algo.stack08;

import com.sun.tools.javac.tree.JCTree;

public class SampleBrowser {

    public static void main(String[] args) {
        SampleBrowser browser = new SampleBrowser();
        browser.open("www.baidu.com");
        browser.goBack();
        browser.goForward();

        browser.open("www.google.com");
        browser.goBack();
        browser.goForward();
    }

    private String currentPage;
    private LinkedListBasedStack backStack;
    private LinkedListBasedStack forwardStack;

    public SampleBrowser() {
        this.backStack = new LinkedListBasedStack();
        this.forwardStack = new LinkedListBasedStack();
    }

    public void open(String url) {
        if (this.currentPage != null) {
            backStack.push(this.currentPage);
            forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack() {
        return this.backStack.size > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.size >0;
    }

    public String goBack() {
        if (canGoBack()) {
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }
        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if(canGoForward()) {
            this.backStack.push(this.currentPage);
            String forwardPage=this.forwardStack.pop();
            showUrl(forwardPage, "Forward");
            return forwardPage;
        }
        System.out.println("* Cannot go forward, no pages ahead.");
        return null;
    }


    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + currentPage);
    }

    public static class LinkedListBasedStack {
        private int size;

        private Node top;

        static Node createNode(String data, Node next) {
            return new Node(data, next);
        }

        public void clear() {
            this.top = null;
            this.size = 0;
        }

        public void push(String data) {
            Node node = new Node(data, this.top);
            this.top = node;
            ++size;
        }

        public String pop() {
            if (top == null) return null;
            String data = this.top.data;
            this.top = this.top.next;
            size--;
            return data;
        }

        public static class Node {
            private String data;
            private Node next;

            public Node(String data, Node next) {
                this.data = data;
                this.next = next;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }
    }

}
