package de.snx.statsapi.mysql;

public abstract interface Callback<T> {
	public abstract void accept(T paramT);
}