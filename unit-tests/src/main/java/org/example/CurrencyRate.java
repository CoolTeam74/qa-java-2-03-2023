package org.example;

public class CurrencyRate {
    Currency from;
    Currency to;
    double rate;

    public CurrencyRate(Currency from, Currency to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    static CurrencyRate[] rates = {
            new CurrencyRate(Currency.RUB, Currency.USD, 0.02),
            new CurrencyRate(Currency.USD, Currency.RUB, 60.0)
    };

    static CurrencyRate getRate(Currency from, Currency to) {
        for (CurrencyRate rate: rates) {
            if (rate.from.equals(from) && rate.to.equals(to)) {
                return rate;
            }
        }
        return null;
    }
}
