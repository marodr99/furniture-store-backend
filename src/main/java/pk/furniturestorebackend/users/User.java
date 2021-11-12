package pk.furniturestorebackend.users;

import pk.furniturestorebackend.database.users.UserRole;

public record User(String email, String password,
                   UserRole role) {
}
