package com.xziying.gobang.factory;

import com.xziying.gobang.pojo.Chess;

/**
 *  棋子工厂 享元池
 */
public interface ChessFactory {

    /**
     * 根据位置参数转化为位置文本信息用作索引
     * @param type 棋子颜色 1 黑色 2 白色
     * @param x    横坐标
     * @param y    纵坐标
     * @return 返回的文本位置信息
     */
    String getPositionInfo(int type, int x, int y);

    /**
     * 根据位置参数获取棋子对象
     * @param type 棋子颜色 1 黑色 2 白色
     * @param x    横坐标
     * @param y    纵坐标
     * @return 棋子对象
     */
    Chess getChess(int type, int x, int y);
}
