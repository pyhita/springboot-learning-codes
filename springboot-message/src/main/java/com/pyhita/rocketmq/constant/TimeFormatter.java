package com.pyhita.rocketmq.constant;

import java.time.format.DateTimeFormatter;

public interface TimeFormatter {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss");
}
