package com.ashvin.msgboard.utils;

import javax.crypto.*;
import java.nio.*;
import java.util.*;
import java.security.*;
import javax.crypto.spec.*;
import com.ashvin.msgboard.dao.DAOException;

public class EncryptionUtility
{
private static final String ALGORITHM="AES/GCM/NoPadding";
private static final int KEY_SIZE_BITS=256;
private static final int IV_SIZE_BYTES=12;
private static final int TAG_SIZE_BITS=128;
private EncryptionUtility()
{
}
public static String getKey() throws DAOException
{
try
{
    //write code to generate secret/salt key
KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
keyGenerator.init(KEY_SIZE_BITS,SecureRandom.getInstanceStrong());
SecretKey secretKey=keyGenerator.generateKey();
return Base64.getEncoder().encodeToString(secretKey.getEncoded());
}catch(Exception exception)
{
System.out.println(exception);
throw new DAOException("Unable to create Key");
}
}
public static String encrypt(String plaintext,String key) throws DAOException
{
//write code to encrypt data
try
{
byte[] initializationVector=new byte[IV_SIZE_BYTES];
SecureRandom.getInstanceStrong().nextBytes(initializationVector);

byte[] decodedKey=Base64.getDecoder().decode(key);
SecretKey secretKey=new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");

Cipher cipher=Cipher.getInstance(ALGORITHM);
GCMParameterSpec parameterSpec=new GCMParameterSpec(TAG_SIZE_BITS,initializationVector);
cipher.init(Cipher.ENCRYPT_MODE,secretKey,parameterSpec);

byte[] cipherText=cipher.doFinal(plaintext.getBytes());

ByteBuffer byteBuffer=ByteBuffer.allocate(initializationVector.length+cipherText.length);
byteBuffer.put(initializationVector);
byteBuffer.put(cipherText);
return Base64.getEncoder().encodeToString(byteBuffer.array());
}catch(Exception exception)
{
System.out.println(exception);
throw new DAOException("Unable to encrypt your data");
}
}
public static String decrypt(String encryptedData,String key) throws DAOException
{
//write code to decrypt password;
try
{
byte[] decoded=Base64.getDecoder().decode(encryptedData);
ByteBuffer byteBuffer=ByteBuffer.wrap(decoded);
byte[] initializationVector=new byte[IV_SIZE_BYTES];
byteBuffer.get(initializationVector);
byte[] cipherText=new byte[byteBuffer.remaining()];
byteBuffer.get(cipherText);

byte[] decodedKey=Base64.getDecoder().decode(key);
SecretKey secretKey=new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");

Cipher cipher=Cipher.getInstance(ALGORITHM);
GCMParameterSpec parameterSpec=new GCMParameterSpec(TAG_SIZE_BITS,initializationVector);
cipher.init(Cipher.DECRYPT_MODE,secretKey,parameterSpec);

byte[] decryptedBytes=cipher.doFinal(cipherText);
return new String(decryptedBytes);

}catch(Exception exception)
{
System.out.println(exception);
throw new DAOException("Unable to encrypt your data");
}
}
public static void main(String args[])
{
try
{
String data="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtsuvwxyz1234567890";
System.out.println("Data: "+data);
String key=EncryptionUtility.getKey();
System.out.println("Key: "+key);
String encryptedData=EncryptionUtility.encrypt(data,key);
System.out.println("EncryptedData: "+encryptedData);
String decryptedData=EncryptionUtility.decrypt(encryptedData,key);
System.out.println("DecryptedData: "+decryptedData);
}catch(DAOException dao)
{
System.out.println(dao);
}
}
}
