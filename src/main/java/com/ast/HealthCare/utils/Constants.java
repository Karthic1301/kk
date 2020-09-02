/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ast.HealthCare.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author USER
 */
public class Constants {

    public static final String DB_SCRIPTPATH = "scripts/tablescripts.sql";

    public static final DateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final String ENCRYPT_ALGORITHM = "PBEWITHMD5ANDDES";

    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_QUERY = "query";
    public static final String PARAM_SORTING = "sort";

    public static final String USERNAME = "username";
    public static final String SECRET = "password";

}
