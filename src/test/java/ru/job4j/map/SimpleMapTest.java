package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.nullValue;

public class SimpleMapTest {

    @Test
    public void whenPutElement() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Hello"));
        assertThat(simpleMap.get(1), is("Hello"));
        assertThat(simpleMap.get(2), is(nullValue()));
    }

    @Test
    public void whenPutTwoEqualElements() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Hello"));
        assertFalse(simpleMap.put(1, "World"));
    }

    @Test
    public void whenPutWithExpand() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "a"));
        assertTrue(simpleMap.put(2, "b"));
        assertTrue(simpleMap.put(3, "c"));
        assertTrue(simpleMap.put(4, "d"));
        assertTrue(simpleMap.put(5, "e"));
        assertTrue(simpleMap.put(6, "f"));
        assertTrue(simpleMap.put(7, "g"));
        assertThat(simpleMap.getCapacity(), is(16));
    }

    @Test
    public void whenGetElement() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Hello"));
        assertThat(simpleMap.get(1), is("Hello"));
        assertThat(simpleMap.get(2), is(nullValue()));
    }

    @Test
    public void whenGetNonExistElement() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Hello"));
        assertThat(simpleMap.get(1), is("Hello"));
        assertThat(simpleMap.get(2), is(nullValue()));
    }

    @Test
    public void whenRemoveExist() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Hello"));
        assertTrue(simpleMap.remove(1));
    }

    @Test
    public void whenRemoveNotExist() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Hello"));
        assertFalse(simpleMap.remove(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnAllKeysInMap() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "a");
        simpleMap.put(2, "b");
        simpleMap.put(3, "c");
        Iterator<Integer> it = simpleMap.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoElementOnlyNoSuchElementException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> it = simpleMap.iterator();
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}