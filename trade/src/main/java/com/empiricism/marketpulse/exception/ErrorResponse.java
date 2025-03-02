package com.empiricism.marketpulse.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}