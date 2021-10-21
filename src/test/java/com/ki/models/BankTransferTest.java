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

    @Test
    public void equalsReturnsTrueWhenSameObject() {
        // When I compare the object to itself
        Boolean expected = sut.equals(sut);
        // Then I expect the result to be true
        //noinspection ConstantConditions
        assertTrue(expected);
    }

    @Test
    public void equalsReturnsFalseWhenNull() {
        // When I compare the object to null
        Boolean expected = sut.equals(null);
        // Then I expect the result to be true
        //noinspection ConstantConditions
        assertFalse(expected);
    }

    @Test
    public void equalsReturnsTrueOnPropertyEquality(){
        // Given an identical Card
        BankTransfer bankTransfer = new BankTransfer(MOCK_ACCOUNT_ID);
        // When I compare the object to another with deep equality
        Boolean expected = sut.equals(bankTransfer);
        // Then I expect the result to be true
        assertTrue(expected);
    }
}