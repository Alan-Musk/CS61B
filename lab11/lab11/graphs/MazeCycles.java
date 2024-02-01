package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo; // 距离数组，distTo[v] 表示到顶点v的最短路径长度
    public int[] edgeTo; // 边数组，edgeTo[v] 表示到达顶点v的最后一条边的起点
    public boolean[] marked; // 标记数组，用于记录顶点是否已被访问
    */
    private Stack<Integer> cycle; // 用于存储环中的所有顶点
    private boolean[] onStack; // 辅助数组，用于检测环，记录DFS过程中栈上的顶点

    public MazeCycles(Maze m) {
        super(m);
        onStack = new boolean[m.N()]; // 初始化辅助数组
        distTo[0] = 0; // 将起点的距离设为0
    }

    @Override
    public void solve() {
        dfs_helper(0); // 从顶点0开始深度优先搜索
        // 构建环路径
        for (int i = cycle.pop(); !cycle.isEmpty(); ) {
            int temp = cycle.pop();
            edgeTo[i] = temp;
            i = temp;
        }
    }

    // Helper methods go here
    private void dfs_helper(int v) {
        onStack[v] = true; // 将当前顶点标记为栈上
        marked[v] = true; // 标记顶点v已被访问
        announce(); // 更新UI或打印调试信息
        for (int w : maze.adj(v)) { // 遍历与v相邻的所有顶点
            if (cycle != null) return; // 如果已找到环，终止搜索
            else if (!marked[w]) { // 如果w未被访问
                edgeTo[w] = v; // 更新到达w的边
                distTo[w] = distTo[v] + 1; // 更新到w的距离
                announce(); // 更新UI或打印调试信息
                dfs_helper(w); // 递归调用
            } else if (onStack[w]) { // 如果w已在栈上，说明找到了一个环
                cycle = new Stack<>(); // 初始化环栈
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x); // 将环中的顶点压入栈
                }
                cycle.push(w); // 将环的起始和结束顶点压入栈
                cycle.push(v);
            }
        }
        onStack[v] = false; // 从栈上移除顶点v
    }
}
