package io.github.dattebayorob.supermarketlist.exception;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ErrorCode {
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String GENERIC_BUSINESS_ERROR = "GENERIC_BUSINESS_ERROR";
    public static final String DUPLICATED_VALUES_ERROR = "DUPLICATED_VALUES_ERROR";
    public static final String REQUEST_PARAM_ERROR = "REQUEST_PARAM_ERROR";
}
