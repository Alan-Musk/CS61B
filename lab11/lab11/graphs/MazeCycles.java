package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @作者 Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* 继承公共字段:
    public int[] distTo; // 距离数组，distTo[v] 表示到顶点v的最短路径长度
    public int[] edgeTo; // 边数组，edgeTo[v] 表示到达顶点v的最后一条边的起点
    public boolean[] marked; // 标记数组，用于记录顶点是否已被访问
    */
    private Maze maze; // 迷宫对象
    private int[] nodeTo; // 用于记录到达每个顶点的上一个顶点
    private boolean isFound = false; // 用于标记是否找到了循环

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.V()]; // 初始化nodeTo数组
    }

    @Override
    public void solve() {
        dfs_helper(-1, 0); // 从顶点0开始深度优先搜索
    }

    // 辅助方法在这里
    private void dfs_helper(int u, int v) {
        marked[v] = true; // 标记顶点v为已访问
        announce(); // 更新显示
        for (int w : maze.adj(v)) { // 遍历v的所有邻接点
            if (!marked[w]) { // 如果邻接点未被访问
                nodeTo[w] = v; // 记录到达w的前一个顶点是v
                dfs_helper(v, w); // 递归访问w
            } else if (w != u) { // 如果邻接点已被访问，且不是u（即找到了一个循环）
                edgeTo[w] = v; // 记录边
                announce(); // 更新显示
                for (int x = v; x != w; x = nodeTo[x]) { // 追溯循环路径
                    edgeTo[x] = nodeTo[x]; // 记录边
                    announce(); // 更新显示
                }
                isFound = true; // 标记找到循环
            }
            if (isFound) return; // 如果找到循环，则结束搜索
        }
    }

}

