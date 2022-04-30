package com.tanvi.ref.mvcref.product.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException  extends RuntimeException{

    private static final long serialVersionUID = 1 ;

   public ResourceNotFoundException(String errorMessage){

        super(errorMessage);
    }

    public ResourceNotFoundException(String errorMessage,Throwable throwable){

        super(errorMessage,throwable);
    }
}
