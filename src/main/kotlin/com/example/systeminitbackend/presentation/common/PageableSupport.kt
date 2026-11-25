package com.example.systeminitbackend.presentation.common

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * 정렬 기준이 같은 행이 페이지 사이에서 중복·누락되지 않도록, 정렬 끝에 id를 붙인다.
 */
fun Pageable.withIdTiebreaker(): Pageable {
    if (sort.getOrderFor(ID) != null) return this
    return PageRequest.of(pageNumber, pageSize, sort.and(Sort.by(Sort.Direction.DESC, ID)))
}

private const val ID = "id"
