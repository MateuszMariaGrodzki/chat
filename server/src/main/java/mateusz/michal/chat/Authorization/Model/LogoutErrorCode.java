package mateusz.michal.chat.Authorization.Model;

import mateusz.michal.chat.Structure.RespondStructure.IMyEnum;

public enum LogoutErrorCode implements IMyEnum {
    COOKIES_NULL,
    TOKEN_MISSING
}
