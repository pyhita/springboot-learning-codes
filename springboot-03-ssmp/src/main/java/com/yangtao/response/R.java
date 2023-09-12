package com.yangtao.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {

    private Boolean flag;
    private Object data;
    private String msg;

    public R(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(Object data, String msg) {
        this.data = data;
    }

}
