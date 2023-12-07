package homework2;

 //The Visitor interface is used to implement visitor pattern.
public interface Visitor {
   public void visit(User user);
   public void visit(Group group);

}
