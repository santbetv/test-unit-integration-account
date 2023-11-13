package org.example.junitapp.domain;

import org.example.junitapp.infrastructure.exception.BussinesRuleException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
class AccountTest {

    private Account data;

    @BeforeAll
    static void beforeAll() {
        System.out.println("iniciando el test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("finalizando el test");
    }

    @BeforeEach//Antes de cada  metodo se ejecuta
    void setUp() {
        data= new Account("Sebastian",new BigDecimal("456454"));
    }


    @AfterEach//Despues de cada  metodo se ejecuta
    void tearDown() {

    }

    @Test
    @DisplayName("Probando  el nombre de la cuenta")
    void test_name_account() {
        //Arrange
        String expected = "Sebastian";

        //Act

        Account account = Account.builder().person("Sebastian").balance(new BigDecimal("10000.123456")).build();

        //Assert
        assertNotNull(data.getBalance());
        assertEquals(expected,data.getPerson());
    }

    @Test
    @DisplayName("Probando  el saldo de la cuenta")
    void test_balance_account(){

        //Arrange
        BigDecimal balance = new BigDecimal(456454);

        //Act


        //Assert
        assertNotNull(data.getBalance());
        assertEquals(balance,data.getBalance());
        assertFalse(data.getBalance().compareTo(BigDecimal.ZERO)<0);
        assertTrue(data.getBalance().compareTo(BigDecimal.ZERO)>0);
    }

    @Test
    @DisplayName("Probando  objetos iguales de cuenta")
    void test_object_account() {

        //Act
        Account data2= new Account("Sebastian",new BigDecimal("456454"));

        //Assert
        assertTrue(data.equals(data2));
    }

    @Test
    @DisplayName("Probando  el retido de la cuenta")
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
    @DisplayName("Probando  el abonos en la cuenta")
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
    @DisplayName("Probando  el saldo insufuciente en cuenta")
    void test_money_insufficient_exception_account() {

        //Arrange
        String expected = "Dinero insuficiente";
        Account data= new Account("Sebastian",new BigDecimal("1000.123"));

        //Act
        BussinesRuleException exception = assertThrows(BussinesRuleException.class, ()-> {
           data.subtractFromAccount(new BigDecimal(1500));
        });

        String present =  exception.getType();

        //Assert
        assertEquals(present,expected);


    }

    @Test
    @Disabled
    @DisplayName("Probando  transferencia de dinero para cuenta")
    void test_transfer_money_account() throws BussinesRuleException {

        fail();
        //Arrange
        Account data1= new Account("Andres",new BigDecimal("2500"));
        Account data2= new Account("Sebastian",new BigDecimal("1500.8989"));

        //Act

        Bank bank = new Bank();
        bank.setName("Banco del estado");
        bank.transfer(data2,data1,new BigDecimal(500));

        //Assert
        assertEquals("1000.8989", data2.getBalance().toPlainString());
        assertEquals("3000", data1.getBalance().toPlainString());
    }

    @Test
    @DisplayName("Probando  relaciones cuenta y banco con asserALL.")
    void test_relation_bank_account() throws  BussinesRuleException {

        //Arrange
        Account data1= new Account("Andres",new BigDecimal("2500"));
        Account data2= new Account("Sebastian",new BigDecimal("1500.8989"));

        //Act

        Bank bank = new Bank();
        bank.addAccount(data1);
        bank.addAccount(data2);

        bank.setName("Banco del estado");
        bank.transfer(data2,data1,new BigDecimal(500));

        //Assert
        assertAll(() -> {
            assertEquals(2, bank.getAccounts().size(), () -> "La cantidad de bancos NO es lo que se esperaba");
            },
        () -> assertEquals("Banco del estado", data1.getBank().getName(), () -> "El nombre del banco NO es lo que se esperaba"),
        () -> assertEquals("Andres",bank.getAccounts().stream().filter(name -> name.getPerson().equals("Andres")).findFirst().get().getPerson(), () -> "El nombre de la persona de la cuenta en el bo NO es lo que se esperaba"),
        () -> assertTrue(bank.getAccounts().stream().anyMatch((name -> name.getPerson().equals("Sebastian"))),() -> "El nombre de la persona de la cuenta en el bo NO es lo que se esperaba"));
    }

    @RepeatedTest(5)
    @EnabledOnOs({OS.LINUX,OS.MAC})
    @DisplayName("Probando  solo linux y mac")
    void test_only_linux_mac(){
        System.out.println("Probando  solo linux y mac.");
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    @DisplayName("Probando  solo windows")
    void test_only_windows(){
        System.out.println("Probando  solo windows");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void test_only_JDK11() {
        System.out.println("Probando solo en java 11");
    }

    @Test
    void test_print_properties_system() {
        Properties properties = System.getProperties();
        properties.forEach((k,v) -> System.out.println(k+" : "+v));
    }

    @Test
    @EnabledIfSystemProperty(named = "java.version", matches = ".*21.*")
    void test_java_version() {
        System.out.println("Probando tipo de version java");
    }

    @Test
    void test_print_envaroments_system() {
        Map<String,String> getEnv = System.getenv();
        getEnv.forEach((k,v) -> System.out.println(k+" : "+v));
    }

    @ParameterizedTest(name = "numero  {index} ejecutando con valor {argumentsWithNames}")
    @DisplayName("Probando  el retido de la cuenta en lista")
    @ValueSource(strings = {"100","200","300","500","700","1000.123"})
    void test_subtract_account_in_list(String value) throws BussinesRuleException {
        //Act
        Account data= new Account("Sebastian",new BigDecimal("1000.123"));

        data.subtractFromAccount(new BigDecimal(value));

        //Assert
        assertNotNull(data.getBalance());
        //assertEquals(900, data.getBalance().intValue());
        assertTrue(data.getBalance().compareTo(BigDecimal.ZERO)>=0);

    }
}

