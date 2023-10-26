package org.example.junitapp.domain;

import org.example.junitapp.infrastructure.exception.BussinesRuleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void test_name_account() {
        //Arrange
        String expected = "Sebastian";

        //Act
        Account data= new Account("Sebastian",new BigDecimal("456454"));
        Account account = Account.builder().person("Sebastian").balance(new BigDecimal("10000.123456")).build();

        //Assert
        assertNotNull(data.getBalance());
        assertEquals(expected,data.getPerson());
    }

    @Test
    void test_balance_account(){

        //Arrange
        BigDecimal balance = new BigDecimal(456454);

        //Act
        Account data= new Account("Sebastian",new BigDecimal("456454"));


        //Assert
        assertNotNull(data.getBalance());
        assertEquals(balance,data.getBalance());
        assertFalse(data.getBalance().compareTo(BigDecimal.ZERO)<0);
        assertTrue(data.getBalance().compareTo(BigDecimal.ZERO)>0);
    }

    @Test
    void test_object_account() {

        //Act
        Account data= new Account("Sebastian",new BigDecimal("456454"));
        Account data2= new Account("Sebastian",new BigDecimal("456454"));

        //Assert
        assertTrue(data.equals(data2));
    }

    @Test
    void test_subtract_account() throws BussinesRuleException {
        //Act
        Account data= new Account("Sebastian",new BigDecimal("1000.123"));

        data.subtractFromAccount(new BigDecimal(100));

        //Assert
        assertNotNull(data.getBalance());
        assertEquals(900, data.getBalance().intValue());

        assertEquals("900.123", data.getBalance().toPlainString());

    }

    @Test
    void test_add_account() {
        //Act
        Account data= new Account("Sebastian",new BigDecimal("900.123"));

        data.addFromAccount(new BigDecimal(100));

        //Assert
        assertNotNull(data.getBalance());
        assertEquals(1000, data.getBalance().intValue());

        assertEquals("1000.123", data.getBalance().toPlainString());

    }

    @Test
    void test_money_insufficient_exception_account() {

        //Arrange
        String expected = "Dinero insuficiente";

        //Act
        Account data= new Account("Sebastian",new BigDecimal("1000.123"));

        BussinesRuleException exception = assertThrows(BussinesRuleException.class, ()-> {
           data.subtractFromAccount(new BigDecimal(1500));
        });

        String present =  exception.getType();

        //Assert
        assertEquals(present,expected);


    }
}