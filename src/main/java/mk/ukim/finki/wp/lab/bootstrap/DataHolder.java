package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;

    @PostConstruct
    public void init(){
        registrations = new ArrayList<>();
        workshops = new ArrayList<>();

        workshops.add(new Workshop("Java Fundamentals", "Programming", 1500, 20));
        workshops.add(new Workshop("Spring Boot", "Programming", 2000, 15));
        workshops.add(new Workshop("Web Development", "Web", 1800, 25));
        workshops.add(new Workshop("Database Design", "Databases", 1600, 10));
        workshops.add(new Workshop("UI/UX Design", "Design", 1200, 12));
        workshops.add(new Workshop("Cyber Security", "Security", 2500, 8));
        workshops.add(new Workshop("Machine Learning", "AI", 3000, 5));
        workshops.add(new Workshop("Cloud Computing", "Cloud", 2200, 10));
        workshops.add(new Workshop("Mobile Development", "Mobile", 1900, 0));
        workshops.add(new Workshop("DevOps", "Programming", 2300, 7));
    }

}
