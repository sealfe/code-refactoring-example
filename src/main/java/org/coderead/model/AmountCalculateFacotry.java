package org.coderead.model;

/**
 * @author by fengww
 * @Classname AmountCalculateFacotry
 * @Description
 * @Date 2022/7/7 22:53
 */
public class AmountCalculateFacotry {


    public static AmountCalculate get(String type, int audience) {
        switch (type) {
            case "comedy":
                return new ComedyAmountCalculate(audience);
            case "tragedy":
                return new TragedyAmountCalculate(audience);
            default:
                throw new RuntimeException("unknown type:" + type);

        }
    }

}
