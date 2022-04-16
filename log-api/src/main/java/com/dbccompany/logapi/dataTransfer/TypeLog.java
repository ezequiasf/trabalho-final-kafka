package com.dbccompany.logapi.dataTransfer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeLog {
    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private final String typeLog;
}