package git.codeminds.temporaly.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:47
 */
@Data
public class UserInfo {

    private List<String> names;

    private List<String> lastNames;

    // We can add more fields later.
}
