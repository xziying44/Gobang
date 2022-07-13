package com.xziying.gobang.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 五子棋配置对象
 */
@Data
@Accessors(chain = true)
public class GameConfig {
    // 五子棋棋盘大小配置
    int width;
    int height;
}
