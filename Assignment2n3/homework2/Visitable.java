package homework2;

//The Visitable interface declares the accept method, allowing objects to
// accept a Visitor and execute a specific operation.
public interface Visitable {
    public void accept(Visitor visitor);
}