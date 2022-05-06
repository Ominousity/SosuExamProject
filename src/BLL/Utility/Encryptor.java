package BLL.Utility;

import BLL.Utility.Bcypt.BCrypt;

import java.io.IOException;

public class Encryptor
{
    public String Encrypt(String string) throws IOException
    {
        return BCrypt.hashpw(string, BCrypt.gensalt(6));
    }

    public boolean check(String string, String hashed) throws IOException
    {
        return BCrypt.checkpw(string, hashed);
    }
}
