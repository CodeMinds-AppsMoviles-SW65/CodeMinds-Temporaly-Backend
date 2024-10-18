package git.codeminds.temporaly.dto.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:35
 */
@Getter
@AllArgsConstructor
@ToString
public class EmailMessageAttachmentResponse {

    private String fileName;

    private String contentType;

    private int size;

    private String downloadUrl;

}
