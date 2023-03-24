package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .containsOnly("second", "first", "five",  "three", "four")
                .containsExactly("first", "second", "three", "four", "five")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .endsWith("four", "five")
                .containsSequence("second", "three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .doesNotHaveDuplicates()
                .doesNotContainNull()
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .containsExactlyInAnyOrder("second", "first", "four", "five", "three")
                .doesNotContain("zero");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("zero", "first", "first", "second", "three", "four");
        assertThat(map).hasSize(5)
                  .doesNotContainValue(2)
                  .containsValues(0, 1, 3, 4)
                  .containsKeys("zero", "first", "second", "three", "four")
                  .doesNotContainKeys("five", "six", "seven")
                  .doesNotContainValue(6)
                  .doesNotContainEntry("six", 6);
    }
}