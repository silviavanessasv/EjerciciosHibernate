package com.hibernate.model;

public interface Item<T> {
    T getValue();

    String getName();

}
