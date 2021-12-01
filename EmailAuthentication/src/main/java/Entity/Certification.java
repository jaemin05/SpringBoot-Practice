package Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash
public class Certification implements Serializable {
    @Id
    @Indexed
    private String email;

    private String code;
    private Certified cerified;

    public Certification updateCretified(Certified cerified) {
        this.cerified = cerified;
        return this;
    }

    public Certification updateCode(String code) {
        this.code = code;
        return this;
    }


}
