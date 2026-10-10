

import java.util.ArrayList;
import java.util.List;

public class ResumeProfile {
    private String fullName;
    private String title;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private String photoPath;
    private List<Experience> experiences;

    public ResumeProfile(String fullName, String title, String email, String phone, String address, String summary, String photoPath) {
        this.fullName = fullName;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.summary = summary;
        this.photoPath = photoPath;
        this.experiences = new ArrayList<>();
    }

    public void addExperience(Experience exp) {
        this.experiences.add(exp);
    }

    public String getFullName() { return fullName; }
    public String getTitle() { return title; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getSummary() { return summary; }
    public String getPhotoPath() { return photoPath; }
    public List<Experience> getExperiences() { return experiences; }
}