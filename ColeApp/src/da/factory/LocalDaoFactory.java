package da.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalDaoFactory extends DAOFactory{
	
	public JSONObject crearConexionLocal(String url) throws IOException, JSONException{
		
		JSONObject  jObj = null;
        InputStream iStream = null;
        try{
            HttpGet hGet = new HttpGet("http://192.168.1.33:88/autonoma/dao.interfaces/"+url);
            //ServerLocal de Prueba 192.168.1.33
            HttpClient hClient = new DefaultHttpClient();
            HttpResponse response = (HttpResponse)hClient.execute(hGet);
            HttpEntity entity = response.getEntity();
            BufferedHttpEntity buffer = new BufferedHttpEntity(entity);
            iStream = buffer.getContent();
            String aux= "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            String line;
            while((line = reader.readLine()) != null){
                aux +=line;
            }
            jObj = new JSONObject(aux);
        }
        catch(Exception ex){
            jObj = new JSONObject();
        }
        finally{
            if(iStream != null){
                iStream.close();
            }
        }
        return jObj;
		
	}
	

}
