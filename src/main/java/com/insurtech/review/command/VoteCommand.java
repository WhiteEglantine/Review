package com.insurtech.review.command;

public class VoteCommand {
    private int value;
    private Long productId;
    private Long userId;

    public VoteCommand(int value, Long productId, Long userId) {
        this.value = value;
        this.productId = productId;
        this.userId = userId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
