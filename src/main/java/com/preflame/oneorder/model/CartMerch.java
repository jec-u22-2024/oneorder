package com.preflame.oneorder.model;

public class CartMerch {
    private int merchId;
    private int amount;
    public CartMerch() {}
    public CartMerch(int id, int amount) {
        this.merchId = id;
        this.amount = amount;
    }

    public int getMerchId() {
        return this.merchId;
    }
    public int getAmount() {
        return this.amount;
    }
}
