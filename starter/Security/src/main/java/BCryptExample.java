import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptExample {

    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // Call a built-in method
        String securePassword = bCryptPasswordEncoder.encode("mySaltedPassword");
    }
}
