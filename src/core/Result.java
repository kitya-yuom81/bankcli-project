package core;

import java.util.Optional;

public record Result<T>(boolean ok, String message, T valueOrNull) {
    public static <T> Result<T> success(String msg, T value) {
        return new Result<>(true, msg, value);
    }
    public static <T> Result<T> fail(String msg) {
        return new Result<>(false, msg, null);
    }
    public Optional<T> value() { return Optional.ofNullable(valueOrNull); }
    public String message() { return message; }
}
