package com.cosmocats.intergalacticmarket.exception;

import java.net.URI;

public record ProblemDetailResponse(
        URI type,
        String title,
        int status,
        String detail,
        URI instance
) {}
