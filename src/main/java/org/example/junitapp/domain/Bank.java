package org.example.junitapp.domain;

import lombok.*;
import org.example.junitapp.infrastructure.exception.BussinesRuleException;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private String name;

    public void transfer(Account origin, Account destination, BigDecimal amount) throws BussinesRuleException {
        origin.subtractFromAccount(amount);
        destination.addFromAccount(amount);
    }

}
