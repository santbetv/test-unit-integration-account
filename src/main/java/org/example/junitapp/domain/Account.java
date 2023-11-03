package org.example.junitapp.domain;

import lombok.*;
import org.example.junitapp.infrastructure.exception.BussinesRuleException;
import org.example.junitapp.infrastructure.exception.ValidateArgument;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String person;
    private BigDecimal balance;//saldo


    public void subtractFromAccount(BigDecimal value) throws BussinesRuleException {
        BigDecimal newValue = this.balance.subtract(value);
        ValidateArgument.validateValueSubtractCorrect(newValue,value,"Dinero insuficiente");
        this.balance=newValue;
    }

    public void addFromAccount(BigDecimal value){
        this.balance = this.balance.add(value);
    }
}


