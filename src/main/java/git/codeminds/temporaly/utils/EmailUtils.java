package git.codeminds.temporaly.utils;

import java.util.Random;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:02
 */
public class EmailUtils {

    private EmailUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Random RANDOM = new Random();

    public static String generateRandomEmailUsername() {
        StringBuilder stringBuilder = new StringBuilder();
        int length = 10 +  RANDOM.nextInt(10);

        for (int i = 0; i < length; i++) {
            char c = (char) (RANDOM.nextInt(26) + 'a');
            stringBuilder.append(c);
        }
        String word = "temporaly";
        int index = RANDOM.nextInt(stringBuilder.length());
        stringBuilder.insert(index, word.substring(0, 3));
        return stringBuilder.toString();
    }
}
