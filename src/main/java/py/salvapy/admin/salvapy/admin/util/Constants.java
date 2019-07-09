/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.util;

/**
 *
 * @author hectorvillalba
 */
public class Constants {
    
    public static final String SECRET_KEY = "SALVAPY.2019";
    
    public static final String PUBLIC_SERVER_URL = "http://45.79.196.30";
    public static final String PUBLIC_API_URL = PUBLIC_SERVER_URL + ":8080/salvapyws/api/";
    public static final String PUBLIC_API_CONFIRM_URL = PUBLIC_API_URL + "auth/public/confirm/";
    
    public static final String HOST_NAME_SMTP = "server1.hostipy.com";
    public static final String PUERT0_SMTP = "465";
    public static final String USER_NAME_SMTP = "noreply@minicubic.com";
    public static final String PASS_SMTP = ".noreply.80*";
    
    public static final String FILE_FORM_NAME = "fileData";
    public static final String UPLOAD_DIR = "/var/www/html";
//    public static final String UPLOAD_DIR = "/sedf/tests/checkit/uploads";
    
    public static final String DB_USR_TIPO_ADMIN_ID = "1";
    public static final String DB_USR_TIPO_USUARIO_ID = "2";
    public static final String DB_USR_TIPO_CLIENTE_ID = "3";
    
    public static final Integer DB_TIPOSEGMENTO_MAPA = 1;
    public static final Integer DB_TIPOSEGMENTO_EDAD = 2;
    public static final Integer DB_TIPOSEGMENTO_SEXO = 3;
    public static final String DB_TIPOSEGMENTO_SPLITTER = "|";

    public static final Integer DB_PREMIO_EFECTIVO_ID = 1;
}
