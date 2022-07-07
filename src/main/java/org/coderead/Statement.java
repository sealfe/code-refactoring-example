package org.coderead;

import org.coderead.model.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static java.lang.String.format;

/**
 * 客户服务类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    private Invoice invoice;

    private Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {
        int totalAmount = getTotalAmount();
        int volumeCredits = getVolumeCredits();

        return getShowString(totalAmount, volumeCredits);
    }

    private String getShowString(int totalAmount, int volumeCredits) {
        return format("Statement for %s", invoice.getCustomer()) +
                getString(getFormat()) +
                format("Amount owed is %s\n", getFormat().format(totalAmount / 100)) +
                format("You earned %s credits\n", volumeCredits);
    }

    private NumberFormat getFormat() {
        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format;
    }

    private String getString(NumberFormat format) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            AmountCalculate amountCalculate = getCalculate(performance.getAudience(), play.getType());
            int thisAmount1;
            switch (amountCalculate.getType()) {
                case "tragedy":
                    thisAmount1 = 40000;
                    if (amountCalculate.getAudience() > 30) {
                        thisAmount1 += 1000 * (amountCalculate.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount1 = 30000;
                    if (amountCalculate.getAudience() > 20) {
                        thisAmount1 += 10000 + 500 *(amountCalculate.getAudience() - 20);
                    }
                    thisAmount1 += 300 * amountCalculate.getAudience();
                    break;
                default:
                    throw new RuntimeException("unknown type:" + amountCalculate.getType());
            }
            int thisAmount = thisAmount1;
            stringBuilder.append(format(" %s: %s (%d seats)\n", play.getName(), format.format(thisAmount / 100), performance.getAudience()));
        }
        return stringBuilder.toString();
    }

    private int getVolumeCredits() {
        int volumeCredits = 0;
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            AmountCalculate amountCalculate = getCalculate(performance.getAudience(), play.getType());
            int volumeCredits1 = 0;
            volumeCredits1 += Math.max(amountCalculate.getAudience() - 30, 0);

            if ("comedy".equals(amountCalculate.getType())) {
                volumeCredits1 += Math.floor(amountCalculate.getAudience() / 5);
            }
            volumeCredits += volumeCredits1;
        }
        return volumeCredits;
    }

    private AmountCalculate getCalculate(int audience, String type) {
        return  AmountCalculateFacotry.get( type,audience);
    }

    private int getTotalAmount() {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            AmountCalculate amountCalculate = getCalculate(performance.getAudience(), play.getType());
            int thisAmount;
            switch (amountCalculate.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (amountCalculate.getAudience() > 30) {
                        thisAmount += 1000 * (amountCalculate.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (amountCalculate.getAudience() > 20) {
                        thisAmount += 10000 + 500 *(amountCalculate.getAudience() - 20);
                    }
                    thisAmount += 300 * amountCalculate.getAudience();
                    break;
                default:
                    throw new RuntimeException("unknown type:" + amountCalculate.getType());
            }
            totalAmount += thisAmount;
        }
        return totalAmount;
    }

}
