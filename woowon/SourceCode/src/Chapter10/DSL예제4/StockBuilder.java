package Chapter10.DSL예제4;

import Chapter10.Stock;

public class StockBuilder {
    public Stock stock = new Stock();

    public void symbol(String symbol){
        stock.setSymbol(symbol);
    }

    public void market(String market){
        stock.setMarket(market);
    }
}
