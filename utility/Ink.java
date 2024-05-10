package utility;
import objects.*;
import java.util.ArrayList;

public class Ink {

    public Ink() {
        // do nothing
    }

    public void printWelcome() {
        System.out.println("Welcome to StockUp beta ***\n");
    }

    public void printGoodday() {
        System.out.println("Richer Every Day!");
    }

    public void printInitialDeposit(double initialDeposit) {
        System.out.printf("Initial Deposit: $%.2f\n", initialDeposit);
    }

    /*public void printBalances(double networth, double cashBalance, double stockBalance) {
        portfolio.updateStockBalance();
        double stockBalance = portfolio.getStockBalance;
        System.out.printf("\nNetworth: $%.2f\n", networth);
        System.out.printf("Bank balance: $%.2f\n", cashBalance);
        System.out.printf("Stock Value: $%.2f\n", stockBalance);
    }*/

    public void printBuyStock(Stock stock) {
        System.out.printf("Name: %s\nSymbol: %s\nPrice: $%.2f \n",
        stock.getName(), stock.getSymbol(), stock.getPrice());
        System.out.println("How many units of this stock would you like to buy? \n");
    }
    public void printSellStock(Stock stock) {
        System.out.printf("Name: %s\nSymbol: %s\nPrice: $%.2f",
        stock.getName(), stock.getSymbol(), stock.getPrice());
        System.out.println("How many units of this stock would you like to sell? \n");
    }

    public void printPortfolio(ArrayList<Stock> stocks, double networth, double cashBalance, double stockBalance) {
        for(int i = 0; i < stocks.size(); i++) {
            System.out.printf("(%d) Name: %s Symbol: %s Price: $%.2f Qty: %d\n",
            i + 1,
            stocks.get(i).getName(),
            stocks.get(i).getSymbol(),
            stocks.get(i).getPrice(),
            stocks.get(i).getQty());
        }
        System.out.printf("\nNetworth: $%.2f\n", networth);
        System.out.printf("Bank balance: $%.2f\n", cashBalance);
        System.out.printf("Stock Value: $%.2f\n", stockBalance);
    } // printPortfolio

    public void printMarket(ArrayList<Stock> stocks) {
        for(int i = 0; i < stocks.size(); i++) {
            System.out.printf("\n(%d) Name %s Symbol: %s Price: $%.2f Qty: %d",
            i + 1,
            stocks.get(i).getName(),
            stocks.get(i).getSymbol(),
            stocks.get(i).getPrice(),
            stocks.get(i).getQty());
        } // for
        System.out.println("\nWhich stock would you like to buy?: ");
        }

    public void printMenu() {
        System.out.println("\n(1) Portfolio");
        System.out.println("(2) Buy Stock");
        System.out.println("(3) Sell Stock");
        System.out.println("(4) Show Add Funds");
        System.out.println("(5) Show Exit");
    }

    public void printStockDetail(Stock stock) {
        System.out.printf("Name: %s Symbol: %s Price: %d Qty: %d",
            stock.getName(), stock.getSymbol(),
            stock.getPrice(), stock.getQty());
    }

    public void printAddFunds(double cashBalance) {
        System.out.printf("Current balance: $%.2f\nAmount to add?: ",
        cashBalance);
    }
} // class