package bstore.bookstore.validation.isbn;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String ISBN_PATTERN =
            "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.matches(ISBN_PATTERN);
    }
}
