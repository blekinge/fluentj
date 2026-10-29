package dk.blekinge.fluentj;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FluentJ2<TYPE1, TYPE2> {

    TYPE1 value1;
    TYPE2 value2;

    public FluentJ2(final TYPE1 value1, final TYPE2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public static <TYPE1, TYPE2> FluentJ2<TYPE1, TYPE2> of(final TYPE1 value1, final TYPE2 value2) {
        return new FluentJ2<>(value1, value2);
    }


    public <NEWTYPE> FluentJ2<NEWTYPE, TYPE2> map1(Function<TYPE1, NEWTYPE> operator) {
        return FluentJ2.of(operator.apply(value1), value2);
    }

    public <NEWTYPE> FluentJ2<TYPE1, NEWTYPE> map2(Function<TYPE2, NEWTYPE> operator) {
        return FluentJ2.of(value1, operator.apply(value2));
    }

    public <NEWTYPE> FluentJ<NEWTYPE> map2(BiFunction<TYPE1, TYPE2, NEWTYPE> operator) {
        return FluentJ.of(operator.apply(value1, value2));
    }

    public FluentJ2<TYPE1, TYPE2> invoke(BiConsumer<TYPE1, TYPE2> invoked) {
        invoked.accept(value1, value2);
        return this;
    }

    public TYPE1 get1() {
        return value1;
    }

    public TYPE2 get2() {
        return value2;
    }


}
