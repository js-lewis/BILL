package edu.sc.csce740;

/**
 *
 */
public class TestDriver {
    public static void main (String[] args) {
        BILL myBill = new BILL ();
        try {
            myBill.loadUsers( "resources/users.json");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
