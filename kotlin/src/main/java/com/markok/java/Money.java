package com.markok.java;

import java.util.Objects;

public class Money {
  long value;

  String currency;

  public Money(long value, String currency) {
    this.value = value;
    this.currency = currency;
  }

  public String getCustom() {
    return "Your wallet has " + value + " " + currency;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "com.markok.java.Money{" +
      "value=" + value +
      ", currency='" + currency + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Money money = (Money) o;
    return value == money.value &&
      Objects.equals(currency, money.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, currency);
  }
}
