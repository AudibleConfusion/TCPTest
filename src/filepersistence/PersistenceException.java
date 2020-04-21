package filepersistence;

public class PersistenceException extends Exception
{
    PersistenceException(String msg)
    {
        super(msg);
    }
    PersistenceException()
    {
        super();
    }
}
