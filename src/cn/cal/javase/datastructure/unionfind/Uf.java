package cn.cal.javase.datastructure.unionfind;

public interface Uf {
    int size();

    boolean isConnected(int p, int q);

    void union(int p, int q);

}
