package org.example;

public enum Currency implements Convertable {
    RUB(new String[]{"рубль", "рубля", "рублей"}){
        @Override
        void showMessage(String message) {
            System.out.println("RUB");
        }
    },
    USD(new  String[]{"доллар", "доллара", "доларов"}){
        @Override
        void showMessage(String message) {
            System.out.println("USD");
        }
    };

    private final String[] endings;

    Currency(String[] endings) {
        this.endings = endings;
    }

    abstract void showMessage(String message);

    public String getEndingMessage(int amount) {
        if (amount == 1) {
            return endings[0];
        } else if (amount <= 4 && amount >= 2) {
            return endings[1];
        } else {
            return endings[2];
        }
    }

    @Override
    public double convert(double amount, Currency currency) {
        CurrencyRate rate = CurrencyRate.getRate(this, currency);
        return amount * rate.rate;
    }
}
