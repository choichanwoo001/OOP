import java.util.*;

class User {
    private String name;
    private String accountNum;
    private int balance;

    public User(String name, String accountNum, int balance) {
        this.name = name;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public String getAccountNum() {return accountNum;}

    public void setBalance(int balance) {
        if(balance < 0){
            System.out.println("계좌의 총 금액보다 많은 금액을 인출할 수 없습니다.");
        }
        else
            this.balance = balance;
    }

    public int getBalance() {return balance;}

    static void deposit(User users, int inputMoney){
        users.setBalance(users.getBalance()+inputMoney);
    }
    static void withdraw(User users, int inputMoney){
        users.setBalance(users.getBalance()-inputMoney);
    }
    static void transaction(User users1, User users2, int inputMoney){
        users1.setBalance(users1.getBalance()-inputMoney);
        users2.setBalance(users2.getBalance()+inputMoney);
    }
    static void checkAccount(User users){
        System.out.println(users.getBalance());
    }
}

class BankSystem{
    static User findAccount(ArrayList<User> users){ // 계좌번호 찾기
        Scanner sc = new Scanner(System.in);

        System.out.print("계좌번호를 입력해주세요 : ");
        String accountNum = sc.nextLine();

        User user1 = null;
        for(int i = 0; i< users.size(); i++){
            if(Objects.equals(users.get(i).getAccountNum(), accountNum))
                user1 = users.get(i);
        }
        if(user1 == null)
            System.out.println("입력하신 계죄번호와 일치하는 계좌가 없습니다.");
        return user1;
    }
}

public class Bank_System {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("최찬우", "12345", 10000));
        users.add(new User("김찬우", "23451", 10000));
        users.add(new User("이찬우", "34512", 10000));
        users.add(new User("오찬우", "45123", 10000));
        users.add(new User("박찬우", "51234", 10000));

        Scanner sc = new Scanner(System.in);
        System.out.println("안녕하세요 원하시는 작업을 선택해주세요");
        System.out.println("1. 입금");
        System.out.println("2. 출금");
        System.out.println("3. 이체");
        System.out.println("4. 잔액 조회");
        System.out.println("0. 종료");

        int option = 9;
        while(true)
        {
            System.out.print("입력 : "); option = sc.nextInt();
            sc.nextLine(); // 버퍼 지우기
            User user1 = null; User users2 = null;

            if(option == 1){ // 입금
                user1 = BankSystem.findAccount(users);

                if(user1 == null){ continue; } // 해당 계좌가 없을때 
                else {
                    System.out.print("입금하실 금액을 입력해주세요 : ");
                    int money = sc.nextInt(); User.deposit(user1, money);
                }
            }
            else if(option == 2){ // 출급
                user1 = BankSystem.findAccount(users);

                if(user1 == null){ continue; } // 해당 계좌가 없을때 
                else {
                    System.out.print("출금하실 금액을 입력해주세요 : ");
                    int money = sc.nextInt(); User.withdraw(user1, money);
                }
            }
            else if(option == 3){ // 이체
                System.out.print("본인의 "); user1 = BankSystem.findAccount(users);
                System.out.print("이체하실 "); users2 = BankSystem.findAccount(users);

                if(user1 == null || users2 == null) {continue;} // 해당 계좌가 없을때 
                else {
                    System.out.print("이체하실 금액을 입력해주세요 : ");
                    int money = sc.nextInt(); User.transaction(user1, users2, money);
                }
            }
            else if(option == 4){ // 잔액 조회
                user1 = BankSystem.findAccount(users);

                if(user1 == null) {continue;} // 해당 계좌가 없을때 
                else User.checkAccount(user1);
            }
            else if(option == 0)
                break;
            else{
                System.out.println("잘못 입력하셨습니다. 종료를 원하신다면 0을 눌러주세요");
            }
        }
    }
}