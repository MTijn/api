package nl.martijnklene.api.infrastructure.annotation;

import nl.martijnklene.api.application.validator.UniqueTitleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTitleValidator.class)
public @interface UniqueTitle {
    String message() default "Title is not unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
