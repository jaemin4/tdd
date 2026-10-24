package pro.tdd.proejct.model.entity;

import pro.tdd.proejct.constants.TransactionType;

public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {
}
