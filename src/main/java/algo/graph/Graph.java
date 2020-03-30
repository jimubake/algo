package algo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 */
public class Graph {
    private int v; // 定点的个数
    private LinkedList<Integer> adj[]; // 邻接表

    private boolean found = false;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }


    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];

        for (int i = 0; i < v; i++)
            prev[i] = -1;

        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int s, int t, boolean[] visited, int[] prev) {
        if (found)
            return;
        visited[s] = true;
        if (s == t) {
            found = true;
            return;
        }

        for (int i = 0; i < adj[s].size(); i++) {
            int q = adj[s].get(i);
            if (!visited[q]) {
                prev[q] = s;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    public void bfs(int s, int t) { // 搜索一条从 s -> t 的路径：Breath-First-Search
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];

        for (int i = 0; i < v; i++)
            prev[i] = -1;

        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s)
            print(prev, s, prev[t]);
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(5, 4);
        graph.addEdge(5, 6);
        graph.addEdge(3, 6);
        graph.addEdge(5, 7);
        graph.addEdge(8, 6);
        graph.addEdge(7, 8);

        graph.bfs(1, 8);
        System.out.println();
        graph.dfs(1,8);
    }
}
