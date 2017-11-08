package edu.sc.csce740.defines;

public enum TransactionType {
    PAYMENT,
    CHARGE;

    @Override
    public String toString() {
        switch (this) {
            case PAYMENT:
                return "PAYMENT";
            case CHARGE:
                return "CHANGE";
            default:
                return super.toString();
        }
    }
}
