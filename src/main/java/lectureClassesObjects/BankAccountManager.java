package lectureClassesObjects;

/** Driver class for BankAccount class */
public class BankAccountManager {
    public static void main(String[] args) {
        BankAccount ba;
        ba = new BankAccount("Jason", 20.0, 1);
        ba.deposit(100);
        ba.withdraw(10);
        System.out.println(ba);

    }
}
