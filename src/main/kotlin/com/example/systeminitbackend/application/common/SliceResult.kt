package com.example.systeminitbackend.application.common

import org.springframework.data.domain.Slice

data class SliceResult<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val hasNext: Boolean,
) {
    companion object {
        fun <E : Any, T> from(slice: Slice<E>, mapper: (E) -> T): SliceResult<T> = SliceResult(
            content = slice.content.map(mapper),
            page = slice.number,
            size = slice.size,
            hasNext = slice.hasNext(),
        )
    }
}
