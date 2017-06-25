package ohopro.com.ohopro.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ohopro.com.ohopro.domains.ErrorDomain;
import ohopro.com.ohopro.domains.ServicesDomain;
import ohopro.com.ohopro.utility.AppConstant;

/**
 * Created by sai on 28-01-2017.
 */

public class ServiceHandler extends BaseHandler {
    ArrayList<ServicesDomain> serviceGroupDomains = new ArrayList<>();
    String errorMessage = AppConstant.NO_RESPONSE;
    Gson gson = new Gson();
    ErrorDomain errorDomain = new ErrorDomain();

    public ServiceHandler(String response) {
        getDataFromResponse(response);
    }

    private void getDataFromResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            serviceGroupDomains = gson.fromJson((new JsonParser().parse(response)).getAsJsonArray(), new TypeToken<ArrayList<ServicesDomain>>() {
            }.getType());
            errorMessage = AppConstant.OK_RESPONSE;
        } catch (JSONException e) {
            errorDomain = gson.fromJson(response, ErrorDomain.class);
            errorMessage = AppConstant.ERROR;
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (errorMessage.equalsIgnoreCase(AppConstant.OK_RESPONSE))
            return serviceGroupDomains;
        else
            return errorDomain;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }
}

