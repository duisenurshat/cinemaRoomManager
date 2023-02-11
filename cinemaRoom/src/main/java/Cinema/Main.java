package Cinema;
import java.util.Scanner;
public class Main{
    final static Scanner scanner = new Scanner(System.in);
    /***
     * Объявляем переменные row - количество рядов, seat - количество мест в каждям ряду.
     * sum = 0, его используем в методе statistic, используем его чтобы считать сумму дохода
     * в настоящее время по тому сколько мест было продано.
     * ticket = 0, показывает сколь мест уже куплено.
     * price = 1, его используем в методе totalIncome, показывает в целом сколько стоят все места в сумме.
     * percent = 1, показвает сколько процентов составила проданные места.
     ***/
    static String[][] Cinema;
    static int row;
    static int seat;
    static int sum = 0, ticket = 0;
    static int seatNum, rowNum;
    static int price = 1;
    static double percent = 1;
    /***
     * Основной метод main принимает во вход row и seat, после вызывает метод setCinema(row, seat).
     * После выдает пользовательский меню для выбора дальнейших действии.
     * 1. Места в кинотеатре
     * 2. Покупка билета
     * 3. Статистика
     * 4. Выход
     * После вызывает метод switchMode(cases).
     ***/
    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seat = scanner.nextInt();
        Cinema = new String[row+1][seat+1];
        setCinema(row, seat);
        System.out.println(" ");
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int cases = scanner.nextInt();
            System.out.println(" ");
            if (cases == 0) {
                break;
            }
            switchMode(cases);
        }

    }
    /***
     * Метод переключатель по значении cases в методе main переключает на следующий метод.
     * 1 - переходит к методу вывода всех мест в массиве(displayCinema)
     * 2 - переходит к методу для выбора мест(choosePlace)
     * 3 - переходит к методу вывода статистики(statistics)
     *
     ***/
    public static void switchMode(int cases) {
        switch(cases){
            case 1: displayCinema(); break;
            case 2: choosePlace(); break;
            case 3: statistics(); break;
            default: {};
        }
    }
    /***
     * Метод заполняет массив размером количество рядов умноженное на количество мест и
     * заполняет ячейки массива значением S.
     ***/
    public static void setCinema(int row, int seat) {
        for(int i = 0; i <= row; ++i) {
            if(i > 0) {
                Cinema[i][0] = Integer.toString(i);
            }
            for(int j = 0; j <= seat; ++j) {
                Cinema[0][0] = " ";
                if (j > 0)
                    Cinema[0][j] = Integer.toString(j);
                if (i > 0 && j > 0) {
                    Cinema[i][j] = "S";
                }
            }
        }
    }
    /***
     * Показывает все места в кинотеатре (свободные S и не свободные B)
     ***/
    public static void displayCinema() {
        System.out.println("Cinema: ");
        for(int i = 0; i <= row; ++i) {
            for(int j = 0; j <= seat; ++j) {
                System.out.print(Cinema[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    /***

     ***/
    public static void choosePlace() {
        System.out.println("Enter a row number: ");
        rowNum = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        seatNum = scanner.nextInt();
        System.out.println(" ");
        if(rowNum > Cinema.length - 1 || seatNum > Cinema.length - 1 || rowNum < 1 || seatNum < 1)
            wrongInput();
        if(Cinema[rowNum][seatNum] == "B")
            purchasedTicket();
        else {
            buyTickets();
            ticket++;
        }

    }
    public static void wrongInput() {
        System.out.println("Wrong input!");
        System.out.println(" ");
        choosePlace();
    }
    public static void purchasedTicket() {
        System.out.println("That ticket has already been purchased!");
        System.out.println(" ");
        choosePlace();
    }
    public static void buyTickets() {
        System.out.print("Ticket price: $");
        int allSeats = (Cinema.length - 1) * (Cinema[0].length - 1);
        if(allSeats < 60) {
            System.out.println(SmallCinema());
            sum = sum + SmallCinema();
        } else {
            System.out.println(LargeCinema(rowNum, seatNum));
            sum = sum + LargeCinema(rowNum, seatNum);
        }
        Cinema[rowNum][seatNum] = "B";
    }

    public static int SmallCinema() {
        return 10;
    }
    public static int LargeCinema(int rowNum, int seatNum) {
        return rowNum > row/2 ? 8 : 10 ;
    }
    public static void statistics() {
        int allSeats = (Cinema.length - 1) * (Cinema[0].length - 1);
        percent = /*percent + */((double)ticket * 100) / allSeats;
        System.out.println("Number of purchased tickets: " + ticket);
        System.out.println("Percentage: " + String.format("%.2f", percent) + "%");
        System.out.println("Current income: $" + sum);
        System.out.println("Total income: $" + totalIncome());
    }
    public static int totalIncome() {
        int part1 = row/2;
        int part2 = row - part1;
        if(row * seat < 60)
            price = row * seat * 10;
        if(row * seat >= 60) {
            price = (part1 * seat * 10) + (part2 * seat * 8);
        }
        return price;
    }
}