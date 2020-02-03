package de.snx.simplestats.mysql;

public abstract interface Callback<T> {
	public abstract void accept(T paramT);
}