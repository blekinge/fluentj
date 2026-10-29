package dk.blekinge.fluentj;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FluentJ<TYPE> {

    TYPE value;

    public FluentJ(final TYPE value) {
        this.value = value;
    }

    public static <TYPE> FluentJ<TYPE> of(TYPE item) {
        return new FluentJ<>(item);
    }

    public <NEWTYPE> FluentJ<NEWTYPE> map(Function<TYPE, NEWTYPE> mapper) {
        return of(mapper.apply(value));
    }

    public FluentJ<TYPE> invoke(Consumer<TYPE> invoked) {
        invoked.accept(value);
        return this;
    }

    public FluentJ<TYPE> call(Consumer<TYPE> called) {
        return invoke(called);
    }

    public FluentJ<TYPE> peek(Consumer<TYPE> peeker) {
        return invoke(peeker);
    }

    public TYPE get() {
        return value;
    }

    public Optional<TYPE> asOptional(){
        return Optional.ofNullable(value);
    }

    public Stream<TYPE> asStream(){
        return Stream.of(value);
    }

    public FluentJ.IfNull<TYPE> ifNull() {
        return new IfNull<>(value);
    }


    public static class IfNull<TYPE> {
        private final TYPE value;

        public IfNull(final TYPE value) {
            this.value = value;
        }

        public FluentJ<TYPE> replaceWith(TYPE replacement) {
            if (value != null) {
                return FluentJ.of(value);
            }
            return FluentJ.of(replacement);
        }

        public <E extends Exception> FluentJ<TYPE> thenThrow(Supplier<E> exceptionProducer) throws E {
            if (value != null) {
                return FluentJ.of(value);
            }
            throw exceptionProducer.get();
        }
    }
}
