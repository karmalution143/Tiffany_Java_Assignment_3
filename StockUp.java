import java.util.ArrayList;
import java.util.Scanner;

import utility.*;
import objects.*;

public class StockUp {
    private static Scanner input = new Scanner(System.in);
    private static Ink ink = new Ink();
    private static Validator validator = new Validator();
    private static Market market = new Market();
    // private static Portfolio portfolio = new Portfolio(); why did this work?
    private static Portfolio portfolio;

    private static int min = 1;
    private static int max = 6;
    private static boolean isDone = false;
    private static boolean goBack = false;


    public static void main(String[] args) {

        ink.printWelcome();

        System.out.println("How much money is your initial deposit? \n");
        double initialDeposit = validator.depositValidation(ink, input);
        portfolio = new Portfolio(initialDeposit);
        ink.printInitialDeposit(initialDeposit);
        
        seedStocks();
        portfolio.updateStockBalance();
        seedMarket();

        ink.printBalances(portfolio.getNetworth(), 
            portfolio.getCashBalance(), 
            portfolio.getStockBalance());

        while(!isDone) { // can also say while(isDone == false)
            int choice = validator.selValidation(ink, input, min, max); // pass in the opjects ink, input etc

            switch(choice) {
                case 1: // print Portfolio
                    ink.printPortfolio(portfolio.getStocks());
                    ink.printBalances(portfolio.getNetworth(), 
                        portfolio.getCashBalance(), 
                        portfolio.getStockBalance());
                    break;
                case 2: // buy stock
                    while(!goBack) {
                        ink.printMarket(market.getStocks());
                        int idx = input.nextInt();
                        Stock stock = market.getStock(idx - 1);
                        ink.printBuyStock(stock);
                        int qty = input.nextInt();
                        System.out.println("Purchasing " + qty + " unit(s) of " + stock.getName());
                        boolean issuccess = market.buyStocks(stock, qty, portfolio.getCashBalance());
                        if(issuccess) {
                            double purchaseAmount = stock.getPrice() * qty;
                            portfolio.buyStock(stock, qty, purchaseAmount);
                            portfolio.updateStockBalance();
                            portfolio.updateNetworth();
                            //portfolio.addStock(stock);
                            ink.printPortfolio(portfolio.getStocks());
                            ink.printBalances(portfolio.getNetworth(), 
                            portfolio.getCashBalance(), 
                            portfolio.getStockBalance());
                            goBack = !goBack;
                        }
                        else {
                            System.out.println("Failed to buy stock./n");
                        }
                    } // while
                    break;
                case 3: // sell stock
                    while(!goBack) {
                        System.out.println("Choose a stock to sell: \n");
                        ink.printPortfolio(portfolio.getStocks());

                        int idx = input.nextInt();
                        Stock stock = portfolio.getStock(idx - 1);
                        ink.printSellStock(stock);
                        int qty = input.nextInt();
                        if (qty <= stock.getQty()) {
                        portfolio.sellStock(stock, qty);
                        portfolio.getStockBalance();
                        
                        System.out.println("The stock has been sold, you're cash balance has been updated");
                        ink.printBalances(portfolio.getNetworth(), 
                            portfolio.getCashBalance(), 
                            portfolio.getStockBalance());
                        ink.printPortfolio(portfolio.getStocks());
                        goBack = !goBack;
                        }
                        else {
                            System.out.println("Insufficient quantity to sell");
                        }
                    }   
                    goBack = !goBack;
                    break;
                    
                case 4: // add funds
                    double amount = validator.fundValidation(ink, input, portfolio.getCashBalance());
                    portfolio.addFunds(amount);
                    // print the new balance
                    System.out.printf("New Balance: $%.2f\n", portfolio.getCashBalance());
                    break;

                case 5: // withdrawal funds
                    amount = validator.withdrawalValidation(ink, input, portfolio.getCashBalance());
                    ink.printWithdrawalFunds(portfolio.getCashBalance());
                    portfolio.withdrawFunds(amount);
                    // print the new balance
                    System.out.printf("New Balance: $%.2f\n", portfolio.getCashBalance());
                    break;
                
                case 6:
                    isDone = !isDone;
                    break;
                default:
                    System.out.println("Oops");
                    break;
            } // switch  
        } // while
         // resets goBack to false, toggling back to main state
        ink.printGoodbye();
    } // main

    public static void seedStocks() {
        // the purpose is to create some test stocks
        Stock stock = new Stock("Microsoft", "MSFT", 420, 100);
        portfolio.addStock(stock);
        stock = new Stock("Uber", "UBR", 120, 50);
        portfolio.addStock(stock);
        stock = new Stock("Nvidia", "NVD", 250, 90);
        portfolio.addStock(stock);
    } // seedStocks

    public static void seedMarket() {
        ArrayList<Stock> stocks = new ArrayList<>();
        // the purpose is to create some TEST stocks for the Market
        Stock stock = new Stock("Adobe", "ADB", 20.00, 0);
        stocks.add(stock);
        stock = new Stock("Netflix", "NFX", 120.00, 0);
        stocks.add(stock);
        stock = new Stock("Apple", "APL", 250.00, 0);
        stocks.add(stock);
        stock = new Stock("Disney", "MOUSE", 1250.00, 0);
        stocks.add(stock);
        stock = new Stock("Microsoft", "MSFT", 420.00, 0);
        stocks.add(stock);
        stock = new Stock("Uber", "UBR", 120.00, 0);
        stocks.add(stock);
        stock = new Stock("Nvidia", "NVD", 900.00, 0);
        stocks.add(stock);
        market.setStocks(stocks);
    }
} // class
