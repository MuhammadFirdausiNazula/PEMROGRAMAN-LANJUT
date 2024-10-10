import java.util.Scanner;
public class ConcertTicketSystem {
    private int[][] SeatPrices;
    private boolean [][] SeatAvailability;
    private int TotalRows;
    private int TotalCols;
    private int SeatsAvailable;
    public ConcertTicketSystem(int Rows, int Cols){
        this.TotalRows = Rows;
        this.TotalCols = Cols;
        this.SeatsAvailable = Rows * Cols;
        initializeSeatPricesAndAvailability();
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter The Number of rows in the concert hall: ");
        int Rows = scanner.nextInt();
        System.out.println("Enter the Number of columns in the concert hall: ");
        int Cols = scanner.nextInt();

        ConcertTicketSystem ticketSystem = new ConcertTicketSystem(Rows, Cols);
        ticketSystem.showsSeatPrice();
        char choice;
        do {
            System.out.println("\noptions: ");
            System.out.println("1. view Available Seats");
            System.out.println("2. Buy Ticket");
            System.out.println("3. view Available Seats count");
            System.out.println("4. Exit");
            System.out.print("Enter Your choice (1/2/3/4):  ");
            choice = scanner.next().charAt(0);

            switch (choice){
                case '1':
                    ticketSystem.showAvailabeleSeats();
                    break;
                case '2':
                    System.out.print("Enter row Number");
                    int row = scanner.nextInt();
                    System.out.print("Enter Column number");
                    int col = scanner.nextInt();
                    ticketSystem.buyTicket(row, col);
                    break;
                case '3':
                    System.out.println("Available Seats count: " + ticketSystem.SeatAvailability);
                    break;
                case '4':
                    System.out.println("Exiting..........");
                    break;
                default:
                    System.out.println("Invalid Choice. please try again.");
            }
        }while (choice != 4);
        scanner.close();
    }
    private void initializeSeatPricesAndAvailability(){
        SeatPrices = new int[TotalRows][TotalCols];
        SeatAvailability = new boolean[TotalRows][TotalCols];
        for (int i = 0; i < TotalRows; i++){
            for (int j = 0; j < TotalCols; j++){
                SeatPrices [i][j] = 50;
                SeatAvailability [i][j] = true;}

        }
    }
    private void showsSeatPrice(){
        System.out.println("Seat Prices");
        for (int i = 0; i < TotalRows; i++){
            for (int j = 0; j < TotalCols; j++){
                System.out.println("Row " + (i+1) + ", column" + (j+1) + ": $" + SeatPrices [i][j]);
            }
        }
    }
    public void showAvailabeleSeats(){
        System.out.println("Available Seats: ");
        for (int i = 0; i < TotalRows; i++){
            for (int j = 0; j < TotalCols; j++){
                if (SeatAvailability[i][j]){
                    System.out.println("[ " + (i+1) + "," + (j + 1) + " ] ");
                } else {
                    System.out.println("[ x ]");
                }
            }
            System.out.println();
        }
    }
    public void buyTicket(int row , int col){
        boolean validSeat = row >= 1 && row <= TotalRows && col >= 1 && col <= TotalCols && SeatAvailability [row - 1][col - 1];
        if (validSeat){
            int price = SeatPrices[row - 1][col - 1];
            System.out.println("You Have Puchased a ticket  at row " + row + "and column" + col + "for $ " + price);
            SeatAvailability[row - 1][col - 1] = false;
            SeatsAvailable--;
        }else {
            System.out.println("Invalid seat selection or seat is already taken. ");
        }
    }
}
