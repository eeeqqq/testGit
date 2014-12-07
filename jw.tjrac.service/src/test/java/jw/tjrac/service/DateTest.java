package jw.tjrac.service;

import org.junit.Test;

public class DateTest {

    @Test
    public void TestDate() {
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        System.out.println(sqlDate);
    }
}
