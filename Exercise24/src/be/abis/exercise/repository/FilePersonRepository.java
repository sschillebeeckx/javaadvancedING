package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.util.DateUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePersonRepository implements PersonRepository {

    private static FilePersonRepository instance;
    private List<Person> allPersons = new ArrayList<Person>();
    private static final String FILELOCATION = "/temp/javacourses/persons.csv";


    private FilePersonRepository() throws IOException {
        this.readFile();
    }

    public static FilePersonRepository getInstance() throws IOException {
        if (instance==null) instance= new FilePersonRepository();
        return instance;
    }

    public void readFile() throws IOException {

        if (allPersons.size() != 0) allPersons.clear();
        List<String> lines = Files.readAllLines(Paths.get(FILELOCATION));
        for (String line : lines) {
            String[] vals = line.split(";");
            if (!vals[0].equals("")) {
                Company c = null;
                String compName = vals[6];
                if (!compName.equals("null")) {
                    Address a = new Address();
                    a.setStreet(!vals[7].equals("null") ? vals[7] : null);
                    a.setNr(!vals[8].equals("null") ? vals[8] : null);
                    a.setZipCode(!vals[9].equals("null") ? vals[9] : null);
                    a.setTown(!vals[10].equals("null") ? vals[10] : null);
                    a.setCountry(!vals[11].equals("null") ? vals[11] : null);
                    a.setCountryCode(!vals[12].equals("null") ? vals[12] : null);

                    c = new Company();
                    c.setName(!vals[6].equals("null") ? vals[6] : null);
                    c.setAddress(a);
                }

                Person p = new Person();
                p.setPersonNumber(!vals[0].equals("null") ? Integer.parseInt(vals[0]) : 0);
                p.setFirstName(!vals[1].equals("null") ? vals[1] : null);
                p.setLastName(!vals[2].equals("null") ? vals[2] : null);

                p.setBirthDate(!vals[3].equals("null") ? DateUtils.parse(vals[3]) : null);
                p.setEmail(!vals[4].equals("null") ? vals[4] : null);
                p.setPassword(!vals[5].equals("null") ? vals[5] : null);

                if (c != null) p.setCompany(c);

                allPersons.add(p);
            }
        }
    }

    public List<Person> getAllPersons() {
        return allPersons;
    }

    @Override
    public void addPerson(Person p) throws IOException, PersonAlreadyExistsException {

        if(allPersons.contains(p)) throw new PersonAlreadyExistsException(p.getFirstName() + ", you were already registered.");

        PrintWriter pw = new PrintWriter(new FileWriter(FILELOCATION, true));
        String s = this.convertPersonToString(p);
        pw.append("\n" + s);
        pw.close();

        allPersons.add(p);

    }


    private String convertPersonToString(Person p) {
        Company c = p.getCompany();
        StringBuilder sb = new StringBuilder();
        sb.append(p.getPersonNumber()).append(";")
                .append(p.getFirstName()).append(";")
                .append(p.getLastName()).append(";")
                .append(DateUtils.format(p.getBirthDate())).append(";")
                .append(p.getEmail()).append(";")
                .append(p.getPassword()).append(";")
                .append(c != null ? c.getName() : "null").append(";")
                .append(c != null ? c.getAddress().getStreet() : "null").append(";")
                .append(c != null ? c.getAddress().getNr() : "null").append(";")
                .append(c != null ? c.getAddress().getZipCode() : "null").append(";")
                .append(c != null ? c.getAddress().getTown() : "null").append(";")
                .append(c != null ? c.getAddress().getCountry() : "null").append(";")
                .append(c != null ? c.getAddress().getCountryCode() : "null").append(";");
        return sb.toString();
    }


}
