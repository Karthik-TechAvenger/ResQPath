package com.src.jtesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.src.model.Wall;

class jtest {

    @Test
    void testWallConstructor() {
        Wall wall = new Wall(10, 20);
        assertEquals(10, wall.getX(), "Constructor should correctly set X");
        assertEquals(20, wall.getY(), "Constructor should correctly set Y");
    }

    @Test
    void testGetX() {
        Wall wall = new Wall(5, 15);
        assertEquals(5, wall.getX(), "getX() should return the correct X value");
    }

    @Test
    void testGetY() {
        Wall wall = new Wall(5, 15);
        assertEquals(15, wall.getY(), "getY() should return the correct Y value");
    }

    @Test
    void testSetX() {
        Wall wall = new Wall(0, 0);
        wall.setX(50);
        assertEquals(50, wall.getX(), "setX() should update the X value");
    }

    @Test
    void testSetY() {
        Wall wall = new Wall(0, 0);
        wall.setY(60);
        assertEquals(60, wall.getY(), "setY() should update the Y value");
    }

    // ✅ Additional Edge Case Testing
    @Test
    void testNegativeValues() {
        Wall wall = new Wall(-5, -10);
        assertEquals(-5, wall.getX(), "Constructor should allow negative X");
        assertEquals(-10, wall.getY(), "Constructor should allow negative Y");
    }

    @Test
    void testLargeValues() {
        Wall wall = new Wall(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals(Integer.MAX_VALUE, wall.getX(), "Should handle max int value");
        assertEquals(Integer.MIN_VALUE, wall.getY(), "Should handle min int value");
    }

    @Test
    void testSetToZero() {
        Wall wall = new Wall(100, 200);
        wall.setX(0);
        wall.setY(0);
        assertEquals(0, wall.getX(), "setX() should allow zero");
        assertEquals(0, wall.getY(), "setY() should allow zero");
    }
}
