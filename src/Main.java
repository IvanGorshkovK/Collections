import java.util.Scanner;

public class Main {

    public static void showMenu(){
        System.out.println("«1»: Добавить контакт\n" +
                "«2»: Удалить контакт\n" +
                "«3»: Посмотреть все контакты\n" +
                "«4»: Найти контакт\n" +
                "«5»: Посмотреть контакты По группе\n" +
                "«0»: Выход\n");
    }



    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        while(flag){
            showMenu();
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    contactManager.inputInfoContact();
                    break;
                case 2:
                    contactManager.deleteContactByPhone();
                    break;
                case 3:
                    contactManager.showContact();
                    break;
                case 4:
                    contactManager.searchContactByName();
                    break;
                case 5:
                    contactManager.showContactsByGroup();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Выберите действие от 0 до 5!");
            }
        }
    }
}
