package com.ki.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShareOrderTest {
    private static final int MOCK_CUSTOMER_ID = 10;
    private static final int MOCK_SHARES = 100;

    private ShareOrder sut;

    @Before
    public void setUp() {
        sut = new ShareOrder(MOCK_CUSTOMER_ID, MOCK_SHARES);
    }

    @Test
    public void getCustomerId() {
        // When I call getCustomerId
        int actual = sut.getCustomerId();
        // Then customerId will be returned
        assertEquals(MOCK_CUSTOMER_ID, actual);
    }


    @Test
    public void testSetCustomerId() {
        // Given a status
        int expectedCustomerId = 1000;
        // When I call getCustomerId
        sut.setCustomerId(expectedCustomerId);
        // Then customerId will have been updated
        assertEquals(expectedCustomerId, sut.getCustomerId());
    }

    @Test
    public void getShares() {
        // When I call getShares
        int actual = sut.getShares();
        // Then shares will be returned
        assertEquals(MOCK_SHARES, actual);
    }


    @Test
    public void testSetShares() {
        // Given a number of shares
        int expectedShares = 1000;
        // When I call setShares
        sut.setShares(expectedShares);
        // Then shares will have been updated
        assertEquals(expectedShares, sut.getShares());
    }
}