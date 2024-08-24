package git.codeminds.temporaly.utils;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:50
 */
public class UserUtils {

    private UserUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateRandomUserName(List<String> names, List<String> lastNames) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            stringBuilder.append(name.substring(0, 3).toLowerCase()).append(".");
        }
        for (String lastName : lastNames) {
            stringBuilder.append(lastName.substring(0, 3).toLowerCase()).append(".");
        }
        return stringBuilder.toString();
    }
}
