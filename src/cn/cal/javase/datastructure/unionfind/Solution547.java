package cn.cal.javase.datastructure.unionfind;

import java.util.TreeSet;

public class Solution547 {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind1 uf = new UnionFind1(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                if (M[i][j] == 1)
                    uf.union(i, j);

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++)
            set.add(uf.find(i));
        return set.size();
    }
}
