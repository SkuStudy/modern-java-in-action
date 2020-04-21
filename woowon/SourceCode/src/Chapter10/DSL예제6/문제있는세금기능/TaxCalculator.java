package Chapter10.DSL예제6.문제있는세금기능;

import Chapter10.DSL예제6.Tax;
import Chapter10.Order;

// 없어도 되는 유창한 세금 계산기
public class TaxCalculator {
    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator withTaxRegional(){
        useRegional = true;
        return this;
    }

    public TaxCalculator withTaxGeneral(){
        useGeneral = true;
        return this;
    }

    public TaxCalculator withTaxSurcharge(){
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order){
        return Tax.calculate(order, useRegional, useGeneral, useSurcharge);
    }

}
