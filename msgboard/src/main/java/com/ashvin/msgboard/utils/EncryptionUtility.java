package com.ashvin.msgboard.utils;

/* 
Don't waster your time on thinking or writing about Encryption/Decryption
Look for java AES encryption / decryption Example
*/

public class EncryptionUtility
{
public EncryptionUtility()
{
}
public static String getKey()
{
String key;
//write code to generate secret/ salt key
return "abcdefghijklmnopqrstuvwxyz";
}
public static String encrypt(String password,String key)
{
String encryptedPassword;
//write code to encrypt
encryptedPassword=password;
return encryptedPassword;
}
public static String decrypt(String encryptedPassword,String key)
{
String password;
//write code to decrypt password;
password=encryptedPassword;
return password;
}
}
