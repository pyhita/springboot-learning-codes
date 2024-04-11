package com.pyhita.constant;

public interface MqConstant {

    String NAME_SERVER = "192.168.233.8:9876";
    String MAC_NAME_SERVER = "localhost:9876";
    String BOOTSTRAP_SERVER = "localhost:9092";
    String CLUSTER_BOOTSTRAP_SERVER = "localhost:19092,localhost:29092,localhost:39092";
    String CLUSTER_ZK_BOOTSTRAP_SERVER = "localhost:9093,localhost:9094";
    String CLUSTER_KRAFT_BOOTSTRAP_SERVER = "localhost:9093,localhost:9094";
}
