package pk.furniturestorebackend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.furniturestorebackend.database.users.UserEntity;
import pk.furniturestorebackend.database.users.UserRepository;
import pk.furniturestorebackend.database.users.UserRole;
import pk.furniturestorebackend.exceptions.UserAlreadyExistException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    public static final Pattern EMAIL_ADDRESS = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    );
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{4,24}");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String createNewUser(User user) {
        validateEmail(user.email());
        validatePassword(user.password());
        Optional<UserEntity> entity = userRepository.findById(user.email());
        if (entity.isPresent())
            throw new UserAlreadyExistException("Email is already used");
        UserEntity userEntity = createUserEntity(user, UserRole.USER);
        return userRepository.save(userEntity).getEmail();
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email can not be null or blank");
        Matcher matcher = EMAIL_ADDRESS.matcher(email);
        if (!matcher.matches())
            throw new IllegalArgumentException("Email does not have proper structure");
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password can not be null or blank");
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        if (!matcher.matches())
            throw new IllegalArgumentException("Password does not have proper structure");
    }

    private UserEntity createUserEntity(User user, UserRole role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.email());
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        userEntity.setRole(role);
        return userEntity;
    }
}
