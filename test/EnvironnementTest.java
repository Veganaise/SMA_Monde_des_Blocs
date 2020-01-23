import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnvironnementTest {

    @Test
    void testId() {
        int test = 'a' + 1;
        assertEquals(test, 'b');
    }

    @Test
    void testStack() {
        Stack<Integer> colonne = new Stack<>();
        for (int i = 0; i <= 4; ++i) {
            colonne.add(i);
        }
        assertEquals(colonne.search(0), 5);
        assertEquals(colonne.search(4), 1);
        colonne.push(40);
        assertEquals(colonne.search(0), 6);
        assertEquals(colonne.search(4), 2);
        colonne.pop();
        assertEquals(colonne.search(0), 5);
        assertEquals(colonne.search(4), 1);
    }
}