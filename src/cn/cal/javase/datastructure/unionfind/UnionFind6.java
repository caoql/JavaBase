package cn.cal.javase.datastructure.unionfind;

public class UnionFind6 implements Uf {
    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
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

    public int find(int p) {
        if (p < 0 || p > parent.length - 1) {
            throw new IllegalArgumentException("p is out of bound");
        }
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    @Override
    public int size() {
        return parent.length;
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
