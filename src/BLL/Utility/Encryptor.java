package BLL.Utility;

import BLL.Utility.Bcypt.BCrypt;

import java.io.IOException;

public class Encryptor
{
    /**
     * encrypts a string
     * @param string
     * @return
     * @throws IOException
     */
    public String Encrypt(String string) throws IOException
    {
        return BCrypt.hashpw(string, BCrypt.gensalt(6));
    }

    /**
     * checks if a string is the same as its hashed counterpart
     * @param string
     * @param hashed
     * @return
     * @throws IOException
     */
    public boolean check(String string, String hashed) throws IOException
    {
        return BCrypt.checkpw(string, hashed);
    }
}
