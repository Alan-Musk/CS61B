package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class Game {
    TERenderer ter = new TERenderer();
    //定义常量
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int MIN_ROOMS = 10;
    public static final int MAX_ROOMS = 20;
    public static final int MIN_ROOM_WIDTH = 2;
    public static final int MAX_ROOM_WIDTH = 6;
    public static final int MIN_ROOM_HEIGHT = 2;
    public static final int MAX_ROOM_HEIGHT = 4;
    private long previousSeed = 0;
    private ArrayList<Position> polePoints = new ArrayList<>(); //方块的极点

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
        //初始化世界
        TETile[][] finalWorldFrame = initializeWorld();
        long seed;
        try {
            seed = analyzeSeed(input);
        } catch (IllegalArgumentException e) {
            seed = 0;
        }
        finalWorldFrame = worldGenerator(finalWorldFrame, seed);
        return finalWorldFrame;
    }

    // 世界初始化
    private TETile[][] initializeWorld() {
        TETile[][] worldFram = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < worldFram.length; x += 1) {
            for (int y = 0; y < worldFram[0].length; y += 1) {
                worldFram[x][y] = Tileset.NOTHING;
            }
        }
        return worldFram;
    }

    // 分析字符串 解析出种子
    private long analyzeSeed(String input) {
        //边界检查
        if (input == null || input.isEmpty() || input.charAt(0) != 'n' && input.charAt(0) != 'l') {
            throw new IllegalArgumentException("Invalid input string");
        }
        // 如果是加载游戏 使用之前的种子
        if (input.charAt(0) == 'l') {
            return previousSeed;
        }
        // 提取种子
        int index = 1;
        StringBuilder number = new StringBuilder("");
        while (index < input.length() && Character.isDigit(input.charAt(index))) {
            number.append(input.charAt(index));
            index += 1;
        }
        if (number.length() == 0) {
            throw new IllegalArgumentException("Seed not found in input string");
        }
        return Long.parseLong(number.toString());
    }

    // 世界生成器
    private TETile[][] worldGenerator(TETile[][] tiles, long seed) {
        // 设置伪随机
        Random random = new Random(seed);
        // 绘制 rooms / floor
        int roomsNumber = RandomUtils.uniform(random, MIN_ROOMS, MAX_ROOMS);
        for (int i = 0; i < roomsNumber; i++) {
            int roomWidth = RandomUtils.uniform(random, MIN_ROOM_WIDTH, MAX_ROOM_WIDTH);
            int roomHeight = RandomUtils.uniform(random, MIN_ROOM_HEIGHT, MAX_ROOM_HEIGHT);
            int starX = RandomUtils.uniform(random, 1, WIDTH - roomWidth - 1);
            int statY = RandomUtils.uniform(random, 1, HEIGHT - roomHeight - 1);

            //绘制房间并同时检测极点
            for (int x = starX; x < starX + roomWidth; x++) {
                for (int y = statY; y < statY + roomHeight; y++) {
                    tiles[x][y] = Tileset.FLOOR;
                }
            }
            scanPolePoint(tiles, starX, statY, roomWidth, roomHeight);
        }
        // 2.通过hashset position来画趋向中心点的hallway 遇到floor就停止连通
        connect(tiles, seed);
        // 贴瓷砖
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (tiles[i][j] == Tileset.FLOOR) {
                    tiles = tile(tiles, i, j);
                }
            }
        }
        return tiles;
    }

    //connect by hallway
    private void connect(TETile[][] tiles, long seed) {
        Random random = new Random(seed);
        for (int i = 0; i < polePoints.size() - 1; i++) {
            Position start = polePoints.get(i);
            Position end = polePoints.get(i + 1);
            boolean direction = RandomUtils.bernoulli(random);
            if (direction) {
                createHallway(tiles, start.getX(), end.getX(), start.getY(), true);
                createHallway(tiles, start.getY(), end.getY(), end.getX(), false);
            } else {
                createHallway(tiles, start.getY(), end.getY(), start.getX(), false);
                createHallway(tiles, start.getX(), end.getX(), end.getY(), true);
            }
        }
    }

    private void createHallway(TETile[][] tiles, int start, int end, int fixed, boolean isHorizontal) {
        for (int i = start; i != end; i += Integer.compare(end, start)) {
            int x = isHorizontal ? i : fixed;
            int y = isHorizontal ? fixed : i;

            if (tiles[x][y] != Tileset.FLOOR) {
                tiles[x][y] = Tileset.FLOOR;
            }
        }
    }

    // 从左下往右,上检测 找到是floor且最接近中心的点
    private void scanPolePoint(TETile[][] tiles, int startX, int startY, int roomWidth, int roomHeight) {
        Position corePoint = new Position(WIDTH / 2, HEIGHT / 2); //中心点
        Position closestPoint = null;
        Double minDistane = Double.MAX_VALUE;
        for (int x = startX; x < startX + roomWidth; x++) {
            for (int y = startY; y < startY + roomHeight; y++) {
                if (tiles[x][y] == Tileset.FLOOR) {
                    Position currentPos = new Position(x, y);
                    double dist = currentPos.getDistance(corePoint);
                    if (dist < minDistane) {
                        minDistane = dist;
                        closestPoint = currentPos;
                    }
                }
            }
        }
        if (closestPoint != null && !polePoints.contains(closestPoint)) {
            polePoints.add(closestPoint);
        }
    }

    // 贴瓷砖的辅助方法 对周围九宫格如果不是floor就贴瓷砖
    private TETile[][] tile(TETile[][] tiles, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && y + j >= 0 && x + i < tiles.length && y + j < tiles[0].length
                        && tiles[x + i][y + j] == Tileset.NOTHING) {
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
        Game test = new Game();
        randomTiles = test.playWithInputString("n12355sswwda");
        ter.renderFrame(randomTiles);
    }
}
