package bstore.bookstore.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class PasswordValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String password = BeanUtils.getProperty(obj, firstFieldName);
            String confirmPassword = BeanUtils.getProperty(obj, secondFieldName);
            return password.equals(confirmPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error while validating password", e);
        }
    }
}
