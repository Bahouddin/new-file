package by.bsu.appAds.jdbc.domain;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User extends Entity {
	    private String login;
	    private String password;
	    private Role role;
	    private String lastname;
	    private String firstname;
	    private String patronymic;
	    private String email;
	    private long phone;
	    private String city;
	    private Blob picture;
	    private Date date;
	    private Integer user_info_id;
	    private String text;
	    private String path_img;
	    private Integer price;
	    private Boolean status;
	    private Boolean isSell;
	    private Integer category_id;
	    
	    public Boolean getIsSell() {
			return isSell;
		}
		public void setIsSell(Boolean isSell) {
			this.isSell = isSell;
		}
		public Integer getCategory_id() {
			return category_id;
		}
		public void setCategory_id(Integer category_id) {
			this.category_id = category_id;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Integer getUser_info_id() {
			return user_info_id;
		}
		public void setUser_info_id(Integer user_info_id) {
			this.user_info_id = user_info_id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getPath_img() {
			return path_img;
		}
		public void setPath_img(String path_img) {
			this.path_img = path_img;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public void setNotes(List<Note> notes) {
			this.notes = notes;
		}
		private List<Note> notes = new ArrayList<Note>();
	    
		public String getLogin() {
			return login;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public Role getRole() {
	        return role;
	    }

	    public void setRole(Role role) {
	        this.role = role;
	    }
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getPatronymic() {
			return patronymic;
		}
		public void setPatronymic(String patronymic) {
			this.patronymic = patronymic;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public long getPhone() {
			return phone;
		}
		public void setPhone(long phone) {
			this.phone = phone;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public Blob getPicture() {
			return picture;
		}
		public void setPicture(Blob picture) {
			this.picture = picture;
		}
		public List<Note> getNotes() {
	        return notes;
	    }
		public User() {
			super();
		}
		public User(String login, String password) {
			super();
			this.login = login;
			this.password = password;
			this.role = Role.USER;
		}
		

		public User(String login, String password, by.bsu.appAds.jdbc.domain.Role role, String lastname, String firstname,
				String patronymic, String email, long phone, String city, Blob picture) {
			super();
			this.login = login;
			this.password = password;
			this.role = role;
			this.lastname = lastname;
			this.firstname = firstname;
			this.patronymic = patronymic;
			this.email = email;
			this.phone = phone;
			this.city = city;
			this.picture = picture;
			System.out.println(picture);
		}
		@Override
		public String toString() {
			return "User [login=" + login + ", password=" + password + ", role=" + role + ", lastname=" + lastname
					+ ", firstname=" + firstname + ", patronymic=" + patronymic + ", email=" + email + ", phone="
					+ phone + ", city=" + city + ", picture=" + picture + ", notes=" + notes + "]";
		}
		public void setStatus(String string) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
		
	}