package byog.Core;

//dault for two-dimensional, three-dimensional class can create by key ward extend
public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    public double getDistance(Position p1) {
        double distance = Math.sqrt(Math.pow((this.getX() - p1.getX()), 2) + Math.pow((this.getY() - p1.getY()), 2));
        return distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
