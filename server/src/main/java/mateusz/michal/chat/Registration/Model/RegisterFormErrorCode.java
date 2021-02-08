package mateusz.michal.chat.Registration.Model;

import mateusz.michal.chat.Structure.RespondStructure.IMyEnum;

public enum RegisterFormErrorCode implements IMyEnum {
    NAME_MISSING,
    EMAIL_MISSING,
    PASSWORD_MISSING,
    NAME_OCCUPIED,
    EMAIL_OCCUPIED,
    NAME_NULL,
    EMAIL_NULL,
    PASSWORD_NULL,
    NAME_INCORRECT,
    EMAIL_INCORRECT,
    WEAK_PASSWORD,
    REGISTERED
}
