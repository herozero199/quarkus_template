package com.odn.requests.requestValidations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InboundProductsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InboundProductsValidation {
    String message() default "Duplicate Product Id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
