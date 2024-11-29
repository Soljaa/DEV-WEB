package daniel.org.Finance_app_api.auth;

import daniel.org.Finance_app_api.enumeration.Role;

public record UserRecord(String username, String password, Role role) {
}
