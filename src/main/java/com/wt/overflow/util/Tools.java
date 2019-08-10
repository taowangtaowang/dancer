package com.wt.overflow.util;

import java.util.List;
import java.util.function.Consumer;

public class Tools {

    /**
     * 数据库分批操作
     *
     * @param voList 操作的总集合
     * @param subset 批次大小 例如 1000
     * @param jdbc   匿名函数
     * @param <T>    集合类型
     */
    public static <T> void splitBatch(List<T> voList, int subset, Consumer<List<T>> jdbc) {
        if (voList.isEmpty()) {
            return;
        }
        int size = voList.size();
        int batchCount = size / subset;
        if (size % subset != 0) {
            batchCount += 1;
        }

        for (int i = 0; i < batchCount; i++) {
            int start = subset * i;
            int end = subset * (i + 1);
            if (size >= end) {
                jdbc.accept(voList.subList(start, end));
            } else {
                jdbc.accept(voList.subList(start, size));
            }
        }
    }


}

