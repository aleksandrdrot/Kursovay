import java.util.Scanner;

public class EmployeeBook {
    ExceptionErrors er = new ExceptionErrors();
    Scanner input = new Scanner(System.in);
    int arrlength = 6;  // устанавливаем размерность списка

//----------- Для работы -----------------------------------------//
    //private String[] [] piopele = new String[arrlength] [3];
    //private int[] salaryEmployee = new int[arrlength];
    //private int[] departmentEmployee = new int[arrlength];

    //----------- Для прверки ----------------------------------------//
    private String[] [] piopele = {{"Александр","Пупков","Леонидович"},{"Александр","Пушкин","Сергеевич"},
            {"Максим","Пупков","Александрович"},{"Александр","Пушкин","Генадеевич"},{null,null,null},{null,null,null}};
    private int[] salaryEmployee = {10254,25874,4358,52458,0,0};
    private int[] departmentEmployee = {1,2,5,4,0,0};
//----------------------------------------------------------------//

    public void start(){
        System.out.print(" Добавить - 1\n Удалить - 2\n Найти - 3\n Найти по отделам - 4\n Найти с меньшей зарплатой - 5\n " +
                "Найти с большей зарплатой - 6\n Общая сумма зарплат - 7\n Среднее значение зарплаты - 8\n Изменить зарплату - 9\n Изменить отдел - 10\n" +
                " Список всех сотрудников - 11\n ");
        System.out.print("Выберите действие: ");
        int level = input.nextInt();
        switch (level){
            case 1: addPiopele(); break;
            case 2: deletePiople(); break;
            case 3: int index = searchPiople(); printOut(index); break;
            case 4: searchDepartment(); break;
            case 5: minSalary(); break;
            case 6: maxSalary(); break;
            case 7: allSalary(); break;
            case 8: averageSalary(); break;
            case 9: changeSalary(); break;
            case 10: changeDepartment(); break;
            case 11: printAllPiople(); break;
        }
        System.out.println();
        start();
    }

    public void addPiopele(){
        Scanner input = new Scanner(System.in);
        System.out.print("Введите Фамилию: ");
        String lastName = input.nextLine();
        System.out.print("Введите Имя: ");
        String firstName = input.nextLine();
        System.out.print("Введите Отчество: ");
        String middleName = input.nextLine();
        System.out.print("Сумма зарплаты: ");
        int salary = input.nextInt();
        System.out.print("Отдел №: ");
        int department = input.nextInt();

        if (firstName.isEmpty() || lastName.isEmpty() || middleName.isEmpty() || salary == 0 || department == 0){
            er.error1();
            start();
        }else {
            int index = 0;
            while (index < arrlength){
                if (piopele[index][0] == null){
                    piopele[index][0] = firstName;
                    piopele[index][1] = lastName;
                    piopele[index][2] = middleName;
                    salaryEmployee[index] = salary;
                    departmentEmployee[index] = department;
                    break;
                }else {
                    index++;
                    if (index == arrlength) {
                        er.error2();
                        start();
                    }
                }
            }
        }
        System.out.println("\n Сотрудник добавлен! \n");
    }

    public int searchPiople(){
        Scanner input = new Scanner(System.in);
        System.out.print("Введите Фамилию: ");
        String lastName = input.nextLine();
        System.out.print("Введите Имя: ");
        String firstName = input.nextLine();
        System.out.print("Введите Отчество: ");
        String middleName = input.nextLine();
        int index = 0;
        for (;index < arrlength;) {
            String str1 = piopele[index][0];
            String str2 = piopele[index][1];
            String str3 = piopele[index][2];
            if (str1 != null && str1.equals(firstName)){
                if (str2.equals(lastName)){
                    if (str3.equals(middleName)){
                        break;
                    }
                }
            }
            index++;
            if (index == arrlength){
                er.error3();
                start();
            }
        }
        return index;
    }

    public void deletePiople(){
        int index = searchPiople();
        piopele[index][0] = null;
        piopele[index][1] = null;
        piopele[index][2] = null;
        salaryEmployee[index] = 0;
        departmentEmployee[index] = 0;
        System.out.println("\nСотрудник удален!\n\n");
    }

    public void searchDepartment(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nВведите намер отдела: ");
        int indexDeportment = input.nextInt();
        boolean employee = true;
        for (int index = 0; index < arrlength; index++){
            if (indexDeportment == departmentEmployee[index]){
                employee = false;
                printOut(index);
            }
        }
        if (employee){er.error3();}
    }

    public void printAllPiople(){
        for (int i = 0; i < arrlength; i++){
            if (piopele[i][0] != null) {
                printOut(i);
            }
        }
    }

    public void allSalary(){
        int salary = 0;
        for (int i = 0; i < arrlength; i++){
            salary += salaryEmployee[i];
        }
        System.out.println("\nСумма необходимая для зарплаты - "+ salary + "\n");
    }

    public void minSalary() {
        int index = 0;
        int salary = salaryEmployee[0];
        for (int i = 0; i < arrlength;i++) {
            if (piopele[i][0] != null) {
                if (salary > salaryEmployee[i]) {
                    salary = salaryEmployee[i];
                    index = i;
                }
            }
        }
        System.out.println("\nНаименьшая зарплата у:\n");
        printOut(index);
    }

    public void maxSalary() {
        int index = 0;
        int salary = salaryEmployee[0];
        for (int i = 0; i < arrlength;i++) {
            if (piopele[i][0] != null) {
                if (salary < salaryEmployee[i]) {
                    salary = salaryEmployee[i];
                    index = i;
                }
            }
        }
        System.out.println("\nНаибольшая зарплата у:\n");
        printOut(index);
    }

    public void averageSalary(){
        float sum = 0;
        int salary = 0;
        for (int i = 0; i < arrlength; i++){
            if (piopele[i][0] != null) {
                sum += salaryEmployee[i];
                salary++;
            }
        }
        System.out.println("\nСредняя сумма зарплат - "+ sum/salary + "\n");
    }

    public void changeSalary(){
        int index = searchPiople();
        printOut(index);
        System.out.print("Введите сумму: ");
        int sum = input.nextInt();
        salaryEmployee[index] = sum;
        printOut(index);
        System.out.print("Зарплата изменена!\n ");
    }

    public void changeDepartment(){
        int index = searchPiople();
        printOut(index);
        System.out.print("Введите отдел: ");
        int dep = input.nextInt();
        departmentEmployee[index] = dep;
        printOut(index);
        System.out.print("Отдел изменен!\n ");
    }

    public void printOut (int index){
        System.out.print("Фамилия: " + piopele[index][1] +"\nИмя: " + piopele[index][0] +"\nОтчество " + piopele[index][2] +"\nЗароботная плата: " + salaryEmployee[index] +"\nОтдел № " + departmentEmployee[index]+ "\n\n");
    }
}
