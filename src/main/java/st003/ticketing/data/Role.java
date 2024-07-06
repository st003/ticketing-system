package st003.ticketing.data;

/**
 * Enum for defining AppUser Roles
 */
public class Role {

    // Note: decided against enum becuase it adds uneeded complexity with
    // databases, template rendering, Spring Security Authorities

    public static final int CUSTOMER = 1;
    public static final int AGENT    = 2;
    public static final int ADMIN    = 3;

    /**
     * Return a role's name.
     *
     * @param role An int representing the numeric identifier for the Role
     * @return     The role name, or empty string if the roleID is unknown
     */
    public static String getName(int num) {
        switch (num) {
            case CUSTOMER:
                return "CUSTOMER";
            case AGENT:
                return "AGENT";
            case ADMIN:
                return "ADMIN";
            default:
                // TODO - add logging here?
                return "";
        }
    }

    /**
     * Return a role's name using the Spring Security Authorities naming format
     *
     * @param role An int representing the numeric identifier for the Role
     * @return     The Spring Security Authority name, or empty string if the roleID is unknown
     */
    public static String getAuthorizationName(int num) {
        switch (num) {
            case CUSTOMER:
                return "ROLE_CUSTOMER";
            case AGENT:
                return "ROLE_AGENT";
            case ADMIN:
                return "ROLE_ADMIN";
            default:
                // TODO - add logging here?
                return "";
        }
    }
}
