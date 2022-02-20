package com.erensayar.HrAppealModuleApi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MailTemplateName {
    ThanksForYourInterest(0),
    InviteToInterview(1),
    NegativeResponse(2);

    private final int templateNo;


}
