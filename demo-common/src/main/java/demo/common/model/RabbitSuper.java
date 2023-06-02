package demo.common.model;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class RabbitSuper implements Serializable {

    private String bh;

    private String name;

    private String title;

    private String type;

    private String sjrq;

    private LocalDateTime cjsj;
}
