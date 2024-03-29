package mateusz.michal.chat.Authorization.Model;

import mateusz.michal.chat.Structure.RespondStructure.IMyEnum;

public enum JwtAuthenticationErrorCode implements IMyEnum {
    NAME_NULL,
    PASSWORD_NULL,
    NAME_MISSING,
    PASSWORD_MISSING,
    BAD_CREDENTIALS
}
