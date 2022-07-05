package ru.geekstar;

public class Card {

    private float deposit;

    private String numberCard;

    private String paySystem;

    private int countTransactions = 0; // выведем все транзакции по карте

    private String[] transactions = new String[5];

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
        do {
            payStatus = withdrawal(sumPay);
            if (payStatus) { // payStatus == true
                String transaction = paySystem + " " + numberCard + ": " + " Оплачено " + sumPay + " Остаток на карте " + deposit;
                setTransactions(transaction);
                //System.out.println(transaction);
            }
        } while (!payStatus); // выполнять цикл, пока не прошла оплата
        /*
        todo: перевести сумму на счет магазина
         */
    }

    public void transfer(float sumTransfer) {
        // рассчитать коммисию за перевод
        float commission;
        if (sumTransfer < 50000) {
            commission = 0.0f;
        } else { // sumTransfer == 5000 || sumTransfer > 50000
            commission = sumTransfer * 0.01f;
        }
        // списать деньи с карты
        boolean transferStatus;
        do {
            transferStatus = withdrawal(sumTransfer);
            if (transferStatus) { // transferStatus == true
                String transaction = paySystem + " " + numberCard + ": " + " Переведено " + sumTransfer + " Комиссия составила " + commission + " Остаток на карте " + deposit;
                setTransactions(transaction);
                //System.out.println(transaction);
            }
        }while (!transferStatus);

        // перевести деньги на другую карту
    }

        //Так как списание и перевод денег одинаковы, выносим общий код в отдельный метод
    private boolean withdrawal(float sum) {
        deposit = deposit - sum;
        if (deposit >= 0) return true;
        else return false;
    }

}
