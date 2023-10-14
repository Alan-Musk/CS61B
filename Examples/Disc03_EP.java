public class Disc03_EP {
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int i = 0; i < x.length; i++) {
            totalLength += x[i].length;
        }
        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int j = 0; j < x.length; j++) {
            for (int k = 0; k < x[j].length; k++, aIndex++) {
                a[aIndex] = x[j][k];
            }
        }
        return a;
    }


    public static void main(String[] args) {

        int[][] test = new int[][]{{1, 2, 3}, {}, {7, 8}};
        int[] result = flatten(test);
    }
}
