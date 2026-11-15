package com.cosmocats.intergalacticmarket.dto;

import java.util.List;

public record PageResponseDto<T>(List<T> content, int page, int size, long totalElements, int totalPages, boolean hasNext) {

    public static <T> PageResponseDto<T> of(List<T> content, int page, int size, long totalElements) {
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean hasNext = page < totalPages - 1;
        return new PageResponseDto<>(List.copyOf(content), page, size, totalElements, totalPages, hasNext);
    }
}
