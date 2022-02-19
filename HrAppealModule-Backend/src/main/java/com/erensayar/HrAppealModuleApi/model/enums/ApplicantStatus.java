package com.erensayar.HrAppealModuleApi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicantStatus {
    TO_BE_EVALUATED(0), // Başvuru başlangıç değeri.
    IMPORTANT(1),       // Ön yüzde dikkat çekici olarak işaretlenebilmesi için.
    POSITIVE(2),        // Görüşme süreçleri başlatıldı ve devam ediyor.
    DONE(3),            // İşe alım geçrekleşti ve arşivlendi olarak kabul edilsin.
    NEGATIVE(4);        // İşe alım süreci sonlandırıldı ve arşivlendi olarak kabul edilsin.

    private final Integer statusId;

}
