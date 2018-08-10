package com.fja.serial.gupao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fja.serial.model.User;

public class NativeJavaSerializer implements Iserializer{

	@Override
	public <T> byte[] serializer(T obj) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		byte[] b = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			b = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(oos!=null)oos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deSerializer(byte[] data, Class<T> clazz) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = null;
		T t = null;
		try {
			ois = new ObjectInputStream(bis);
			t = (T)ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				bis.close();
				if(ois!=null)ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return t;
	}
}

class TestSerial{
	public static void main(String[] args) {
		Iserializer iSerializer = new NativeJavaSerializer();
		User user = new User();
		user.setName("铁蛋");
		user.setAge(18);
		byte[] bytes = iSerializer.serializer(user);
		
		User ironEgg = iSerializer.deSerializer(bytes, User.class);
		System.out.println(ironEgg);
	}
}
