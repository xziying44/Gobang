package com.xziying.gobang.factory;

import com.xziying.gobang.pojo.Chess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SingleChessFactory implements ChessFactory{
    Map<String, Chess> map = new ConcurrentHashMap<>();

    /**
     * 根据位置参数转化为位置文本信息用作索引
     *
     * @param type 棋子颜色 1 黑色 2 白色
     * @param x    横坐标
     * @param y    纵坐标
     * @return 返回的文本位置信息
     */
    @Override
    public String getPositionInfo(int type, int x, int y) {
        return String.valueOf(type) +
                '-' +
                x +
                '-' +
                y;
    }

    /**
     * 根据位置参数获取棋子对象
     *
     * @param type 棋子颜色 1 黑色 2 白色
     * @param x    横坐标
     * @param y    纵坐标
     * @return 棋子对象
     */
    @Override
    public Chess getChess(int type, int x, int y) {
        String position = getPositionInfo(type, x, y);
        // 双重检查创建对象
        if (!map.containsKey(position)){
            synchronized (SingleChessFactory.class){
                if (!map.containsKey(position)){
                    map.put(position,
                            new Chess()
                                    .setType(type)
                                    .setX(x)
                                    .setY(y)
                    );
                }
            }
        }
        return map.get(position);
    }
}
