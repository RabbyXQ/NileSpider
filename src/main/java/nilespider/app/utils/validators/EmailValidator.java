package nilespider.app.utils.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private final String email;

    public EmailValidator(String email) {
        this.email = email;
    }

    public boolean isEmail() {
        // Regular expression for a valid email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static void main(String[] args) {
        String validEmail = "test@example.com";
        String invalidEmail = "invalid-email";

        EmailValidator validEmailValidator = new EmailValidator(validEmail);
        EmailValidator invalidEmailValidator = new EmailValidator(invalidEmail);

        System.out.println("Is valid email: " + validEmailValidator.isEmail()); // Should return true
        System.out.println("Is valid email: " + invalidEmailValidator.isEmail()); // Should return false
    }
}
