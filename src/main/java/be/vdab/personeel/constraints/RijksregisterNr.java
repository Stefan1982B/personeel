package be.vdab.personeel.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.springframework.messaging.handler.annotation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = RijksregisterNrValidator.class)
public @interface RijksregisterNr {
	String message() default "{be.vdab.personeel.constraints.RijksregisterNr.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
