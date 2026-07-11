package com.rajat.framework.api.model.common;

public final class Pagination {

	private final Integer page;
	private final Integer size;
	private final Long totalElements;
	private final Integer totalPages;
	private final Boolean first;
	private final Boolean last;

	public Pagination(Integer page, Integer size, Long totalElements, Integer totalPages, Boolean first, Boolean last) {
		this.page = page;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.first = first;
		this.last = last;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public Boolean getFirst() {
		return first;
	}

	public Boolean getLast() {
		return last;
	}
}