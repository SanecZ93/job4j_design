package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        input.add(12);
        input.add(11);
        Predicate<Integer> filter = f -> f % 2 != 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).containsSequence(12);
    }

    @Test
    void whenReplaceIf() {
        input.add(-1);
        input.add(2);
        Predicate<Integer> filter = f -> f > 0;
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input).containsSequence(1, 3, 0, 2);
    }

    @Test
    void whenRemoveAll() {
        input.add(0);
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input).containsSequence(0);
    }
}