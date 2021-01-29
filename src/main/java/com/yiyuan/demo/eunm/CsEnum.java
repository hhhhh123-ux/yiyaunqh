package com.yiyuan.demo.eunm;

/**
 * @author SongYC
 */
public interface CsEnum {

    /**
     * 菜单默认父id
     */
    enum menu {
        Menu_PaeentId(0,"目录"),
        Menu_ParentId_One(1,"目录"),
        Menu_Ca(2,"菜单"),
        Menu_Ca_TWO(3,"菜单");
        private int value;
        private String message;

        menu(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int getValue() {
            return value;
        }

        public String getMessage() {
            return message;
        }

    }
}
