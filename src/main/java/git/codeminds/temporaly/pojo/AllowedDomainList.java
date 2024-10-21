package git.codeminds.temporaly.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 21/10/24 @ 12:24
 */
@Data
public class AllowedDomainList {
    private List<String> secureDomains;
}
