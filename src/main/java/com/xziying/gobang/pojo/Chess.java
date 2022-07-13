package com.xziying.gobang.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Chess {
    int type;
    int x;
    int y;
}
