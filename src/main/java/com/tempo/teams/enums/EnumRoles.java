package com.tempo.teams.enums;

public enum EnumRoles {
    DEVELOPER("Developer"),
    PRODUCTOWNER("Product Owner"),
    TESTER("Tester");

    EnumRoles(String title) {
        this.title = title;
    }
    private String title;

    public String getTitle() {
        return title;
    }
}
