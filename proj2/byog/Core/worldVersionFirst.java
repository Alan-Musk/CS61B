package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class worldVersionFirst {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private int previousSeed = 0;

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

    private TETile[][] worldGenerator(TETile[][] tiles, long seed) {
        // 设置伪随机
        Random RANDOM = new Random(seed);
        // 绘制 rooms / floor
        int roomsNumber = RandomUtils.uniform(RANDOM, 80, 100);
        for (int i = 0; i < roomsNumber; i++) {
            // 设置 room的 高 宽 渲染起始点
            rooms room = new rooms(RandomUtils.uniform(RANDOM, 1, 5), RandomUtils.uniform(RANDOM, 1, 5), RandomUtils.uniform(RANDOM, 1, WIDTH - 6), RandomUtils.uniform(RANDOM, 1, HEIGHT - 6));
            for (int x = room.getCoreX(); x < room.getCoreX() + room.getWidth(); x++) {
                for (int y = room.getCoreY(); y < room.getCoreY() + room.getHeight(); y++) {
                    tiles[x][y] = Tileset.FLOOR;
                }
            }
        }
        //将各个区块连接在一起

        // 贴瓷砖
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (tiles[i][j] == Tileset.FLOOR) {
                    tiles = Tile(tiles, i, j);
                }
            }
        }
        return tiles;
    }

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
