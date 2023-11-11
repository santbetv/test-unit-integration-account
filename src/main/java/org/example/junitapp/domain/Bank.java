package org.example.junitapp.domain;

import lombok.*;
import org.example.junitapp.infrastructure.exception.BussinesRuleException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Bank {

    private String name;
    private List<Account>  accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }


    public void transfer(Account origin, Account destination, BigDecimal amount) throws BussinesRuleException {
        origin.subtractFromAccount(amount);
        destination.addFromAccount(amount);
    }

    public void addAccount(Account account){
        accounts.add(account);
        account.setBank(this);
    }

}
