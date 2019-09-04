package interfaces;

public class Student implements Comparable<Student> {
    private String name;
    private int id;
    // constructor – code not shown
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return name.compareTo(otherStudent.name);
        /*if (this.id < otherStudent.id)
            return -1;
        else if (this.id == otherStudent.id){
            return 0;        }
        else
            return 1;

         */
    }

    @Override
    public String toString() {
        return name + ": " + id;
    }


}
