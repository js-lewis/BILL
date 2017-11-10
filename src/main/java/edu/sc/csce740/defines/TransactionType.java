package edu.sc.csce740.defines;

/**
 * Possible Study Abroad statuses
 * <li>{@link #PAYMENT}</li>
 * <li>{@link #CHARGE}</li>
 */
public enum TransactionType {
    /**
     * A Payment (credit) made to a student's account.
     */
    PAYMENT,

    /**
     * A Charge (debit) made to a student's account.
     */
    CHARGE;

    /**
     * Convert a Transaction Type to a string.
     * @return A string representation of a Transaction Type.
     */
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
