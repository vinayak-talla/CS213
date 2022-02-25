/**
 *
 * @author Alvin Alex, Vinayak Talla
 *
 * Class that represents a person with an acount using first name, last name, and date of birth
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructor for Profile that initializes Profile object with first name, last name, and date of birth
     * @param fname the first name for the profile
     * @param lname the last name for the profile
     * @param dob the date of birth of the person
     */
    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Method to print out a Profile object
     * @return Profile object formatted as a String
     */
    public String toString(){
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }

    public boolean equals(Profile profile){ // test this method
        if(this.fname.equals(profile.fname)){
            if(this.lname.equals(profile.lname)){
                if(this.dob.compareTo(profile.dob) == 0){
                    return true;
                }
            }
        }
        return false;
    }

}
