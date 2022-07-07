package org.coderead.model;

/**
 * @author by fengww
 * @Classname ComedyAmountCalculate
 * @Description
 * @Date 2022/7/7 22:51
 */
public class ComedyAmountCalculate extends AmountCalculate{

    public ComedyAmountCalculate(int audience) {
        super(audience, "comedy");
    }


    @Override
    public String getType() {
        return "comedy";
    }

    @Override
    public int getAmount() {
        int thisAmount = 30000;
        if (getAudience() > 20) {
            thisAmount += 10000 + 500 *(getAudience() - 20);
        }
        thisAmount += 300 * getAudience();
        return thisAmount;
    }

    @Override
    public int getVolumeCredits() {
        int volumeCredits = 0;
        volumeCredits += Math.max(getAudience() - 30, 0);
        volumeCredits += Math.floor(getAudience() / 5);
        return volumeCredits;
    }
}
