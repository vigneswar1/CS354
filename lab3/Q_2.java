import java.util.*;

class Tuple {
    char u, v; 
    int w; 
    Tuple(char u, char v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public String toString() {
        return u + " - " + v + " : " + w;
    }
}

class Q_2 {


    static int[] parent = new int[4];

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    static int idx(char c) {
        return c - 'a'; 
    }

    public static void main(String[] args) {

        Tuple[] edges = {
            new Tuple('a','c',6),
            new Tuple('a','b',6),
            new Tuple('a','d',6),
            new Tuple('b','d',2),
            new Tuple('c','d',2)
        };

        Arrays.sort(edges, Comparator.comparingInt(e -> e.w));

        for (int i = 0; i < 4; i++)
            parent[i] = i;

        int mstWeight = 0;

        System.out.println("Minimum Spanning Tree:");

        for (Tuple e : edges) {
            int u = idx(e.u);
            int v = idx(e.v);

            if (find(u) != find(v)) {
                union(u, v);
                mstWeight += e.w;
                System.out.println(e);
            }
        }

        System.out.println("Total MST weight = " + mstWeight);

        int INF = 9999;

        char[] vertices = {'a','b','c','d'};

        int[][] graph = {
            {0, 6, 6, 6},
            {6, 0, INF, 2},
            {6, INF, 0, 2},
            {6, 2, 2, 0}
        };

        int[] dist = {0, INF, INF, INF};  
        int[] parentSPT = {-1, -1, -1, -1};
        boolean[] visited = new boolean[4];

        for (int i = 0; i < 4; i++) {

            int u = -1;
            int min = INF;

            for (int j = 0; j < 4; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            visited[u] = true;

            for (int j = 0; j < 4; j++) {
                if (!visited[j] && graph[u][j] != INF &&
                    dist[u] + graph[u][j] < dist[j]) {

                    dist[j] = dist[u] + graph[u][j];
                    parentSPT[j] = u;
                }
            }
        }

        System.out.println("\nShortest Path Tree from a:");

        for (int i = 1; i < 4; i++) {
            System.out.println(vertices[parentSPT[i]] +
                               " -> " + vertices[i] +
                               " (distance = " + dist[i] + ")");
        }
    }
}

