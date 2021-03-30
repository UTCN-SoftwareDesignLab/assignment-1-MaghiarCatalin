package model.builder;

import model.ClientAccount;
import view.DTO.ClientAccountDTO;

public class ClientAccountBuilder {
    private ClientAccount clientAccount;

    public ClientAccountBuilder() {
        clientAccount = new ClientAccount();
    }

    public ClientAccount buildFromDTO(ClientAccountDTO clientAccountDTO) {
        ClientAccount clientAccount = new ClientAccountBuilder()
                .setCardNumber(clientAccountDTO.getCard_number())
                .setAccountType(clientAccountDTO.getAccount_type())
                .setAmount(clientAccountDTO.getAmount())
                .setDateCreated(clientAccountDTO.getDate_created())
                .build();

        return clientAccount;

    }

    public ClientAccountBuilder setId(Long id) {
        clientAccount.setId(id);
        return this;
    }

    public ClientAccountBuilder setClientId(Long id) {
        clientAccount.setClient_id(id);
        return this;
    }

    public ClientAccountBuilder setCardNumber(String card_number) {
        clientAccount.setCard_number(card_number);
        return this;
    }

    public ClientAccountBuilder setAccountType(String type) {
        clientAccount.setAccount_type(type);
        return this;
    }

    public ClientAccountBuilder setAmount(int amount) {
        clientAccount.setAmount(amount);
        return this;
    }

    public ClientAccountBuilder setDateCreated(String date) {
        clientAccount.setDate_created(date);
        return this;
    }

    public ClientAccount build() {
        return clientAccount;
    }
}
