package com.navercorp.pinpoint.common.topo.domain;

public enum XNodeType {
    // common java
    UNDEFINED((short) -1, "UNDEFINED"),
    JAVA((short) 0, "JAVA"),
    UNKNOWN((short) 1, "UNKNOWN"),
    USER((short) 2, "USER"),
    PYTHON((short) 10, "PYTHON"),
    GO((short) 11, "GO"),

    // DB
    MYSQL((short) 21, "MYSQL"),
    MSSQL((short) 22, "MSSQL"),
    ORACLE((short) 23, "ORACLE"),
    CUBRID((short) 24, "CUBRID"),
    POSTGRESQL((short) 25, "POSTGRESQL"),
    CASSANDRA((short) 26, "CASSANDRA"),
    UNKNOWN_DB((short) 20, "UNKNOWN_DB"),
    SCALA((short) 1906, "SCALA"),

    // Cache Client
    RABBITMQ((short) 830, "RABBITMQ"),
    ACTIVEMQ((short) 831, "ACTIVEMQ_CLIENT"),
    KAFKA((short) 835, "KAFKA");

    private final short code;
    private final String desc;

    XNodeType(short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public short getCode() {
        return code;
    }
}
