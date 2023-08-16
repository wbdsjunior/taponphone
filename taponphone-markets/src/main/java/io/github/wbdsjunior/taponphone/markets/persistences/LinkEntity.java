package io.github.wbdsjunior.taponphone.markets.persistences;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LinkEntity {

    private String rel;
    private String method;
    private String href;
}
