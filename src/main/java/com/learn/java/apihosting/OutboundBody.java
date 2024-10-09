package com.learn.java.apihosting;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutboundBody {
    private String name;
    private String capital;
    private int population;
}
