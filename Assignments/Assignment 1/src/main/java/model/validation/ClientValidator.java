package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator {
    private static final String PERSONAL_NUMBER_VALIDATION_REGEX = "^[0-9]+$";

    private final Client client;
    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateIdentityCardNumber(client.getIdentity_card_number());
        validatePersonalNumericalCode(client.getPNC());
        return errors.isEmpty();
    }

    private void validateIdentityCardNumber(String identityCardNumber) {
        if (identityCardNumber.length() != 4)
            errors.add("The identity card should have 4 digits.");
        if(!Pattern.compile(PERSONAL_NUMBER_VALIDATION_REGEX).matcher(identityCardNumber).matches()){
            errors.add("Invalid identity card number, must contain only numbers");
        }
    }

    private void validatePersonalNumericalCode(String personalNumericalCode) {

        if (!Pattern.compile(PERSONAL_NUMBER_VALIDATION_REGEX).matcher(personalNumericalCode).matches()) {
            errors.add("Invalid PNC, must contain only numbers");
        }
    }
}
