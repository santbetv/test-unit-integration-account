package org.example.junitapp.infrastructure.exception;


import lombok.Getter;
import lombok.Setter;

/**
*
* @author Santiago Betancur
*/

@Getter
@Setter
public class BussinesRuleException extends Exception{
	
    private long id;
    private String code;
    private String type;


    public BussinesRuleException(String type) {
        this.type = type;
    }

    public BussinesRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinesRuleException(Long id, String code, String message) {
        super(message);
        this.id = id;
        this.code = code;
    }

    public BussinesRuleException(String type,String code, String message) {
        super(message);
        this.type = type;
        this.code = code;
    }
    



}
