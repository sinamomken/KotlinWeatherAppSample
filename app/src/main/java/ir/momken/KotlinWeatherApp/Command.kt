package ir.momken.KotlinWeatherApp

public interface Command<out T> {
    fun execute(): T
}