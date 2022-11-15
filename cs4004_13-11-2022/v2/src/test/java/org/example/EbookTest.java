package org.example;

import org.junit.jupiter.api.Test;

public class EbookTest {
    @Test
    public void ebook(){
        Ebook b1 = new Ebook("a1", "01/02/0003", "title1", "1", "pub1", "a");
        Ebook b2 = new Ebook("a1", "06/12/2035", "title1", "1", "pub1");
    }
}
