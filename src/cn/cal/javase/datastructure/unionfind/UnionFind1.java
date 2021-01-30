package cn.cal.javase.datastructure.unionfind;

public class UnionFind1 implements Uf {
    private int[] id;

    public UnionFind1(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size is more than 0");
        }
        id = new int[size];
        for (int i = 0, end = id.length - 1; i <= end; i++) {
            id[i] = i;
        }
    }

    @Override
    public int size() {
        return id.length;
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
        for (int i = 0, end = id.length - 1; i <= end; i++) {
            if (id[i] == pRoot) {
                id[i] = qRoot;
            }
        }
    }

    public int find(int p) {
        if (p < 0 || p >= size()) {
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }

}
