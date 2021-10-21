package com.ki.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    private static final int MOCK_CARD_ID = 10;
    private static final String MOCK_CARD_STATUS = "processed";

    private Card sut;

    @Before
    public void setUp() {
        sut = new Card(MOCK_CARD_ID, MOCK_CARD_STATUS);
    }

    @Test
    public void testIsSuccessfulWhenProcessed() {
        // Given a card with status processed
        Card sut = new Card(MOCK_CARD_ID, "processed");
        // When I call isSuccessful
        Boolean actual = sut.isSuccessful();
        // Then the result is true
        assertTrue(actual);

    }

    @Test
    public void testIsSuccessfulWhenDeclined() {
        // Given a card with status declined
        Card sut = new Card(MOCK_CARD_ID, "declined");
        // When I call isSuccessful
        Boolean actual = sut.isSuccessful();
        // Then the result is false
        assertFalse(actual);
    }

    @Test
    public void testIsSuccessfulWhenErrored() {
        // Given a card with status processed
        Card sut = new Card(MOCK_CARD_ID, "errored");
        // When I call isSuccessful
        Boolean actual = sut.isSuccessful();
        // Then the result is false
        assertFalse(actual);
    }

    @Test
    public void testGetCardId() {
        // When I call getCardId
        int actual = sut.getCardId();
        // Then cardId will be returned
        assertEquals(MOCK_CARD_ID, actual);
    }

    @Test
    public void testSetCardId() {
        // Given a cardId
        int expectedCardId = 5;
        // When I call setCardId
        sut.setCardId(expectedCardId);
        // Then cardId will have been updated
        assertEquals(expectedCardId, sut.getCardId());
    }

    @Test
    public void testGetStatus() {
        // When I call getStatus
        String actual = sut.getStatus();
        // Then status will be returned
        assertEquals(MOCK_CARD_STATUS, actual);
    }

    @Test
    public void testSetStatus() {
        // Given a status
        String expectedStatus = "declined";
        // When I call setStatus
        sut.setStatus(expectedStatus);
        // Then status will have been updated
        assertEquals(expectedStatus, sut.getStatus());
    }

    @Test
    public void equalsReturnsTrueWhenSameObject() {
        // When I compare the object to itself
        Boolean expected = sut.equals(sut);
        // Then I expect the result to be true
        assertTrue(expected);
    }

    @Test
    public void equalsReturnsFalseWhenNull() {
        // When I compare the object to null
        Boolean expected = sut.equals(null);
        // Then I expect the result to be true
        assertFalse(expected);
    }

    @Test
    public void equalsReturnsTrueOnPropertyEquality(){
        // Given an identical Card
        Card card = new Card(MOCK_CARD_ID, MOCK_CARD_STATUS);
        // When I compare the object to another with deep equality
        Boolean expected = sut.equals(card);
        // Then I expect the result to be true
        assertTrue(expected);
    }
}