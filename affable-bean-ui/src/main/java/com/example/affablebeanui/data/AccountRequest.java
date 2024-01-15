package com.example.affablebeanui.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountRequest(
        @JsonProperty("card_number")String cardNumber,
        double amount
) {
}
