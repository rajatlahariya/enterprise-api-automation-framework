package com.rajat.framework.api.model.user;

public final class UserSearchCriteria {

	private final String firstName;
	private final String username;
	private final String email;
	private final String role;
	private final Boolean isActive;
	private final Integer minAge;
	private final Integer maxAge;
	private final Integer page;
	private final Integer size;
	private final String sort;

	private UserSearchCriteria(Builder builder) {
		this.firstName = builder.firstName;
		this.username = builder.username;
		this.email = builder.email;
		this.role = builder.role;
		this.isActive = builder.isActive;
		this.minAge = builder.minAge;
		this.maxAge = builder.maxAge;
		this.page = builder.page;
		this.size = builder.size;
		this.sort = builder.sort;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public String getSort() {
		return sort;
	}

	public static final class Builder {

		private String firstName;
		private String username;
		private String email;
		private String role;
		private Boolean isActive;
		private Integer minAge;
		private Integer maxAge;

		private Integer page = 0;
		private Integer size = 10;
		private String sort = "id,asc";

		private Builder() {
		}

		public Builder firstName(String firstName) {
			this.firstName = normalize(firstName);
			return this;
		}

		public Builder username(String username) {
			this.username = normalize(username);
			return this;
		}

		public Builder email(String email) {
			this.email = normalize(email);
			return this;
		}

		public Builder role(String role) {
			this.role = normalize(role);
			return this;
		}

		public Builder isActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}

		public Builder minAge(Integer minAge) {
			this.minAge = minAge;
			return this;
		}

		public Builder maxAge(Integer maxAge) {
			this.maxAge = maxAge;
			return this;
		}

		public Builder page(Integer page) {
			this.page = page;
			return this;
		}

		public Builder size(Integer size) {
			this.size = size;
			return this;
		}

		public Builder sort(String sort) {
			this.sort = normalize(sort);
			return this;
		}

		public UserSearchCriteria build() {
			validate();
			return new UserSearchCriteria(this);
		}

		private void validate() {
			if (page == null || page < 0) {
				throw new IllegalArgumentException("Page must be zero or greater.");
			}

			if (size == null || size <= 0) {
				throw new IllegalArgumentException("Page size must be greater than zero.");
			}

			if (minAge != null && minAge < 0) {
				throw new IllegalArgumentException("Minimum age cannot be negative.");
			}

			if (maxAge != null && maxAge < 0) {
				throw new IllegalArgumentException("Maximum age cannot be negative.");
			}

			if (minAge != null && maxAge != null && minAge > maxAge) {
				throw new IllegalArgumentException("Minimum age cannot be greater than maximum age.");
			}

			if (sort == null) {
				sort = "id,asc";
			}
		}

		private static String normalize(String value) {
			if (value == null || value.isBlank()) {
				return null;
			}

			return value.trim();
		}
	}
}