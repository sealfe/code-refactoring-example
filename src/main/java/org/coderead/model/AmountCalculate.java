package org.coderead.model;

public abstract class AmountCalculate {

    private final int audience;

    private final String type;

    public AmountCalculate(int audience, String type) {
        this.audience = audience;
        this.type = type;
    }

    public int getAudience() {
        return audience;
    }

    public String getType() {
        return type;
    }

    public abstract int getAmount() ;

    public abstract int getVolumeCredits();
}
