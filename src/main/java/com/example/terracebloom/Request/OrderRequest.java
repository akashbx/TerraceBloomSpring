package com.example.terracebloom.Request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private Integer userId;
    private List<Integer> productIds;
    private List<Integer> gardenerIds;
}

