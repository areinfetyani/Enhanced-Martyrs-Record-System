import java.util.Date;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private int age;
	private Date dateOfDeath;
	private char gender;
	public Martyr(String name, int age, Date dateOfDeath, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.dateOfDeath = dateOfDeath;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public int compareTo(Martyr o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	public String toString() {
		return "["+getName() + ", " + getAge() +", "+ getDateOfDeath() +", " + getGender()+"]";
	}
	
	
	
}
