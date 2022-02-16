public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String toString(){
        return this.fname + " " + this.lname + "DOB: " + this.dob.toString();
    }

    public boolean equals(Profile profile){
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
