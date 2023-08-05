package org.apache.apisix.plugin.runner.filter;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class TokenValidator implements PluginFilter {

    @Override
    public String name() {
        return "TokenValidator";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        // parse `conf` to json
        log.info("TokenValidator");
        String configStr = request.getConfig(this);
        Gson gson = new Gson();
        Map<String, Object> conf = new HashMap<>();
        conf = gson.fromJson(configStr, conf.getClass());

        // get configuration parameters
        String token = request.getHeader((String) conf.get("validate_header"));
        String validateUrl = (String) conf.get("validate_url");
        boolean flag = validate(token, validateUrl);

        // token verification results
        if (!flag) {
            String rejectedCode = (String) conf.get("rejected_code");
            response.setStatusCode(Integer.parseInt(rejectedCode));
            chain.filter(request, response);
        }

        chain.filter(request, response);
    }

    private Boolean validate(String token, String validateUrl) {
        //TODO: improve the validation process
        if ("ming".equals(token))
            return false;
        return true;
    }
}