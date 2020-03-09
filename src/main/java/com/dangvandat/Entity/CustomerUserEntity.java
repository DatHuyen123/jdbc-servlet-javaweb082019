package com.dangvandat.Entity;

import com.dangvandat.annotation.Column;
import com.dangvandat.annotation.Entity;
import com.dangvandat.annotation.Table;

@Entity
@Table(name = "customer_user")
public class CustomerUserEntity extends BaseEntity{

    @Column(name = "customerid")
    private Long customerId;

    @Column(name = "userid")
    private Long userId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
