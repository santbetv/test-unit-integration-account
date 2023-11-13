    package org.example.junitapp.infrastructure.exception;

    import java.math.BigDecimal;
    public class ValidateArgument {

        public ValidateArgument() {
        }

        public static void validateValueSubtractCorrect(BigDecimal valueUpdated, BigDecimal valueSubtract, String menssage) throws BussinesRuleException {
            if (valueUpdated.compareTo(BigDecimal.ZERO) < 0) {
                throw new BussinesRuleException(menssage);
            }
        }

    }
