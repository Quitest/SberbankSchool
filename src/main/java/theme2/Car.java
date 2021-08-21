package theme2;

import lombok.Getter;
import lombok.Setter;

public class Car {
    @Setter
    @Getter
    private String model;
    @Setter
    @Getter
    private String type;

    public Car(String model, String type) {
        this.model = model;
        this.type = type;
    }
}
