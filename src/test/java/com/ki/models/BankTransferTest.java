package com.ki.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTransferTest {
    private static final int MOCK_ACCOUNT_ID = 10;

    private BankTransfer sut;

    @Before
    public void setUp() {
        sut = new BankTransfer(MOCK_ACCOUNT_ID);
    }

    @Test
    public void testIsSuccessfulReturnsTrue() {
        // When I call isSuccessful
        Boolean actual = sut.isSuccessful();
        // Then the result is true
        assertTrue(actual);
    }

    @Test
    public void testGetAccountId() {
        // When I call getAccountId
        int actual = sut.getAccountId();
        // Then accountId will be returned
        assertEquals(MOCK_ACCOUNT_ID, actual);
    }

    @Test
    public void testSetAccountId() {
        // Given an accountId
        int expectedAccountId = 5;
        // When I call setAccountId
        sut.setAccountId(expectedAccountId);
        // Then the accountId will have been updated
        assertEquals(expectedAccountId, sut.getAccountId());
    }
}