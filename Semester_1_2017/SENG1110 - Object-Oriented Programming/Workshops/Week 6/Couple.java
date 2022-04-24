public class Couple
{
    private Person he,she;
//--------------------------------------------------------------------------------------
    public Couple()
    {
        he  = new Person();
        she = new Person();

        she.age=15;
    }
//--------------------------------------------------------------------------------------
    public void addData(int option, String newName, int newAge)
    {
        if (option==1) setData1(she,newName,newAge);
        else           setData1(he,newName,newAge);
        
    }
    private void setData1(Person p, String name, int age)
    {
            p.setName(name);
            p.setAge(age);
    }
//--------------------------------------------------------------------------------------    
    public String test()
    {
       if (she.getAge() < he.getAge()) return("GOOD FOR "+he.getName()+"!");
       else                            return("GOOD FOR "+she.getName()+"!");
    }
}