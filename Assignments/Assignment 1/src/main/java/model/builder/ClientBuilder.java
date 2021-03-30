package model.builder;

import model.Client;


public class ClientBuilder {
    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setId(Long id) {
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
