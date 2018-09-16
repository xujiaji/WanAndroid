package com.google.common.base;

import com.annimon.stream.Objects;
import com.annimon.stream.OptionalDouble;
import com.annimon.stream.OptionalInt;
import com.annimon.stream.OptionalLong;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;

import java.util.NoSuchElementException;

/**
 * author: xujiaji
 * created on: 2018/9/17 0:41
 * description: 由于Dagger2 使用@BindsOptionalOf，必须使用{@code com.google.common.base.Optional} or {@code java.util.Optional}.<br>
 * 但是引入Guava这么大个包很没有必要，{@code java.util.Optional}有必须得API 24+ <br>
 * 因此写了这个中间层，包的路径为Guava中Optional的路径
 */
public class Optional<T> {
    private com.annimon.stream.Optional<T> optional;
    private static final Optional<?> EMPTY = new Optional();

    private final T value;

    private Optional() {
        this.value = null;
    }

    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
        optional = com.annimon.stream.Optional.of(value);
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? Optional.<T>empty() : of(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    public static <T> Optional<T> absent() {
        return (Optional<T>) EMPTY;
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable emptyAction) {
        if (value != null) {
            consumer.accept(value);
        } else {
            emptyAction.run();
        }
    }

    public Optional<T> executeIfPresent(Consumer<? super T> consumer) {
        ifPresent(consumer);
        return this;
    }

    public Optional<T> executeIfAbsent(Runnable action) {
        if (value == null)
            action.run();
        return this;
    }

    public <R> R custom(Function<Optional<T>, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (!isPresent()) return this;
        return predicate.test(value) ? this : Optional.<T>empty();
    }

    public Optional<T> filterNot(Predicate<? super T> predicate) {
        return filter(Predicate.Util.negate(predicate));
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        if (!isPresent()) return empty();
        return Optional.ofNullable(mapper.apply(value));
    }

    public OptionalInt mapToInt(ToIntFunction<? super T> mapper) {
        if (!isPresent()) return OptionalInt.empty();
        return OptionalInt.of(mapper.applyAsInt(value));
    }

    public OptionalLong mapToLong(ToLongFunction<? super T> mapper) {
        if (!isPresent()) return OptionalLong.empty();
        return OptionalLong.of(mapper.applyAsLong(value));
    }

    public OptionalDouble mapToDouble(ToDoubleFunction<? super T> mapper) {
        if (!isPresent()) return OptionalDouble.empty();
        return OptionalDouble.of(mapper.applyAsDouble(value));
    }

    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        if (!isPresent()) return empty();
        return Objects.requireNonNull(mapper.apply(value));
    }

    @SuppressWarnings("unchecked")
    public Stream<T> stream() {
        if (!isPresent()) return Stream.empty();
        return Stream.of(value);
    }

    @SuppressWarnings("unchecked")
    public <R> Optional<R> select(Class<R> clazz) {
        Objects.requireNonNull(clazz);
        if (!isPresent()) return empty();
        return (Optional<R>) Optional.ofNullable(clazz.isInstance(value) ? value : null);
    }

    public Optional<T> or(Supplier<Optional<T>> supplier) {
        if (isPresent()) return this;
        Objects.requireNonNull(supplier);
        return Objects.requireNonNull(supplier.get());
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exc) throws X {
        if (value != null) return value;
        else throw exc.get();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof com.annimon.stream.Optional)) {
            return false;
        }

        Optional<?> other = (Optional<?>) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value != null
                ? String.format("Optional[%s]", value)
                : "Optional.empty";
    }
}
