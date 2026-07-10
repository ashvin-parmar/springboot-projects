package com.ashvin.msgboard.utils;

/**
 * @author Ashvin
 * @since 2026-07-10
 * Description: 
 */
public class ValidateUtility 
{
public static boolean isValidEmail(String email)
{
//later on added
if(!email.endsWith("@gmail.com")) return false;
return true;
}
public static boolean isStrongPassword(String password)
{
if(password.length()<8) return false;
return true;
}
}

