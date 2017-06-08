package com.residentevil.customValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class PresentOrFutureValidator implements ConstraintValidator<PresentOrFuture, Date> {
    @Override
    public void initialize(PresentOrFuture presentOrFuture) {

    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Date today = new Date();

        if(date== null){
            return false;
        }

        return date.after(today);
    }
}
