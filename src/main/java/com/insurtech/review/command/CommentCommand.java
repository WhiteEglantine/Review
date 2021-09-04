package com.insurtech.review.command;

public class CommentCommand {
    private String value;
    private Long productId;
    private Long userId;

    public CommentCommand(String value, Long productId, Long userId) {
        this.value = value;
        this.productId = productId;
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
