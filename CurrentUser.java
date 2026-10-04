public class CurrentUser {

    // =========================================================
    // CURRENT USER DETAILS
    // =========================================================

    private static String username;

    private static String name;


    // =========================================================
    // SET CURRENT USER
    // =========================================================

    public static void setUser(
            String username,
            String name
    ) {

        CurrentUser.username = username;

        CurrentUser.name = name;
    }


    // =========================================================
    // GET USERNAME
    // =========================================================

    public static String getUsername() {

        return username;
    }


    // =========================================================
    // GET NAME
    // =========================================================

    public static String getName() {

        return name;
    }


    // =========================================================
    // CHECK WHETHER USER IS LOGGED IN
    // =========================================================

    public static boolean isLoggedIn() {

        return username != null
                && !username.isEmpty();
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    public static void logout() {

        username = null;

        name = null;
    }
}