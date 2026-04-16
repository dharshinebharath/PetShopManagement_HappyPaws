//package com.sprint.pet_shop.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class Supplier {
//
//	    @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    @Column(name = "supplier_id")
//	    private long supplierId;
//	    @Column(nullable = false , length=100)
//	    private String name;
//	    @Column(nullable = false , length=50 , name = "contact_person")
//	    private String contactPerson;
//	    @Column(nullable = false , length=20 , name = "phone_number")
//	    private String phoneNumber;
//	    @Column(nullable = false , length=100)
//	    private String email;
////	    @ManyToOne
////	    @JoinColumn(name = "address_id") 
////	    private Address address;
////		public Address getAddress() {
////			return address;
////		}
////		public void setAddress(Address address) {
////			this.address = address;
////		}
//		public long getSupplierId() {
//			return supplierId;
//		}
//		public void setSupplierId(long supplierId) {
//			this.supplierId = supplierId;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getContactPerson() {
//			return contactPerson;
//		}
//		public void setContactPerson(String contactPerson) {
//			this.contactPerson = contactPerson;
//		}
//		public String getPhoneNumber() {
//			return phoneNumber;
//		}
//		public void setPhoneNumber(String phoneNumber) {
//			this.phoneNumber = phoneNumber;
//		}
//		public String getEmail() {
//			return email;
//		}
//		public void setEmail(String email) {
//			this.email = email;
//		}
//		
//
//}
package com.sprint.pet_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private long supplierId;

    @Column(length = 100)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(length = 50, name = "contact_person")
    @NotNull(message = "Contact person cannot be null")
    @NotBlank(message = "Contact person cannot be empty")
    private String contactPerson;

    @Column(length = 20, name = "phone_number")
    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @Column(length = 100)
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    // Getters and Setters

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
