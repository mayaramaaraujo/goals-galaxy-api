package com.goalsgalaxyapi.domain.model;

public enum Category {
    HEALTH,
    EDUCATION,
    FINANCE,
    CAREER,
    RELATIONSHIP,
    PERSONAL_DEVELOPMENT,
    NOT_DEFINED;

    public static Category fromString(String text) {
        switch (text.toUpperCase()) {
            case "HEALTH":
                return HEALTH;
            case "EDUCATION":
                return EDUCATION;
            case "FINANCE":
                return FINANCE;
            case "CAREER":
                return CAREER;
            case "RELATIONSHIP":
                return RELATIONSHIP;
            case "PERSONAL_DEVELOPMENT":
                return PERSONAL_DEVELOPMENT;
            default:
                return NOT_DEFINED;
        }
    }

}
