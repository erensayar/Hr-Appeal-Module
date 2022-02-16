package com.erensayar.HrWebApplicationApi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AllowedFileTypes {
    pdf(0);

    private final Integer id;
}