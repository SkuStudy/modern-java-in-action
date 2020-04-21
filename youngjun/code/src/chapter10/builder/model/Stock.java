/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chapter10.builder.model;

public class Stock {

    private String symbol;
    private String market;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return String.format("Stock[symbol=%s, market=%s]", symbol, market);
    }

    //Lombok  @Builder
    public Stock(String symbol, String market) {
        this.symbol = symbol;
        this.market = market;
    }

    public Stock(Builder builder) {
        this.symbol = builder.symbol;
        this.market = builder.market;
    }

    public static class Builder {

        private String symbol;
        private String market;

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder market(String marget) {
            this.market = market;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }
    }
}
