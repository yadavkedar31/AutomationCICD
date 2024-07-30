package ke.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String,String>> getJsonDataToHashMap() throws IOException
	{	
		//Reading Json to string
		String JsonContent = FileUtils.readFileToString(new File (System.getenv("user.dir")+"//src//test//java//ke//data//PurchaceOrder.json"),StandardCharsets.UTF_8);
		
		//COnvert String to HashMap Jackson DataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference <List<HashMap<String,String>>>(){});
		return data;
	
	}

}
