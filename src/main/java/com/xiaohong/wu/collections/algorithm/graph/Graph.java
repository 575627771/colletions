package com.xiaohong.wu.collections.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 * <p>
 * 使用邻接表这种存储结构来实现
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/8/1 14:11
 **/
public class Graph {
    /**
     * 顶点数
     */
    private int v;
    /**
     * 邻接表
     */
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 无向图 相互添加
     *
     * @param s 顶点
     * @param e 顶点
     */
    public void addEdge(int s, int e) {
        adj[s].add(e);
        adj[e].add(s);
    }

    /**
     * 广度优先搜索
     *
     * @param s
     * @param e
     */
    public void bfs(int s, int e) {
        if (s == e) {
            return;
        }
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] pre = new int[v];
        for (int i = 0; i < v; i++) {
            pre[i] = -1;
        }
        while (queue.size() != 0) {
            Integer poll = queue.poll();
            for (int i = 0; i < adj[poll].size(); i++) {
                Integer integer = adj[poll].get(i);
                if (!visited[integer]) {
                    pre[integer] = poll;
                    if (integer == e) {
                        print(pre, s, e);
                        return;
                    }
                    visited[integer] = true;
                    queue.add(integer);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");

    }


    boolean found = false;

    /**
     * 深度优先算法
     *
     * @param s
     * @param e
     */
    public void dfs(int s, int e) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] pre = new int[v];
        for (int i = 0; i < v; i++) {
            pre[i] = -1;
        }
        recurDfs(s, e, visited, pre);
    }

    private void recurDfs(int s, int e, boolean[] visited, int[] pre) {
        if (found) {
            return;
        }
        visited[s] = true;
        if (s == e) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[s].size(); i++) {
            Integer q = adj[s].get(i);
            if (!visited[q]) {
                pre[q] = s;
                recurDfs(q, e, visited, pre);
            }
        }
    }


}
