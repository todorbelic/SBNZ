package com.bank.sbnz.events;

import com.bank.sbnz.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class TransactionEvent implements Serializable {

    private Date executionTime;

    private Transaction transaction;

    public TransactionEvent(Transaction transaction) {
        this.transaction = transaction;
    }
}
