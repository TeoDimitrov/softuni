package com.residentevildemo.annotations;

import com.residentevildemo.annotations.IsInTheFuture;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class IsInTheFutureValidator implements ConstraintValidator<IsInTheFuture, Date> {
   public void initialize(IsInTheFuture constraint) {
   }

   public boolean isValid(Date date, ConstraintValidatorContext context) {
      boolean isInTheFuture = false;
      if(date == null){
         return isInTheFuture;
      }

      Date currentDate = new Date();
      isInTheFuture= date.after(currentDate);
      return isInTheFuture;
   }
}
