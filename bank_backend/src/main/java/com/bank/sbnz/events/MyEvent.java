package com.bank.sbnz.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyEvent {
    private String id;
    private long timestamp;

    // Constructors, getters, and setters

    // Additional properties, methods, and logic
}