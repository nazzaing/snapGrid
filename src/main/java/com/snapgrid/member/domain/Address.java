package com.snapgrid.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "road_addr")
    private String roadAddr;

    @Column(name = "addr_detail")
    private String addrDetail;
}