package com.shinhan.changyo.api.service.account.exception;

import java.util.NoSuchElementException;

public class NoAccountException extends NoSuchElementException {
    public NoAccountException(){

    }

    public NoAccountException(String s){
        super(s);
    }

}
