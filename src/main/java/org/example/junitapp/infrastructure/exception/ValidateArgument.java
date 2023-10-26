    package org.example.junitapp.infrastructure.exception;

    import java.math.BigDecimal;
    public class ValidateArgument {

        public ValidateArgument() {
        }

        public static void validateValueSubtractCorrect(BigDecimal newValue, BigDecimal actualValue, String menssage) throws BussinesRuleException {
            if (actualValue.compareTo(newValue) < 0) {
                throw new BussinesRuleException(menssage);
            }
        }

    }
