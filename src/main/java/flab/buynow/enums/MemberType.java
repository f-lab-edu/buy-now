package flab.buynow.enums;

public enum MemberType {
    ADMIN("Y"),
    MEMBER("N");

    private String adminYn;

    MemberType(String adminYn) {
        this.adminYn = adminYn;
    }

    public String getAdminYn() {
        return adminYn;
    }
}
