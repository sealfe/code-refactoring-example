package org.coderead.model;

/**
 * @author by fengww
 * @Classname TragedyAmountCalculate
 * @Description
 * @Date 2022/7/7 22:49
 */
public class TragedyAmountCalculate extends AmountCalculate{

    public TragedyAmountCalculate(int audience) {
        super(audience, "tragedy");
    }


    @Override
    public int getAmount() {
        int thisAmount = 40000;
        if (getAudience() <= 30) {
            return thisAmount;
        }
        thisAmount += 1000 * (getAudience() - 30);
        return thisAmount;
    }

    @Override
    public int getVolumeCredits() {
        return Math.max(getAudience() - 30, 0);
    }

    @Override
    public String getType() {
        return "tragedy";
    }
}
