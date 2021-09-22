package com.lesson;

/**
 * @author Yu Jian
 * @create 2021/9/22 10:38
 */

public class Animal {
    private String name;
    private boolean healthy;

    public Animal(String name) {
        this.name = name;
        this.healthy = true;
    }

    public Animal(String name, boolean healthy) {
        this.name = name;
        this.healthy = healthy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}
