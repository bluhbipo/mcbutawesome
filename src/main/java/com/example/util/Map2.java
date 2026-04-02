package com.example.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Map2<K1,K2,V> implements Map<K1, Map<K2, V>>
{

	@Override
	public int size()
	{
		return 0;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public boolean containsKey(Object key)
	{
		return false;
	}

	@Override
	public boolean containsValue(Object value)
	{
		return false;
	}

	@Override
	public Map<K2, V> get(Object key)
	{
		return Collections.emptyMap();
	}

	@Override
	public @Nullable Map<K2, V> put(K1 key, Map<K2, V> value)
	{
		return Collections.emptyMap();
	}

	@Override
	public Map<K2, V> remove(Object key)
	{
		return Collections.emptyMap();
	}

	@Override
	public void putAll(@NotNull Map<? extends K1, ? extends Map<K2, V>> m)
	{

	}

	@Override
	public void clear()
	{

	}

	@Override
	public @NotNull Set<K1> keySet()
	{
		return Collections.emptySet();
	}

	@Override
	public @NotNull Collection<Map<K2, V>> values()
	{
		return Collections.emptyList();
	}

	@Override
	public @NotNull Set<Entry<K1, Map<K2, V>>> entrySet()
	{
		return Collections.emptySet();
	}
}
