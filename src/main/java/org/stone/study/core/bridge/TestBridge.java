package org.stone.study.core.bridge;

public class TestBridge {
}

class Merchant {
    public Number actionPrice(Customer customer) {
        return 0;
    }
}

class NaiveMerchant extends Merchant {
    @Override
    public Double actionPrice(Customer customer) {
        return 0.0D;
    }
}

class Customer {

}
