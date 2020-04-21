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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    @Override
    public String toString() {
        String strTrades = trades.stream().map(t -> "  " + t).collect(Collectors.joining("\n", "[\n", "\n]"));
        return String.format("Order[customer=%s, trades=%s]", customer, strTrades);
    }

    //Lombok  @Builder
    public Order(String customer, List<Trade> trades) {
        this.customer = customer;
        this.trades = trades;
    }

    private Order(Builder builder) {
        this.customer = builder.customer;
        this.trades = builder.trades;
    }

    public static class Builder {

        private String customer;
        private List<Trade> trades = new ArrayList<>();

        public Builder customer(String customer) {
            this.customer = customer;
            return this;
        }

        public Builder trades(List<Trade> trades) {
            this.trades = trades;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
