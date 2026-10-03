public class Participant {
    private String firstName;
    private String lastName;
    private boolean isVerified = true;      // w pliku przy nazwie jest np.: (Niezweryfikowany)
    private boolean isExternal = false;     // w pliku przy nazwie: (Zewnętrzny)
    private boolean isGuest = false;        // w pliku przy nazwie: (Gość)
    private String joinTime;
    private String leaveTime;
    private String duration;
    private String email;
    private String participantID;
    private String role;
    private boolean certificate = false;    // zaświadczenie

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParticipantID() {
        return participantID;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isCertificate() {
        return certificate;
    }

    public String hasCertificate() {
        return (certificate) ? "TAK" : "NIE";
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }
}