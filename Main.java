public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   Welcome to the Banking System");
        System.out.println("=================================");
        
        Bank bank = new Bank();
        
        if (bank.login()) {
            bank.performTransaction();
        }
        
        System.out.println("Program terminated.");
    }
}