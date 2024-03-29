package com.aeternal.clients.models.utils;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class CompanySubjectExchange implements Serializable {

    @NonNull
    private String companyId;

    @NonNull
    private String companyName;

}
