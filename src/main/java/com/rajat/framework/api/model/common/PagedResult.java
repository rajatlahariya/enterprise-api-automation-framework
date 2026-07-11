package com.rajat.framework.api.model.common;

import java.util.Collections;
import java.util.List;

public final class PagedResult<T> {

	private final List<T> content;
	private final Pagination pagination;

	public PagedResult(List<T> content, Pagination pagination) {

		this.content = content == null ? Collections.emptyList() : List.copyOf(content);

		this.pagination = pagination;
	}

	public List<T> getContent() {
		return content;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public boolean isEmpty() {
		return content.isEmpty();
	}

	public int getNumberOfElements() {
		return content.size();
	}
}