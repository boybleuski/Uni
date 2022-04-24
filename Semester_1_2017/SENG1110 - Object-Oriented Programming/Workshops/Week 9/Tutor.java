public class Tutor
{
    String[] name = new String[4];
    int[] grade = new int[4];
    int contactHours;

    public Tutor(String[] nm,int[] grd,int contact ){
        name =nm;
        grade = grd;
        contactHours = contact;
    }
    
    public void printNames(){
        System.out.println("The four tutors are : "+ name[0]+"\t"+name[1]+"\t"+name[2]+"\t"+name[3]); 
    }
    
    public void printGrades(){
        System.out.println(name[0]+"'s grade is : "+ grade[0]); 
        System.out.println(name[1]+"'s grade is : "+ grade[1]);
        System.out.println(name[2]+"'s grade is : "+ grade[2]);
        System.out.println(name[3]+"'s grade is : "+ grade[3]);
    }
    public void printContactHours(){
        System.out.println("The total contact hours is :"+ contactHours);    
    }
}
