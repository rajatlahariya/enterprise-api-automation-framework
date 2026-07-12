package com.rajat.framework.testgroup;

public final class TestGroups {

    private TestGroups() {
        // Prevent object creation
    }

    // Execution scope
    public static final String UNIT = "unit";
    public static final String INTEGRATION = "integration";
    public static final String SMOKE = "smoke";
    public static final String REGRESSION = "regression";

    // Capability scope
    public static final String AUTHENTICATION = "authentication";
    public static final String CRUD = "crud";
    public static final String SEARCH = "search";
    public static final String SCHEMA = "schema";
    public static final String NEGATIVE = "negative";
}
