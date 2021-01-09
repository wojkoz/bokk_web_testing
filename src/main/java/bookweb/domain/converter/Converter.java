package bookweb.domain.converter;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
