package ru.geekstar;

public class Card {

    private float deposit;

    private String numberCard;

    private String paySystem;

    private char currency; // '₽'

    private int countTransactions = 0; // выведем все транзакции по карте

    private String[] transactions = new String[50];

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }

    public String[] getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions[countTransactions++] = transactions;
    }

    public int getCountTransactions() {
        return countTransactions;
    }

    public void setCountTransactions(int countTransactions) {
        this.countTransactions = countTransactions;
    }

    public String getPaySystem() {
        return paySystem;
    }

    public void setPaySystem(String paySystem) {
        this.paySystem = paySystem;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public void pay(float sumPay) {
        // списать сумму покупки с карты
        boolean payStatus; // объявление переменной payStatus
        byte errorTransaction = 0;
        do {
            payStatus = withdrawal(sumPay);
            if (payStatus) { // payStatus == true
                String transaction = paySystem + " " + numberCard + ": " + " Покупка " + sumPay + currency + " Остаток на карте " + deposit + currency;
                setTransactions(transaction);
                //System.out.println(transaction);
            } else errorTransaction++;
        } while (!payStatus && errorTransaction < 3); // выполнять цикл, пока не прошла оплата
        /*
        todo: перевести сумму на счет магазина
         */
    }

    public void transfer(float sumTransfer) {
        // рассчитать коммисию за перевод
        float comission;
        if (sumTransfer < 50000) {
            comission = 0.0f;
        } else { // sumTransfer == 5000 || sumTransfer > 50000
            comission = sumTransfer * 0.01f;
        }
        // списать деньи с карты
        boolean transferStatus;
        byte errorTransaction = 0;
        do {
            transferStatus = withdrawal(sumTransfer + comission);
            if (transferStatus) { // transferStatus == true
                String transaction = paySystem + " " + numberCard + ": " + " Переведено " + sumTransfer + currency + " Комиссия составила " + comission + currency + " Остаток на карте " + deposit + currency;
                setTransactions(transaction);
                //System.out.println(transaction);
            } else errorTransaction++;
        } while (!transferStatus && errorTransaction <3);

        // перевести деньги на другую карту

        // и перевести комиссию на счет банка
    }

    private void depositing(float sumDepositing) {
        // внесение денег на карту
        deposit = deposit + sumDepositing;
        String transaction = paySystem + " " + numberCard + ": " + " Внесено " + sumDepositing + currency + " Остаток на карте " + deposit + currency;
        setTransactions(transaction);
    }

        //Так как списание и перевод денег одинаковы, выносим общий код в отдельный метод
    private boolean withdrawal(float sum) {
        if (deposit >= sum) {
            deposit = deposit - sum;
        return true;
        } else {
            String transaction = paySystem + " " + numberCard + ": " + " Недостаточно средств на карте " + deposit + currency;
            setTransactions(transaction);
            return false;
        }
        
    }

}
