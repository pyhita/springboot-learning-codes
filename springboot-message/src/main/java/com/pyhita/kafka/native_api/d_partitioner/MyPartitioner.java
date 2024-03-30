package com.pyhita.kafka.native_api.d_partitioner;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @Author pyhita
 * @Date 2024/3/30
 */
// 自定义分区器
@Slf4j
public class MyPartitioner implements Partitioner {


    // 自定义分区算法
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        log.info("自定义分区算法， {}", s);
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
