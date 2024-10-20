package fr.exalt.businessmicroservicecustomer.domain.entities;

public class Customer {
    private String customerId;
    private String firstname;
    private String lastname;
    private String state;
    private String email;
    private String createdAt;
    private Address address;

    private Customer(Customer.CustomerBuilder builder) {
        this.customerId = builder.customerId;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.state = builder.state;
        this.email = builder.email;
        this.createdAt = builder.createdAt;
        this.address = builder.address;
    }

    public static Customer.CustomerBuilder builder (){
        return new Customer.CustomerBuilder();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public static class CustomerBuilder {
        private String customerId;
        private String firstname;
        private String lastname;
        private String state;
        private String email;
        private String createdAt;
        private Address address;

        public CustomerBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerBuilder state(String state) {
            this.state = state;
            return this;
        }
        public CustomerBuilder email(String email){
            this.email = email;
            return this;
        }

        public CustomerBuilder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CustomerBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }

    @Override
    public String toString() {
        return "Customer [" +
                "customer id='" + customerId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", created at='" + createdAt + '\'' +
                ", address=" + address +
                '}';
    }
}
