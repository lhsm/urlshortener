package com.github.lhsm.service.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@Serial.Version(1L)
public interface AccountInfo {

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getPassword();

    boolean isSuccess();

    String getDescription();


}
