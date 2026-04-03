package com.example.util;

public class Pair<A,B>
{
	public A first;
	public B second;
	public Pair(A a, B b)
	{
		first = a;
		second = b;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Pair)) return false;
		Pair p2 = (Pair)obj;
		if(!first.equals(p2.first)) return false;
		return second.equals(p2.second);
	}
}
