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

        // Programming Category
        DataHolder.registrations.add(new Registration("Java Fundamentals", "Alice Smith", "alice@example.com", 2));
        DataHolder.registrations.add(new Registration("Spring Boot", "Bob Jones", "bob@example.com", 1));
        DataHolder.registrations.add(new Registration("DevOps", "Charlie Brown", "charlie@example.com", 3));

        // Web Category
        DataHolder.registrations.add(new Registration("Web Development", "Diana Prince", "diana@example.com", 1));

        // Databases Category
        DataHolder.registrations.add(new Registration("Database Design", "Evan Wright", "evan@example.com", 2));

        // Design Category
        DataHolder.registrations.add(new Registration("UI/UX Design", "Fiona Gallagher", "fiona@example.com", 1));

        // Security Category
        DataHolder.registrations.add(new Registration("Cyber Security", "George Clark", "george@example.com", 2));

        // AI Category
        DataHolder.registrations.add(new Registration("Machine Learning", "Hannah Abbott", "hannah@example.com", 1));

        // Cloud Category
        DataHolder.registrations.add(new Registration("Cloud Computing", "Ian Malcolm", "ian@example.com", 2));

        // Mobile Category
        DataHolder.registrations.add(new Registration("Mobile Development", "Julia Roberts", "julia@example.com", 1));
    }

}
