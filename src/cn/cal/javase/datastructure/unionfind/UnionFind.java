package cn.cal.javase.datastructure.unionfind;

/**
 * 并查集的模板代码，理解并记住
 */
public class UnionFind implements Uf {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size is more than 0");
        }
        parent = new int[size];
        rank = new int[size];
        for (int i = 0, end = parent.length - 1; i <= end; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int size() {
        return parent.length;
    }

    private int find(int p) {
        if (p < 0 || p > parent.length - 1) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }


    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
