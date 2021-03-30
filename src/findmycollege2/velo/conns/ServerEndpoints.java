package findmycollege2.velo.conns;
import java.util.ArrayList;

import org.json.*;

import org.springframework.boot.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import findmycollege2.velo.data.Piece;
import findmycollege2.velo.data.Profile;


@RestController
public class ServerEndpoints {
	@GetMapping("/get/all")
	public String hello() {
		JSONObject obj = new JSONObject();
		Database db = new Database();
		ArrayList<Object> objects = db.getAllObject();
		for(Object o : objects) {
			JSONObject prof = new JSONObject();
			Profile p = (Profile)o;			
			for(Piece pc : p.getData()) {
				prof.put(pc.getDataType().toString(), pc.getValue());
			}
			obj.put(p.getName(), prof.toString());
		}
		return obj.toString(); 
	}
}
