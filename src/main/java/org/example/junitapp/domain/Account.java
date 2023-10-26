package org.example.junitapp.domain;

import lombok.*;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String person;
    private BigDecimal balance;//saldo

    public void subtractFromAccount(BigDecimal value){
        this.balance = this.balance.subtract(value);
    }

    public void addFromAccount(BigDecimal value){
        this.balance = this.balance.add(value);
    }
}


