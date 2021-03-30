package view.DTO;

public class ClientDTO {
    private String name;
    private String identity_card_number;
    private String PNC;
    private String address;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity_card_number() {
        return identity_card_number;
    }

    public void setIdentity_card_number(String identity_card_number) {
        this.identity_card_number = identity_card_number;
    }

    public String getPNC() {
        return PNC;
    }

    public void setPNC(String PNC) {
        this.PNC = PNC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
