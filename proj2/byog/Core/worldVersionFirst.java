package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class worldVersionFirst {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private int previousSeed = 0;
    private Set<Position> polePoints = new HashSet<>();//方块的极点

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        //初始化世界
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < finalWorldFrame.length; x += 1) {
            for (int y = 0; y < finalWorldFrame[0].length; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }
        char[] c = input.toCharArray();   //转为char字符
        if (c[0] == 'l') {
            //读档 读取之前的世界种子
            finalWorldFrame = worldGenerator(finalWorldFrame, previousSeed);
        } else {
            //获取字符串中的种子
            int index = 1;
            StringBuilder number = new StringBuilder("");
            while (Character.isDigit(c[index])) {
                number.append(c[index]);
                index += 1;
            }
            //使用获取的种子 生成世界
            finalWorldFrame = worldGenerator(finalWorldFrame, Integer.parseInt(number.toString()));
        }
        return finalWorldFrame;
    }

    // 世界生成器
    private TETile[][] worldGenerator(TETile[][] tiles, long seed) {
        // 设置伪随机
        Random RANDOM = new Random(seed);
        // 绘制 rooms / floor
        int roomsNumber = RandomUtils.uniform(RANDOM, 80, 100);
        for (int i = 0; i < roomsNumber; i++) {
            // 设置 room的 高 宽 渲染起始点
            rooms room = new rooms(RandomUtils.uniform(RANDOM, 2, 6), RandomUtils.uniform(RANDOM, 2, 4), RandomUtils.uniform(RANDOM, 1, WIDTH - 7), RandomUtils.uniform(RANDOM, 1, HEIGHT - 5));
            for (int x = room.getCoreX(); x < room.getCoreX() + room.getWidth(); x++) {
                for (int y = room.getCoreY(); y < room.getCoreY() + room.getHeight(); y++) {
                    tiles[x][y] = Tileset.FLOOR;
                }
            }
        }
        //链接各区块
        // 1.扫描全图 找到方块的极点 helper function
        for (int i = 0; i < WIDTH - 1; i++) {
            for (int j = 0; i < HEIGHT - 1; j++) {
                //为避免没必要的扫描产生的性能消耗 noting->floor 此时的floor才进入辅助函数 而不是对每个floor都判断
                if ((tiles[i][j] == Tileset.NOTHING && tiles[i + 1][j] == Tileset.FLOOR)) {
                    scanPolePoint(tiles, i + 1, j);
                } else if (tiles[i][j] == Tileset.NOTHING && tiles[i][j + 1] == Tileset.FLOOR) {
                    scanPolePoint(tiles, i, j + 1);
                }
            }
        }
        // 2.通过hashset position来画趋向中心点的hallway 遇到floor就停止连通
        // 3.循环 知道hashset 为空

        // 贴瓷砖
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (tiles[i][j] == Tileset.FLOOR) {
                    tiles = Tile(tiles, i, j);
                }
            }
        }
        tiles[WIDTH / 2][HEIGHT / 2] = Tileset.FLOWER;
        return tiles;
    }

    private void scanPolePoint(TETile[][] tiles, int x, int y) {
        Position corePoint = new Position(WIDTH / 2, HEIGHT / 2);//中心点
        Position tempPoint;
        while (tiles[x][y] == Tileset.FLOOR && x + 1 < tiles.length && y + 1 < tiles[0].length) {
            if (tiles[x][y + 1] == Tileset.FLOOR && tiles[x + 1][y] == Tileset.FLOOR) {
                if (corePoint.getDistance(tempPoint = new Position(x, y + 1)) > corePoint.getDistance(tempPoint = new Position(x + 1, y))) {
                    x = x + 1;
                } else {
                    y += 1;
                }
            }else if(tiles[x][y+1]==Tileset.FLOOR)
            {
                y+=1;
            }
            else if(tiles[x+1][y]==Tileset.FLOOR){
                x+=1;
            }
            else{
                polePoints.add(tempPoint=new Position(x,y));
            }
        }
    }
    // 贴瓷砖的辅助方法 对周围九宫格如果不是floor就贴瓷砖
    private TETile[][] Tile(TETile[][] tiles, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && y + j >= 0 && x + i < tiles.length && y + j < tiles[0].length && tiles[x + i][y + j] == Tileset.NOTHING) {
                    tiles[x + i][y + j] = Tileset.WALL;
                }
            }
        }
        return tiles;
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        worldVersionFirst test = new worldVersionFirst();
        randomTiles = test.playWithInputString("n12355sswwda");
        ter.renderFrame(randomTiles);
    }
}
