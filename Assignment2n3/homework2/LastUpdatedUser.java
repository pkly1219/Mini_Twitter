package homework2;

public class LastUpdatedUser implements Visitor {

    private long time = 0;      // Variable to keep track of the last updated time
    private User lastUpdateUser ;    // Variable to store the user with the last update


    @Override
    public void visit(User user) {
        // Check if the user has never been updated (initialization state)
        // and if the user has a later update time than the current stored time
        if (user.getLastUpdateTime() != -1 && user.getLastUpdateTime() > time) {
            // Update the last updated time and store the reference to the user
            time = user.getLastUpdateTime();
            lastUpdateUser = user;
        }
    }

    @Override
    public void visit(Group group) {
        // Handle group visit logic if needed
    }

    public User getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUsern(User lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}
