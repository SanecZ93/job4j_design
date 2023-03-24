package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        boolean rsl = box.isExist();
        int vertex = box.getNumberOfVertices();
        double area = box.getArea();
        assertThat(name).isEqualTo("Sphere");
        assertThat(rsl).isTrue();
        assertThat(vertex).isEqualTo(0);
        assertThat(area).isEqualTo(1256.6370614359173);
    }
}