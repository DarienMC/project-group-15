package ca.mcgill.ecse321.tutoringapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringapp.Database.src.Course;
import ca.mcgill.ecse321.tutoringapp.Database.src.Person;
import ca.mcgill.ecse321.tutoringapp.Database.src.PersonRole;
import ca.mcgill.ecse321.tutoringapp.Database.src.Tutor;
import ca.mcgill.ecse321.tutoringapp.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringapp.dao.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
//	@Autowired
//	PersonRoleRepository personRoleRepository;
	
	@Transactional
	public Person createPerson(String firstname, String lastname, String username) {
		Person person = new Person();
		person.setUserName(username);
		person.setFirstName(firstname);
		person.setLastName(lastname);
		personRepository.save(person);
		
		return person;
	}
	
	@Transactional
	public Person modifyPersonName(Person person, String newFirstName, String newLastName) {
		person.setFirstName(newFirstName);
		person.setLastName(newLastName);
		personRepository.save(person);
		return person;
	}
	
	@Transactional
	public List<Person> getAllPersons(){
		return toList(personRepository.findAll());
	}
	
	
	@Transactional
	public boolean removePerson(String userName) {
		Person person = personRepository.findPersonByUserName(userName);

		if(person == null) {
			throw new NullPointerException("No such person exists");
		}
		personRepository.delete(person);
		return true;
	}
	
	@Transactional
	public Person getPersonByUsername(String username) {
		return personRepository.findPersonByUserName(username);
	}
	@Transactional
	public void addPersonRole(Person person, PersonRole role) {
		person.addPersonRole(role);
		personRepository.save(person);
	}
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
