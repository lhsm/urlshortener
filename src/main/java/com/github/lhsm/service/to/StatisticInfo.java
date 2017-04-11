package com.github.lhsm.service.to;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

@Value.Immutable
@Serial.Version(1L)
public interface StatisticInfo {

    String getUrl();

    Long getCount();

}
