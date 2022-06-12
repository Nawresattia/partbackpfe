package com.example.RestApi.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;

import com.example.RestApi.entities.Msg;
import com.example.RestApi.entities.User;
import com.example.RestApi.repository.MsgRepository;
import com.example.RestApi.repository.ParcRoulantRepository;
import com.example.RestApi.repository.ReceptionRepository;
import com.example.RestApi.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	private final UserRepository userrepo;
	private final ParcRoulantRepository ParcRoulancerepo;
	private final ReceptionRepository receptionrepo;
	private final MsgRepository msgrepo;
	UserController(UserRepository userrepo,	MsgRepository msgrepo,ParcRoulantRepository prr,ReceptionRepository receptionrepo) {
		this.userrepo = userrepo;
		this.ParcRoulancerepo = prr;
		this.receptionrepo = receptionrepo;
		this.msgrepo = msgrepo;
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public Map<String, String> loginUser(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		User user = userrepo.findByEmail(us.get("email"));
		if (user!=null && user.getPassword().equals(us.get("pw"))) {
			map.put("role",user.getRole());
			map.put("nom",user.getNom());
		} else
			map.put("key", "false");
		return map;
	}
	
	 
	@CrossOrigin(origins = "*")
	@GetMapping("/AllGardien")
	public  List<User> AllGardien() {
		return userrepo.findByRole("gardien");
	}
	 
	
	@CrossOrigin(origins = "*")
	@GetMapping("/Alldirg")
	public  List<User> Alldirg() {
		return userrepo.findByRole("directeur-general");
	}
	 
	
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/Alldirs")
	public  List<User> Alldirs() {
		return userrepo.findByRole("directeur-site");
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/Allusers")
	public  List<User> Allusers() {
		return userrepo.findAll();
	}
	 
	@CrossOrigin(origins = "*")
	@PostMapping("/createmsg")
	public Map<String, String> createmsg(@RequestBody Map<String, String> us) {
		HashMap<String, String> maper = new HashMap<>();
		Msg msg = new Msg();
		msg.setMsg(us.get("msg"));
		msg.setSender(us.get("sen"));
		msg.setReciver(us.get("rec"));
		msg.setDate(new Date());
		msgrepo.save(msg);
		maper.put("key", "true");
		return maper;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/getmsg")
	public List<Msg> getmsg(@RequestBody Map<String, String> us) {		
		List<Msg> msg = msgrepo.getMsg(us.get("rec"),us.get("sen"));
		return msg;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/CreateUser")
	public Map<String, String> CreateUser(@RequestBody Map<String, String> us) {

		HashMap<String, String> maper = new HashMap<>();
		
 		User user = new User();
	
			
        if(us.get("email")!=null && us.get("email")!="")
        	user.setEmail(us.get("nom"));
        if(us.get("nom")!=null && us.get("nom")!="")
        	user.setNom(us.get("nom"));        
        if(us.get("password")!=null && us.get("password")!="")
        	user.setPassword(us.get("password"));
        if(us.get("date")!=null && us.get("date")!="")
        {
        	try {
        		//new Date();
				user.setLogoutdate(new SimpleDateFormat("yyyy-MM-dd").parse(us.get("date")));
			} catch (ParseException e) {
				maper.put("key", "false");
				return maper;
			}
        }
        
        if(us.get("prenom")!=null && us.get("prenom")!="")
        	user.setPrenom(us.get("prenom"));
        if(us.get("role")!=null && us.get("role")!="")
        	user.setRole(us.get("role"));
        if(us.get("site")!=null && us.get("site")!="")
        	user.setSite(us.get("site"));
        if(us.get("tel")!=null && us.get("tel")!="")
        	user.setTel(us.get("tel"));
        
               
			userrepo.save(user);
			maper.put("key", "true");
			return maper;
		
	}
	
	
	
	
	
	
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteUser")
	public Map<String, String> DeleteUser(@RequestBody Map<String, String> us) {
		System.out.print("***************");
		System.out.print(us);
		HashMap<String, String> maper = new HashMap<>();
		User user = userrepo.findByByIdCust(Long.parseLong(us.get("userid")));		
		userrepo.delete(user);
		maper.put("key", "true");
		return maper;
	}

	
	@CrossOrigin(origins = "*")
	@PostMapping("/UpdateUser")
	public Map<String, String> UpdateUser(@RequestBody Map<String, String> us) {

		HashMap<String, String> maper = new HashMap<>();
		User user = userrepo.findByByIdCust(Long.parseLong(us.get("userid")));	
		//User user=new User();
		if(user !=null) {
			
        if(us.get("email")!=null && us.get("email")!="")
        	user.setEmail(us.get("nom"));
        if(us.get("nom")!=null && us.get("nom")!="")
        	user.setNom(us.get("nom"));        
        if(us.get("password")!=null && us.get("password")!="")
        	user.setPassword(us.get("password"));
        if(us.get("date")!=null && us.get("date")!="")
        {
        	try {
        		//new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				user.setLogoutdate(new SimpleDateFormat("yyyy-MM-dd").parse(us.get("date")));
			} catch (ParseException e) {
				maper.put("key", "false");
				return maper;
			}
        }
        
        if(us.get("prenom")!=null && us.get("prenom")!="")
        	user.setPrenom(us.get("prenom"));
        if(us.get("role")!=null && us.get("role")!="")
        	user.setRole(us.get("role"));
        if(us.get("site")!=null && us.get("site")!="")
        	user.setSite(us.get("site"));
        if(us.get("tel")!=null && us.get("tel")!="")
        	user.setTel(us.get("tel"));
        
               
			userrepo.save(user);
			maper.put("key", "true");
			return maper;
		}
		maper.put("key", "false");
		return maper;
	}
	
	
	
	
	
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/stat")
	HashMap<String, List> stat() {
		List<Long> equoi = new ArrayList<>();
		List<Long> Monay = new ArrayList<>();
		List<Long> nbrs = new ArrayList<>();
		HashMap<String, List> map = new HashMap<>();
		String thisYear = Year.now().toString();		
		String cv=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String YeMo=cv.substring(0, 8);
		try {
			nbrs.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(YeMo+ "01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(YeMo+"30 00:00:00")));
			System.out.print("***2****");
			nbrs.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-01-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-31 00:00:00")));
			///////////
			System.out.print("***3****");
			nbrs.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(YeMo+ "01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(YeMo+ "30 00:00:00")));
			System.out.print("***4****");
			nbrs.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-01-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-31 00:00:00")));
			
             // equoi => ParcRoulancerepo nb all months
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-01-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-02-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-02-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-03-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-03-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-04-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-04-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-05-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-05-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-06-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-06-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-07-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-07-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-08-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-08-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-09-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-09-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-10-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-10-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-11-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-11-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-01 00:00:00")));
			equoi.add(ParcRoulancerepo.CountParcRoulant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-01 00:00:00"),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-31 00:00:00")));

			// COut receptionrepo
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-01-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-02-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-02-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-03-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-03-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-04-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-04-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd").parse(thisYear + "-05-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-05-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-06-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-06-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-07-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-07-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-08-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-08-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-09-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-09-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-10-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-10-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-11-01 00:00:00")));
			Monay.add(receptionrepo
					.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-11-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-01 00:00:00")));
			Monay.add(receptionrepo
			.CountReception(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-01 00:00:00"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(thisYear + "-12-31 00:00:00")));
			
			
			//[reception_month,reception_year,parc_month,reception_yera]
			System.out.print("*****************");
			System.out.print(equoi);
			System.out.print(Monay);
			map.put("parcroulancedata", equoi);
			map.put("receptiondata", Monay);
			map.put("nbrs", nbrs);

		} catch (ParseException e) {
			return map;
		}

		
		return map;
	}
	
}
