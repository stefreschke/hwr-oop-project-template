package hwr.oop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pin {
    public Pin(int pinNumber, boolean standing) {
        this.pinNumber = pinNumber;
        this.standing = standing;
    }

    public int pinNumber;
    public boolean standing;


}