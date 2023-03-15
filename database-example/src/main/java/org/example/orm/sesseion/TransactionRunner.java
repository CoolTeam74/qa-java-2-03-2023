package org.example.orm.sesseion;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);
}
