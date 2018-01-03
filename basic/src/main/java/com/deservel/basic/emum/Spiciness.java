package com.deservel.basic.emum;

/**
 * @author DeserveL
 * @date 2017/2/22 14:09
 * @since 1.0.0
 */
public enum Spiciness {
    NOT("呵呵"),MILD("123"),MEDIUM(""),HOT("hot"),FLAMING("flaming");

    private String description;

    /**
     * 默认为private，只能在enum定义的内部使用构造器创建enum实例
     *
     * @param description
     */
    Spiciness(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name()+":"+getDescription();
    }
}
