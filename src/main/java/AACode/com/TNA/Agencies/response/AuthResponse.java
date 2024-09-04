package AACode.com.TNA.Agencies.response;

import AACode.com.TNA.Agencies.model.Enums.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private USER_ROLE role;
}
