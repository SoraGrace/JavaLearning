package com.fja.serial.gupao;

public interface Iserializer {
	
	public <T> byte[] serializer(T obj);
	
	public <T> T deSerializer(byte[] data,Class<T> clazz);
}
