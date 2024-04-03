package org.weslleycabral.cadastro_pessoas.services.exceptions;

public class EmptyListException extends RuntimeException{
    public EmptyListException(String msg) {
        super(msg);
    }
}
