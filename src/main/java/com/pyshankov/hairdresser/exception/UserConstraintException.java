package com.pyshankov.hairdresser.exception;

/**
 * Created by pyshankov on 16.10.2016.
 */
public class UserConstraintException extends RuntimeException {
   public  UserConstraintException(String message){
        super(message);
    }
}
