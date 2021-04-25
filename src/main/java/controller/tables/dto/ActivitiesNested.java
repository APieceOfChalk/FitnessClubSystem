package main.java.controller.tables.dto;

/**
 * Вспомогательный класс, чтобы сделать сложный json.
 */
public class ActivitiesNested {
    public String name;
    public area area;
    public trainer trainer;


    public ActivitiesNested(String name, main.java.controller.tables.dto.area area, main.java.controller.tables.dto.trainer trainer) {
        this.name = name;
        this.area = area;
        this.trainer = trainer;
    }
}
