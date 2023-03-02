package de.devsnx.statsapi.mysql;

public abstract interface Callback<T> {
	public abstract void accept(T paramT);
}