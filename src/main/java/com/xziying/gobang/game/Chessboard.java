package com.xziying.gobang.game;

import com.xziying.gobang.pojo.Chess;

import java.util.List;

/**
 * 棋盘
 */
public interface Chessboard {
    int NULL = 0;  // 空白
    int BLACK = 1;  // 黑色棋子
    int WHITE = 2;  // 白色棋子

    /**
     * 下棋
     * @param type 棋子颜色 1 黑色 2 白色
     * @param x 横坐标
     * @param y 纵坐标
     * @return 是否胜利
     * 异常 @BeyondBordersException 超出棋盘边界 @NotToPlayException 当前位置不可下
     *
     */
    boolean play(int type, int x, int y);

    List<Chess> getChess();

    int getWidth();
    int getHeight();

    /**
     * 打印棋盘到控制台
     */
    void display();
}
