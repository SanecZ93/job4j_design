package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void when0ElFindLastThen0() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(5, 4);
        tree.add(5, 0);
        assertThat(tree.findBy(0)).isPresent();
    }

    @Test
    void whenNullPointerException() {
        Tree<Integer> tree = new SimpleTree<>(0);
        assertThatThrownBy(() -> tree.add(0, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenIsNotBinaryIsTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(4, 6);
        tree.add(4, 7);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenIsNotBinaryIsFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        tree.add(4, 7);
        assertThat(tree.isBinary()).isFalse();
    }
}