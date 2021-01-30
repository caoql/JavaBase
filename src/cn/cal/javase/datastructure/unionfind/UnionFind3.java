package cn.cal.javase.datastructure.unionfind;

public class UnionFind3 implements Uf {

    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size is more than 0");
        }
        parent = new int[size];
        sz = new int[size];
        for (int i = 0, end = parent.length - 1; i <= end; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int size() {
        return parent.length;
    }

    public int find(int p) {
        if (p < 0 || p > parent.length - 1) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
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
        if (sz[pRoot] >= sz[qRoot]) {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
    }
}
