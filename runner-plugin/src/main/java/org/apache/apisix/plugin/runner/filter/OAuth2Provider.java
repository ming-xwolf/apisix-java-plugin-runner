package org.apache.apisix.plugin.runner.filter;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class OAuth2Provider  implements PluginFilter{
    @Override
    public String name() {
        return "OAuth2Provider";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        log.info("OAuth2Provider");
        String configStr = request.getConfig(this);
        Gson gson = new Gson();
        Map<String, Object> conf = new HashMap<>();
        conf = gson.fromJson(configStr, conf.getClass());

        // get configuration parameters
        String clientId = request.getHeader((String) conf.get("client_id"));
        String clientSecret = (String) conf.get("client_secret");

        //
        String token = getToken();
        chain.filter(request, response);
    }

    private String getToken() {
        // http://127.0.0.1:10201/oauth/token
        return null;
    }

    @Override
    public List<String> requiredVars() {
        List<String> vars = new ArrayList<>();
        vars.add("client_id");
        vars.add("client_secret");
        vars.add("oauth2_token_url");
        return vars;
    }
}
