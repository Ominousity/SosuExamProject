package BLL.Utility;

import BLL.Utility.Bcypt.BCrypt;

public class Encryptor
{
    public String Encrypt(String string){
        return BCrypt.hashpw(string, BCrypt.gensalt(6));
    }

    public boolean check(String string, String hashed){
        return BCrypt.checkpw(string, hashed);
    }
}
