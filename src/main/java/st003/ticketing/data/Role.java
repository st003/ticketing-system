package st003.ticketing.data;

/**
 * Enum for defining AppUser Roles
 */
public enum Role {

    ROLE_CUSTOMER,
    ROLE_AGENT,
    ROLE_ADMIN;

    /**
     * Return the name of the enum with the "ROLE_" prefix
     * stripped out.
     *
     * @return A String
     */
    public String getShortName() {
        String strName = toString();
        return strName.substring(5);
    }
}
