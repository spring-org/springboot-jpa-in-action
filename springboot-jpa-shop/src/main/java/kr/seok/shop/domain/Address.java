package kr.seok.shop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    /* JPA 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 Protected로 설정한다. */
    protected Address() { }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
