package com.example.ordersservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ApproveOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
}
