package payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class EmailCertifiedRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
