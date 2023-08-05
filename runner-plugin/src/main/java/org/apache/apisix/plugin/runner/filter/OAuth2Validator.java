package org.apache.apisix.plugin.runner.filter;

import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class OAuth2Validator implements PluginFilter{
    @Override
    public String name() {
        return "OAuth2Validator";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        PluginFilter.super.filter(request, response, chain);
    }

    @Override
    public List<String> requiredVars() {
        List<String> vars = new ArrayList<>();
        vars.add("oauth2_check_token_url");
        return vars;
    }
}
