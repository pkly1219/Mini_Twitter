package homework2;
//Implement a Singleton to ensure only one instance of MiniTwitter exists.
public class MiniTwitter extends AdminControlPanel {
    private static MiniTwitter instance = null;   //Singleton instance
    private MiniTwitter()      // Private Constructor
    {
    }
    public static MiniTwitter getInstance() {
        //Check if the instance is null or not
        if (instance == null)
        {
            instance = new MiniTwitter();

        }
        return instance;
    }
}