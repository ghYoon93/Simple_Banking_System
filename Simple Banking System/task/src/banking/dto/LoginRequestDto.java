package banking.dto;

public class LoginRequestDto {
    private final String cardNumber;
    private final String pin;

    public LoginRequestDto(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }
}
