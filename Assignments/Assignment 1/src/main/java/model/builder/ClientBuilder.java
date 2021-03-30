package model.builder;

import model.Client;
import model.ClientAccount;
import view.DTO.ClientAccountDTO;
import view.DTO.ClientDTO;


public class ClientBuilder {
    private Client client;

    public ClientBuilder() {
        client = new Client();
    }


    public Client buildFromDTO(ClientDTO clientDTO) {
        Client client = new ClientBuilder()
                .setName(clientDTO.getName())
                .setIdentity_card_number(clientDTO.getIdentity_card_number())
                .setPNC(clientDTO.getPNC())
                .setAddress(clientDTO.getAddress())
                .setEmail(clientDTO.getEmail())
                .build();

        return client;

    }

    public ClientBuilder setId(int id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setIdentity_card_number(String identity_card_number) {
        client.setIdentity_card_number(identity_card_number);
        return this;
    }

    public ClientBuilder setPNC(String PNC) {
        client.setPNC(PNC);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setEmail(String email) {
        client.setEmail(email);
        return this;
    }

    public Client build() {
        return client;
    }


}
