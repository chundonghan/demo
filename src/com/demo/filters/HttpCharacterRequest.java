package com.demo.filters;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpCharacterRequest extends HttpServletRequestWrapper
{
    @SuppressWarnings("rawtypes")
    private Map params;

    @SuppressWarnings("rawtypes")
    public HttpCharacterRequest(HttpServletRequest request, Map newParams)
    {
        super(request);
        this.params = newParams;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map getParameterMap()
    {
        return params;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Enumeration getParameterNames()
    {
        Vector l = new Vector(params.keySet());
        return l.elements();
    }
    @Override
    public String[] getParameterValues(String name)
    {
        Object v = params.get(name);
        if (v == null)
        {
            return null;
        }
        else if (v instanceof String[])
        {
            String[] value = (String[]) v;
            for (int i = 0; i < value.length; i++)
            {
                value[i] = value[i].replaceAll("<", "&lt;");
                value[i] = value[i].replaceAll(">", "&gt;");
            }
            return (String[]) value;
        }
        else if (v instanceof String)
        {
            String value = (String) v;
            value = value.replaceAll("<", "&lt;");
            value = value.replaceAll(">", "&gt;");
            return new String[] { (String) value };
        }
        else
        {
            return new String[] { v.toString() };
        }
    }
    @Override
    public String getParameter(String name)
    {
        Object v = params.get(name);
        if (v == null)
        {
            return null;
        }
        else if (v instanceof String[])
        {
            String[] strArr = (String[]) v;
            if (strArr.length > 0)
            {
                String value = strArr[0];
                value = value.replaceAll("<", "&lt;");
                value = value.replaceAll(">", "&gt;");
                return value;
            }
            else
            {
                return null;
            }
        }
        else if (v instanceof String)
        {
            String value = (String) v;
            value = value.replaceAll("<", "&lt;");
            value = value.replaceAll(">", "&gt;");
            return (String) value;
        }
        else
        {
            return v.toString();
        }
    }

}
