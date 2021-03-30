package model.validation;

import model.ClientAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AccountValidator {

    private static final String PERSONAL_NUMBER_VALIDATION_REGEX = "^[0-9]+$";

    private final ClientAccount account;
    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public AccountValidator(ClientAccount account) {
        this.account = account;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateCardNumber(account.getCard_number());
        validateType(account.getAccount_type());
        return errors.isEmpty();
    }

    private void validateCardNumber(String identityCardNumber) {
        if (identityCardNumber.length() != 12)
            errors.add("The card number. It should have 12 digits.");
        if(!Pattern.compile(PERSONAL_NUMBER_VALIDATION_REGEX).matcher(identityCardNumber).matches()){
            errors.add("Invalid card number, must contain only numbers");
        }
    }


    private void validateType(String type) {
        if (!type.equals("Visa") && !type.equals("Mastercard") && !type.equals("Visa Electron") && !type.equals("Pay Pal"))
            errors.add("The type inserted is not a legit one. Try one of these: Visa, Mastercard, Visa Electron, Pay Pal");
    }
}
