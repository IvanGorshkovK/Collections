import java.util.*;

public class ContactManager {
    List<Contact> contactList;
    Set<Contact> contactSet;
    Map<String, List<Contact>> contactMap;

    public  ContactManager(){
        contactList = new ArrayList<>();
        contactSet = new LinkedHashSet<>();
        contactMap = new HashMap<>();
    }

    // Добавление контакта
    public void addContact(Contact contact){
        if(contactSet.contains(contact)){
            System.out.println("Контакт с таким телефоном уже существует");
            return;
        }

        contactList.add(contact);
        contactSet.add(contact);


        contactMap.computeIfAbsent(contact.getGroup(), k-> new ArrayList<>()).add(contact);
        System.out.println("Контакт добавлен!");
    }
    //Ввод данных контакта
    public void inputInfoContact(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя");
        String name = scanner.nextLine().trim();

        System.out.println("Введите номер телефона");
        String phone = scanner.nextLine().trim();

        System.out.println("Введите email");
        String email = scanner.nextLine().trim();

        System.out.println("Введите группу");
        String group = scanner.nextLine().trim();

        Contact contact = new Contact(name, phone, email, group);
        addContact(contact);
    }

    //Удаление контакта
    public void deleteContactByPhone(){
        System.out.println("Введите номер телефона для удаления");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine().trim();
        boolean tmp =false;
        Iterator<Contact> contactIterator = contactList.iterator();

        while(contactIterator.hasNext()){
            Contact contact = contactIterator.next();
            if (contact.getPhone().equals(phone)){
                contactIterator.remove();
                contactSet.remove(contact);

                List<Contact> contactSGroup = contactMap.get(contact.getGroup());
                if(contactSGroup!=null){
                    contactSGroup.remove(contact);
                    //Если в группе не контактов, удаление группы
                    if (contactSGroup.isEmpty()){
                        contactMap.remove(contact.getGroup());
                    }
                }
                System.out.println("Контакт с телефоном "+phone+" успешно удален");
                tmp = true;
            }
            if(!tmp){
                System.out.println("Контакт с указанным телефоном не найден!");
            }
        }

    }

    //Поиск контакта по имени
    public void searchContactByName(){
        System.out.println("Введите имя контакта для поиска");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine().trim();

        List<Contact> results = new ArrayList<>();

        Iterator<Contact> contactIterator = contactList.iterator();
        while(contactIterator.hasNext()){
            Contact contact = contactIterator.next();
            if(contact.getName().toLowerCase().contains(name.toLowerCase())){
                results.add(contact);
            }
        }

        if(!results.isEmpty()){
            System.out.print("Список найденных контактов по вашему запросу: ");
            System.out.println(results);
        }else {
            System.out.println("По вашему запросу контакт не найден");
        }
    }

    public void showContactsByGroup() {
        Scanner scanner = new Scanner(System.in);
        if (contactMap.isEmpty()) {
            System.out.println("Нет доступных групп!");
            return;
        }

        System.out.println("--- Доступные группы ---");
        // Показываем все доступные группы
        Set<String> groups = contactMap.keySet();
        Iterator<String> groupIterator = groups.iterator();
        while (groupIterator.hasNext()) {
            String group = groupIterator.next();
            System.out.println("- " + group);
        }

        System.out.print("Введите название группы для просмотра: ");
        String group = scanner.nextLine().trim();

        List<Contact> groupContacts = contactMap.get(group);
        if (groupContacts == null || groupContacts.isEmpty()) {
            System.out.println("Группа '" + group + "' не найдена или пуста!");
            return;
        }

        System.out.println("Контакты в группе '" + group + "'");
        // Используем итератор для обхода списка контактов группы
        Iterator<Contact> contactIterator = groupContacts.iterator();
        while (contactIterator.hasNext()) {
            System.out.println(contactIterator.next());
        }
    }

    // Вывод всех контактов
    public void showContact(){
        for (Contact contact : contactList) {
            System.out.println(contact.toString());
        }
    }

}
