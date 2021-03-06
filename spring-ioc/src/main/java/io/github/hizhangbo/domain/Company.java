package io.github.hizhangbo.domain;

/**
 * @author Bob
 * @since 2020/12/6 14:53
 */
public class Company {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
