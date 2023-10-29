public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString string = new synthesizer.GuitarString(110.0);
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    string = new synthesizer.GuitarString(440 * Math.pow(2.0, (keyboard.indexOf(key) - 24) / 12.0));
                    string.pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = string.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);
            System.out.println(sample);

            /* advance the simulation of each guitar string by one step */
            string.tic();
        }
    }
}
