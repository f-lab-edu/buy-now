package flab.buynow.common;

import com.fasterxml.uuid.Generators;

public class Util {

    public static String getUuidBasedOnTime() {
        return Generators.timeBasedGenerator().generate().toString();
    }

}
