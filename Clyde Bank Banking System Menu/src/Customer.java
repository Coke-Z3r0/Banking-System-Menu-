/**
 * Name: Customer.Java
 * Author:Lee McGuire Faud
 * Date: 11/2/2023
 * Description: This is the Class that Allows Users to set their Name in the bank.
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String password;

    public Customer(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
}
