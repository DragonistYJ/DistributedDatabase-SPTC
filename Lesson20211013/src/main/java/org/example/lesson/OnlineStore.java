package org.example.lesson;

import lombok.Data;

@Data
public class OnlineStore {
    private Long phoneNum;

    public OnlineStore(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long salePhone() {
        if (phoneNum > 0) {
            this.phoneNum = this.phoneNum - 1;
            return this.phoneNum;
        } else {
            return -1;
        }
    }
}
